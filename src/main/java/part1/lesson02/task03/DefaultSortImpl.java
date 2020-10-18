package part1.lesson02.task03;

import part1.lesson02.task03.person.Person;
import part1.lesson02.task03.person.PersonAgeComparator;
import part1.lesson02.task03.person.PersonNameComparator;
import part1.lesson02.task03.person.PersonSexComparator;

import java.util.Arrays;
import java.util.List;

/**
 * Класс реализует сортировку с помощью компаратора.
 */
public class DefaultSortImpl implements ICustomSort {
    @Override
    public void customSort(Person[] persons) {
        List<Person> list = Arrays.asList(persons);
        list.sort(new PersonSexComparator().thenComparing(new PersonAgeComparator()).thenComparing(new PersonNameComparator()));
    }
}
