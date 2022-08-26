package machine;

import java.util.Scanner;
import java.lang.Math;

// This class will maintain the state of the coffee machine. No calculations.
public class state {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean isRunning = true; // an indicator of whether the program is running or not.
    public static State currentState = State.CHOOSING_AN_ACTION; // the default state is to choose an action.

    enum State { // the enum is helping in organizing the states of the machine.
        CHOOSING_AN_ACTION, CHOOSING_A_COFFEE, FILLING_MACHINE, TAKING_MONEY
    }

    public static void start() { // This function starts the machine.
        while (state.isRunning) {
            state.operationsManager(state.currentState);
        }


    }

    public static void operationsManager(State state) { // This function will operate differently according to the current state.
        switch (state) {
            case CHOOSING_AN_ACTION:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                String action = scanner.nextLine();
                switch (action) { // this nested switch is meant to change the state.
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
            // From here, the cases will act according to the state that have been changed in the previous switch.
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
