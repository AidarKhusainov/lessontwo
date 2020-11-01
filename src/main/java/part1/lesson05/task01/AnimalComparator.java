package part1.lesson05.task01;

import java.util.Comparator;

/**
 * Компаратор для сравнения животных.
 * Поля для сортировки –  хозяин, кличка животного, вес
 */
public class AnimalComparator implements Comparator<Animal> {

    public int compare(Animal a, Animal b) {
        int order;
        if ((order = a.getOwner().compareTo(b.getOwner())) != 0) {
            return order;
        }
        if ((order = a.getName().compareTo(b.getName())) != 0) {
            return order;
        }
        if ((order = Integer.compare(b.getWeight(), a.getWeight())) != 0) {
            return order;
        }
        return order;
    }
}