package part1.lesson02.task01;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The class implements the second lesson.
 *
 * @author Aidar Khusainov
 * @version 1.0.0 02.10.2020
 */
public class Main {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        System.out.println("Enter the task (1, 2, 3) or 0 for exit: ");

        Scanner in = new Scanner(System.in);
        int taskNum = 0;

        do {
            try {
                taskNum = in.nextInt();

                switch (taskNum) {
                    case 0:
                        break;
                    case 1:
                        generateNPE();
                        break;
                    case 2:
                        generateArrayIndexOutOfBoundsException();
                        break;
                    case 3:
                        generateYourError("Cool!");
                        break;
                    default:
                        System.out.println("The command does not exist.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Main.main: " + e);
            }
        } while (taskNum != 0);
    }

    /**
     * The function generates the NPE
     */
    private static void generateNPE() {
        Object npeObject = null;

        if (npeObject.equals("HelloWorld")) {
            System.out.println("It won't work.");
        }
    }

    /**
     * The function generates the ArrayIndexOutOfBoundsException
     */
    private static void generateArrayIndexOutOfBoundsException() {
        int[] arrCount = new int[]{1, 2, 3, 4, 5};

        for (int i = 0; i < arrCount.length; i++) {
            if (arrCount[arrCount.length + 1] > 5) {
                System.out.println("It's impossible!");
            }
        }
    }

    /**
     * The function generates your error
     *
     * @param textError Error text for output
     * @throws Exception
     */
    private static void generateYourError(String textError) throws Exception {
        throw new Exception(textError);
    }
}
