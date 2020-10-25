package part1.lesson02.task03;

import part1.lesson02.task03.person.Person;
import part1.lesson02.task03.person.PersonComparator;

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

        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int middleMarker = leftMarker - (leftMarker - rightMarker) / 2;
        int rightTmpMarker = rightBorder;
        int leftTmpMarker = 0;
        PersonComparator personComparator = new PersonComparator();

        while (leftMarker < rightMarker) {

            while (personComparator.compare(person[leftMarker], person[middleMarker]) <= 0
                    && leftMarker < middleMarker) {
                leftMarker++;
            }

            while (personComparator.compare(person[rightMarker], person[middleMarker]) >= 0
                    && rightMarker > middleMarker) {
                rightMarker--;
            }

            if (leftMarker <= rightMarker) {

                while (personComparator.compare(person[leftTmpMarker], person[rightMarker]) <= 0
                        && leftTmpMarker < rightMarker) {
                    leftTmpMarker++;
                }

                if (personComparator.compare(person[leftTmpMarker], person[rightMarker]) >= 0
                        && leftTmpMarker < rightMarker) {
                    swap(person, rightMarker, leftTmpMarker);
                }

                while (personComparator.compare(person[rightTmpMarker], person[leftMarker]) >= 0
                        && rightTmpMarker > leftMarker) {
                    rightTmpMarker--;
                }

                if (personComparator.compare(person[rightTmpMarker], person[leftMarker]) <= 0
                        && rightTmpMarker > leftMarker) {
                    swap(person, leftMarker, rightTmpMarker);
                }

                if (leftMarker == middleMarker)
                    middleMarker = rightMarker;
                else if (rightMarker == middleMarker)
                    middleMarker = leftMarker;

                rightTmpMarker = rightBorder;
                leftTmpMarker = 0;

                leftMarker++;
                rightMarker--;
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

