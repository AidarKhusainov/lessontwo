package part1.lesson02.task03;

import part1.lesson02.task03.person.Person;
import part1.lesson02.task03.person.PersonAgeComparator;
import part1.lesson02.task03.person.PersonNameComparator;
import part1.lesson02.task03.person.PersonSexComparator;

import java.util.List;

public class SortImpl implements Sortable {

    public SortImpl(List<Person> personList) {
    }

    public void sortOfCollectionByDefault(List<Person> list) {
        list.sort(new PersonSexComparator().thenComparing(new PersonAgeComparator()).thenComparing(new PersonNameComparator()));
    }

    public void bubbleSort(List<Person> list){
        sortSex(list);
        sortSexAge(list);
        sortSexAgeName(list);
    }

    private void sortSex(List<Person> list) {
        int sexCompareRes;
        for (int j = 1; j < list.size(); j++) {
            for (int i = 1, prevInd = i - 1; i < list.size(); i++, prevInd++) {
                sexCompareRes = list.get(i).getSex().compareTo(list.get(prevInd).getSex());

                if (sexCompareRes == -1) {
                    swap(list, prevInd, i);
                }
            }
        }
    }

    private void sortSexAge(List<Person> list) {
        int sexCompareRes;
        int ageCompareRes;
        for (int j = 1; j < list.size(); j++) {
            for (int i = 1, prevInd = i - 1; i < list.size(); i++, prevInd++) {
                sexCompareRes = list.get(prevInd).getSex().compareTo(list.get(i).getSex());
                ageCompareRes = Integer.compare(list.get(i).getAge(), list.get(prevInd).getAge());

                if (sexCompareRes != -1 && ageCompareRes == -1) {
                    swap(list, prevInd, i);
                }

            }
        }
    }

    private void sortSexAgeName(List<Person> list) {
        int sexCompareRes;
        int ageCompareRes;
        int nameCompareRes;
        for (int j = 1; j < list.size(); j++) {
            for (int i = 1, prevInd = i - 1; i < list.size(); i++, prevInd++) {
                sexCompareRes = list.get(prevInd).getSex().compareTo(list.get(i).getSex());
                ageCompareRes = Integer.compare(list.get(i).getAge(), list.get(prevInd).getAge());
                nameCompareRes = list.get(prevInd).getName().compareTo(list.get(i).getName());

                if (sexCompareRes != -1 && ageCompareRes != -1 && nameCompareRes == -1) {
                    swap(list, prevInd, i);
                }
            }
        }
    }

    public static void printPersonToConsole(String message, List<Person> list) {
        System.out.println(message);
        list.forEach((person) -> System.out.println(person.getSex() + " " + person.getAge() + " " + person.getName()));
    }

    private void swap(List<Person> list, int prevInd, int currInd) {
        Person personTmp = list.get(prevInd);
        list.add(prevInd, list.get(currInd));
        list.add(currInd, personTmp);
        list.remove(prevInd + 1);
        list.remove(currInd + 1);
    }

}
