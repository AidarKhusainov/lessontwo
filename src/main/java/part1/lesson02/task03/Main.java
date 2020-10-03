package part1.lesson02.task03;

import java.util.ArrayList;

import static part1.lesson02.task02.Main.*;

public class Main {

    private static int sizeOfArray;
    private static ArrayList<Person> persons = new ArrayList<Person>(sizeOfArray);

    public static void main(String[] args) {
        sizeOfArray = inputInt();
        for (int i = 0; i < sizeOfArray; i++) {
            persons.add(new Person(getRandNumber(0, 100), getRandomWord()));
            printToConsole(persons.get(i).getName());
        }

        //Sort bubbleSort = new Sort(persons);
//        Sort insertionSort = new Sort(persons);
    }

    public static String getRandomWord() {
        String randomString;
        char[] word = new char[getRandNumber(1, 8)];

        for (int j = 0; j < word.length; j++) {
            word[j] = (char) ('a' + getRandNumber(1, 30));
        }
        randomString = new String(word);

        return randomString;
    }
}
