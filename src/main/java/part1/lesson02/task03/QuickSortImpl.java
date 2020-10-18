package part1.lesson02.task03;

import part1.lesson02.task03.person.Person;

/**
 * Класс реализует алгоритм быстрой сортировки.
 *
 * @author Aidar
 */
public class QuickSortImpl implements ICustomSort {
    @Override
    public void customSort(Person[] persons) {
        quickSort(persons, 0, persons.length - 1);
    }

    public void quickSort(Person[] person, int leftBorder, int rightBorder) {
        if (person.length == 0)
            return;

        if (leftBorder >= rightBorder)
            return;

        int leftSexMarker = leftBorder;
        int rightSexMarker = rightBorder;

        int middleMarker = leftSexMarker - (leftSexMarker - rightSexMarker) / 2;

        while (leftSexMarker < rightSexMarker) {
            while (compareSex(person[leftSexMarker], person[middleMarker]) && leftSexMarker < middleMarker) {

                if (compareAge(person[leftSexMarker], person[middleMarker]) && person[leftSexMarker].getSex() == person[middleMarker].getSex()) {

                    swap(person, leftSexMarker, middleMarker);

                    if (compareName(person[leftSexMarker], person[middleMarker]) && person[leftSexMarker].getAge() > person[middleMarker].getAge()) {
                        swap(person, leftSexMarker, middleMarker);
                    }
                }
                leftSexMarker++;
            }

            while (compareSex(person[middleMarker], person[rightSexMarker]) && rightSexMarker > middleMarker) {

                if (compareAge(person[middleMarker], person[rightSexMarker]) && person[middleMarker].getSex() == person[rightSexMarker].getSex()) {

                    swap(person, middleMarker, rightSexMarker);

                    if (compareName(person[middleMarker], person[rightSexMarker]) && person[rightSexMarker].getAge() < person[middleMarker].getAge()) {
                        swap(person, middleMarker, rightSexMarker);
                    }
                }
                rightSexMarker--;
            }

            if (leftSexMarker <= rightSexMarker) {

                swap(person, rightSexMarker, leftSexMarker);

                if (leftSexMarker == middleMarker)
                    middleMarker = rightSexMarker;
                else if (rightSexMarker == middleMarker)
                    middleMarker = leftSexMarker;

                leftSexMarker++;
                rightSexMarker--;
            }
        }
        quickSort(person, leftBorder, middleMarker);
        quickSort(person, middleMarker + 1, rightBorder);
    }


    private boolean compareName(Person person1, Person person2) {
        return person1.getName().compareTo(person2.getName()) != 1;
    }

    private boolean compareAge(Person person1, Person person2) {
        return Integer.compare(person1.getAge(), person2.getAge()) != 1;
    }

    private boolean compareSex(Person sex1, Person sex2) {
        if (sex1.getSex().compareTo(sex2.getSex()) == 1) return false;
        return true;
    }

    private void swap(Person[] person, int leftMarker, int rightMarker) {
        Person tmp = person[leftMarker];
        person[leftMarker] = person[rightMarker];
        person[rightMarker] = tmp;
    }
}

