package part1.lesson02.task03;

import part1.lesson02.task03.person.Person;

import java.util.List;

/**
 * Интерфейс для сортировки Person
 * BubbleSort - Пузырьковая сортировка
 */
interface BubbleSort {
    void bubbleSort(List<Person> personList);
}
