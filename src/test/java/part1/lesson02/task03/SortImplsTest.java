package part1.lesson02.task03;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import part1.lesson02.task03.person.Person;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SortImplsTest {

    @Test
    public void quickSortTest() {
        final ICustomSort quickSort = new QuickSortImpl();
        Person[] quickSorted = getTestData();
        Person[] sorted = sortArray(quickSorted);
        time(quickSorted, quickSort);
        assertArrayEquals(quickSorted, sorted);
    }

    @Test
    public void defaultSortTest() {
        final ICustomSort defaultSort = new DefaultSortImpl();
        Person[] defSorted = getTestData();
        Person[] sorted = sortArray(getTestData());
        time(defSorted, defaultSort);
        assertArrayEquals(defSorted, sorted);
    }

    private void time(Person[] people, ICustomSort sorter) {
        long before = System.nanoTime();
        sorter.customSort(people);
        long after = System.nanoTime();
        System.out.println(after - before);
    }

    @NotNull
    private Person[] getTestData() {
        return new Person[]{
                new Person(5, "name1", Sex.MAN),
                new Person(5, "name1", Sex.MAN),
                new Person(6, "name1", Sex.MAN),
                new Person(5, "name2", Sex.MAN),
                new Person(4, "name2", Sex.MAN),
                new Person(5, "name2", Sex.MAN),
                new Person(5, "name2", Sex.MAN),
                new Person(4, "name1", Sex.MAN),
                new Person(6, "name1", Sex.MAN),
                new Person(6, "name1", Sex.MAN),
                new Person(6, "name2", Sex.MAN),
                new Person(6, "name2", Sex.MAN),
                new Person(6, "name2", Sex.WOMAN),
                new Person(6, "name2", Sex.WOMAN),
                new Person(6, "name2", Sex.WOMAN),
                new Person(5, "name2", Sex.WOMAN),
                new Person(5, "name2", Sex.WOMAN),
                new Person(5, "name", Sex.WOMAN),
                new Person(5, "name", Sex.WOMAN),
                new Person(5, "name", Sex.WOMAN),
                new Person(5, "name1", Sex.MAN)
        };
    }


    /**
     * выдает новый отсортированный массив
     */
    private static Person[] sortArray(Person[] people) {
        return Arrays.stream(people).sorted((a, b) -> {
            int order;
            if ((order = a.getSex().toString().compareTo(b.getSex().toString())) != 0) {
                return order;
            }
            if ((order = Integer.compare(b.getAge(), a.getAge())) != 0) {
                return order;
            }
            if ((order = a.getName().compareTo(b.getName())) != 0) {
                return order;
            }
            return order;
        }).toArray(Person[]::new);
    }
}