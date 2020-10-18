package part1.lesson02.task03.person;

import java.util.Comparator;


/**
 * Класс для сравнения имени и возраста Person
 */
public class PersonComparator implements Comparator<Person> {

    public int compare(Person a, Person b) {
        int order;
        if ((order = a.getSex().toString().compareTo(b.getSex().toString())) != 0) {
            return order;
        }
        if ((order = Integer.compare(b.getAge(), a.getAge())) != 0) {
            return order;
        }
        if ((order = a.getName().compareTo(b.getName())) != 0) {
            return order;
        }
        return order;
    }
}
