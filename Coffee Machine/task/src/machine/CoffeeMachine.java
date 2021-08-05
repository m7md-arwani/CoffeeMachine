package machine;

import java.util.Scanner;
import java.lang.Math;

public class CoffeeMachine {


    public static void main(String[] args) {
        while (state.isRunning) {
            state.programRuns(state.currentState);
        }


    }


}


class state {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean isRunning = true;
    public static State currentState = State.CHOOSING_AN_ACTION;

    enum State {
        CHOOSING_AN_ACTION, CHOOSING_A_COFFEE, FILLING_MACHINE, TAKING_MONEY
    }

    public static void programRuns(State state) {
        switch (state) {
            case CHOOSING_AN_ACTION:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                String action = scanner.nextLine();
                switch (action) {
                    case "buy":
                        currentState = State.CHOOSING_A_COFFEE;
                        break;
                    case "fill":
                        currentState = State.FILLING_MACHINE;
                        break;
                    case "take":
                        currentState = State.TAKING_MONEY;
                        break;
                    case "remaining":
                        newCoffeeMachine.status();
                        break;
                    case "exit":
                        isRunning = false;
                        break;
                }
                break;
            case CHOOSING_A_COFFEE:
                newCoffeeMachine.buy();
                currentState = State.CHOOSING_AN_ACTION;
                break;
            case FILLING_MACHINE:
                newCoffeeMachine.fill();
                currentState = State.CHOOSING_AN_ACTION;
                break;
            case TAKING_MONEY:
                newCoffeeMachine.take();
                currentState = State.CHOOSING_AN_ACTION;
                break;
        }
    }
}


class newCoffeeMachine {
    static double water = 400;
    static double milk = 540;
    static double coffeeBeans = 120;
    static int cups = 9;
    static int money = 550;


    enum coffTypes {
        ESPRESSO(250, 0, 16, 4), LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6),
        ;
        int water;
        int milk;
        int coffeeBeans;
        int money;

        coffTypes(int water, int milk, int coffeeBeans, int money) {
            this.water = water;
            this.milk = milk;
            this.coffeeBeans = coffeeBeans;
            this.money = money;
        }
    }

    // This function is abstract when it comes to quantity. This program allows buying one cup at a time,
    // the function is ready for a feature update where the user can buy more cups at a time.
    // It is able to deal with double values too.

    public static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        Scanner g = new Scanner(System.in);
        String choice = g.nextLine();
        switch (choice) {
            case "1":
                if (check_before_selling(coffTypes.ESPRESSO.water, coffTypes.ESPRESSO.milk, coffTypes.ESPRESSO.coffeeBeans, 1)) {
                    water -= coffTypes.ESPRESSO.water;
                    coffeeBeans -= coffTypes.ESPRESSO.coffeeBeans;
                    money += coffTypes.ESPRESSO.money;
                    cups--;
                }

                break;
            case "2":
                if (check_before_selling(coffTypes.LATTE.water, coffTypes.LATTE.milk, coffTypes.LATTE.coffeeBeans, 1)) {
                    water -= coffTypes.LATTE.water;
                    milk -= coffTypes.LATTE.milk;
                    coffeeBeans -= coffTypes.LATTE.coffeeBeans;
                    money += coffTypes.LATTE.money;
                    cups--;
                }
                break;
            case "3":
                if (check_before_selling(coffTypes.CAPPUCCINO.water, coffTypes.CAPPUCCINO.milk, coffTypes.CAPPUCCINO.coffeeBeans, 1)) {
                    water -= coffTypes.CAPPUCCINO.water;
                    milk -= coffTypes.CAPPUCCINO.milk;
                    coffeeBeans -= coffTypes.CAPPUCCINO.coffeeBeans;
                    money += coffTypes.CAPPUCCINO.money;
                    cups--;
                }

                break;
            case "back":
                break;

            default:
                System.out.println("Wrong number");
                break;

        }

    }

    public static void fill() {
        Scanner g = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        water += g.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += g.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add:");
        coffeeBeans += g.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add:");
        cups += g.nextInt();

    }

    public static boolean check_before_selling(int neededWater, int neededMilk, int neededCoffee, int quantity) {

        if (!(Math.floor(water / (neededWater * quantity)) >= quantity)) {
            System.out.println("Sorry, not enough water!");
            return false;
        }

        if (!(milk - (quantity * neededMilk) >= 0)) {
            System.out.println("Sorry, not enough milk!");
            return false;
        }

        if (!(Math.floor(coffeeBeans / (neededCoffee * quantity)) >= quantity)) {
            System.out.println("Sorry, not enough coffee beans!");
            return false;
        }

        if (!(cups >= quantity)) {
            System.out.println("Sorry, not enough disposable cups!");
            return false;
        }
        System.out.println("I have enough resources, making you a coffee!");
        return true;
    }

    public static void take() {
        System.out.println("I gave you " + money);
        money = 0;


    }

    public static void status() {
        System.out.println("The coffee machine has:");
        System.out.println((int) water + " ml of water");
        System.out.println((int) milk + " ml of milk");
        System.out.println((int) coffeeBeans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println(money + " of money");
    }

}
