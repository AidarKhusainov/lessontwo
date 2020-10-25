package part1.lesson02.task03;

import java.util.*;

import part1.lesson02.task01.MyException;
import part1.lesson02.task03.person.Person;

import static part1.lesson02.task02.Main.*;
import static part1.lesson02.task03.person.Person.printPersonToConsole;

public class Main {
    static final String upperCaseLexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String lowerCaseLexicon = "abcdefghijklmnopqrstuvwxyz";
    static final Set<String> identifiers = new HashSet<>();
    private final static int SIZE_OF_ARRAY = 100000;
    static Person[] persons = new Person[SIZE_OF_ARRAY];
    static Person[] personsDef = new Person[] {
            new Person(9, "A",      Sex.WOMAN),
            new Person(1, "B",      Sex.WOMAN),
            new Person(3, "C",      Sex.MAN  ),
            new Person(7, "D",      Sex.WOMAN),
            new Person(3, "E",      Sex.MAN ),
            new Person(1, "F",      Sex.WOMAN),
            new Person(8, "G",      Sex.WOMAN),
            new Person(7, "H",      Sex.MAN),
            new Person(4, "J",      Sex.WOMAN),
            new Person(7, "A",      Sex.WOMAN  ),
            new Person(1, "L",      Sex.WOMAN),
            new Person(7, "A",      Sex.MAN),
            new Person(6, "O",      Sex.WOMAN),
            new Person(5, "I",      Sex.MAN  ),
    };

    public static void main(String[] args) throws MyException {
        ICustomSort[] sorts = {new QuickSortImpl(), new DefaultSortImpl()};
        Person[] generatePerson = generatePersonsWithRandomValues(SIZE_OF_ARRAY);
        for (ICustomSort sort : sorts) {
            persons = generatePerson;
//            persons = personsDef;
            checkDuplicatesAndThrowException(persons);

//            printPersonToConsole("-------------------------Start sort-------------------------", persons);
            long startTime = System.currentTimeMillis();

            sortingOfPersons(sort, persons);

            long endTime = System.currentTimeMillis();
//            printPersonToConsole("-------------------------End sort-------------------------", persons);
            System.out.print("\nВремя выполнения " + sort.getClass().toString() + ": " + (endTime - startTime) + "мс.\n");
        }
    }

    private static void sortingOfPersons(ICustomSort sort, Person[] persons) {
        sort.customSort(persons);
    }

    /**
     * @param sizeOfArray - размер массива
     */
    private static Person[] generatePersonsWithRandomValues(int sizeOfArray) {
        Person[] persons = new Person[sizeOfArray];
        for (int i = 0; i < sizeOfArray; i++) {
            persons[i] = new Person(
                    getRandNumber(0, 100),
                    getRandomString(5, 15),
                    getRandNumber(0, 1) == 1 ? Sex.MAN : Sex.WOMAN);
        }
        return persons;
    }


    /**
     * @param rangeFrom - Длина строки от
     * @param rangeTo   - Длина строки до
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
     * @param persons - массив объектов Person
     * @throws MyException - Выброс исключения в случае нахождения дубликата
     */
    public static void checkDuplicatesAndThrowException(Person[] persons) throws MyException {
        if (persons.length == 0) return;
        HashSet<String> set = new HashSet<>();
        boolean isAdded;
        for (Person person : persons) {
            isAdded = set.add(person.getAge() + person.getName());
            if (!isAdded) {
                throw new MyException("Имена людей и возраст совпадают: " + person.getAge() + " " + person.getName());
            }
        }
    }

}
