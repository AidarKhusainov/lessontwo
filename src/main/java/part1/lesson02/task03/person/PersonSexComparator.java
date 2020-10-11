package part1.lesson02.task03.person;

import java.util.Comparator;

public class PersonSexComparator implements Comparator<Person> {

    public int compare(Person a, Person b) {
        return a.getSex().compareTo(b.getSex());
    }
}
