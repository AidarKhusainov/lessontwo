package part1.lesson02.task02;

import java.util.Random;
import java.util.Scanner;

/**
 * The class implements the second lesson.
 *
 * @author Aidar Khusainov
 * @version 1.0.0 03.10.2020
 */
public class Main {
    public static void main(String[] args) {
        printToConsole("Generate: ");

        int amountOfNum = inputInt();
        int genNumber;
        int squareWholePart;

        for (int i = 0; i < amountOfNum; i++) {
            genNumber = getRandNumber(-100, 100);
            try {
                if (genNumber < 0) {
                    throw new ArithmeticException("Отрицательное число");
                }

                squareWholePart = (int) Math.pow(2, getWholeFraction(findSqrt(genNumber)));

                if (genNumber == squareWholePart) {
                    printToConsole(genNumber);
                }
            } catch (ArithmeticException e) {
                printToConsole(e.toString());
            }
        }
    }

    public static int getWholeFraction(double fraction) {
        return (int) fraction;
    }

    public static double findSqrt(int number) {
        return Math.sqrt(number);
    }

    public static int getRandNumber(int rangeFrom, int rangeTo) {
        Random random = new Random();
        return random.nextInt(rangeTo - rangeFrom) + rangeFrom;
    }

    public static int inputInt() {
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    public static void printToConsole(Object printText) {
        System.out.println(printText);
    }

//    public static void printToConsole(int printText) {
//        System.out.println(printText);
//    }
}
