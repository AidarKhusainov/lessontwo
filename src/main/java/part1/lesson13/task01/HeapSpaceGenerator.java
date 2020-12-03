package part1.lesson13.task01;

/**
 * Класс воспроизводит ошибку java.lang.OutOfMemoryError: Java heap space
 */
public class HeapSpaceGenerator {
    private int numberOfIter;

    public HeapSpaceGenerator(int numberOfIter) {
        this.numberOfIter = numberOfIter;
    }

    public void generate() {
        int multiplier = 1000;
        System.out.println("Start with initial free memory: " + Runtime.getRuntime().freeMemory());

        for (int i = 1; i < numberOfIter; i++) {
            int[] myIntList = new int[multiplier];

            System.out.println("Free Memory: " + Runtime.getRuntime().freeMemory() +
                    " # Round: " + i +
                    " # len: " + myIntList.length);

            multiplier = multiplier * 10;
        }
    }
}
