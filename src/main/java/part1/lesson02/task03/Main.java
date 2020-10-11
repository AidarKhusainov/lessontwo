package part1.lesson02.task03;


import java.util.*;

import part1.lesson02.task01.MyException;
import part1.lesson02.task03.person.Person;

import static part1.lesson02.task02.Main.*;
import static part1.lesson02.task03.SortImpl.printPersonToConsole;


public class Main {
    static final String upperCaseLexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String lowerCaseLexicon = "abcdefghijklmnopqrstuvwxyz";
    static final Set<String> identifiers = new HashSet<>();
    private final static int SIZE_OF_ARRAY = 100;

    static List<Person> personList = new ArrayList<>(
//            Arrays.asList(
//                    new Person(getRandNumber(0, 100), "Ada", Sex.WOMAN),
//                    new Person(getRandNumber(0, 100), "Ivanko", Sex.MAN),
//                    new Person(50, "Anna", Sex.WOMAN),
//                    new Person(getRandNumber(0, 100), "Aidar", Sex.MAN),
//                    new Person(getRandNumber(0, 100), "Ivan", Sex.MAN),
//                    new Person(50, "Anna", Sex.MAN)
//            )
    );

    public static void main(String[] args) throws MyException {

        fillInPersonListWithRandomValues(SIZE_OF_ARRAY, personList);

        checkDuplicatesAndThrowException(personList);

        printPersonToConsole("---Start sort---", personList);

        long startTime = System.currentTimeMillis();

//        Sortable sortableBubble = new SortImpl(personList);
//        sortableBubble.bubbleSort(personList);

        Sortable sortableDefault = new SortImpl(personList);
        sortableDefault.sortOfCollectionByDefault(personList);

        double endTime = (double) System.currentTimeMillis();
        System.out.println("\nВремя выполнения программы: " + ((endTime - startTime) / 1000) + "c.\n");

        printPersonToConsole("---End sort---", personList);

    }

    /**
     * @param lengthOfArray - размер массива
     * @param personList    - массив Person
     */
    private static void fillInPersonListWithRandomValues(int lengthOfArray, List<Person> personList) {
        for (int i = 0; i < lengthOfArray; i++) {
            personList.add(new Person(
                    getRandNumber(0, 100),
                    getRandomString(5, 15),
                    getRandNumber(0, 1) == 1 ? Sex.MAN : Sex.WOMAN));
        }
    }

    /**
     * @param rangeFrom - Длина строки от
     * @param rangeTo - Длина строки до
     * @return - Возвращает случайную строку с заглавной буквы
     */
    public static String getRandomString(int rangeFrom, int rangeTo) {
        StringBuilder builder = new StringBuilder();

        while (builder.toString().length() == 0) {
            int length = getRandNumber(rangeFrom, rangeTo);
            builder.append(upperCaseLexicon.charAt(getRandNumber(1, upperCaseLexicon.length() - 1)));

            for (int i = 0; i < length; i++) {
                builder.append(lowerCaseLexicon.charAt(getRandNumber(0, lowerCaseLexicon.length() - 1)));
            }

            if (identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    /**
     * Проверяет отсортированный массив на дубликаты
     *
     * @param personList - массив объектов Person
     * @throws MyException - Выброс исключения в случае нахождения дубликата
     */
    public static void checkDuplicatesAndThrowException(List<Person> personList) throws MyException {
        HashSet<String> set = new HashSet<>();
        boolean isAdded;
        for(Person person : personList){
            isAdded = set.add(person.getAge() + person.getName());
            if (!isAdded) {
                throw new MyException("Имена людей и возраст совпадают: " + person.getAge() + " " + person.getName());
            }
        }
    }

}
