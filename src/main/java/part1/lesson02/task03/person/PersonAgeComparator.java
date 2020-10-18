package part1.lesson02.task03.person;

import java.util.Comparator;

public class PersonAgeComparator implements Comparator<Person> {

    public int compare(Person a, Person b) {
        return Integer.compare(b.getAge(), a.getAge());
    }
}
