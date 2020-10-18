package part1.lesson02.task03.person;

import java.util.Comparator;


/**
 * Класс для сравнения имени и возраста Person
 */
public class PersonComparator implements Comparator<Person> {
    @Override
    public int compare(Person o1, Person o2) {
        int compareName = o1.getName().compareTo(o2.getName());

        if (compareName != 0) {
            return compareName;
        }

        int compareAge = Integer.compare(o1.getAge(), o2.getAge());

        if (compareAge != 0) {
            return compareAge;
        }

        return 0;
    }
}
