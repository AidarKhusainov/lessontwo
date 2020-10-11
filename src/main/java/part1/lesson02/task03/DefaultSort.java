package part1.lesson02.task03;

import part1.lesson02.task03.person.Person;

import java.util.List;


/**
 * Интерфейс для сортировки Person
 * DefaultSort - Сортировка с помощью Comparator
 */
interface DefaultSort {
    void sortOfCollectionByDefault(List<Person> personList);
}
