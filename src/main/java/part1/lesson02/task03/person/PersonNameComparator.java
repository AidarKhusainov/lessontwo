package part1.lesson02.task03.person;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {

    public int compare(Person a, Person b) {
        return a.getName().compareTo(b.getName());
    }
}
