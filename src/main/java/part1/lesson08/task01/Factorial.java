package part1.lesson08.task01;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * Класс вычисляет факториалы для массива чисел
 */
public class Factorial implements Callable<BigInteger[]> {

    private Integer currentFactorial;
    private Integer[] arrayOfNumber;
    private HashMap<Integer, BigInteger> cache = new HashMap<>();

    public Factorial(Integer[] arrayOfNumber) {
        this.arrayOfNumber = arrayOfNumber;
    }

    /**
     * Вычисляет факториал для всех элементов массива
     *
     * @return computed result
     */
    @Override
    public BigInteger[] call() {
        BigInteger[] result = new BigInteger[arrayOfNumber.length];

        for (int i = 0; i < this.arrayOfNumber.length; i++) {
            result[i] = findFactorial(this.arrayOfNumber[i]);
        }
        return result;
    }

    private BigInteger findFactorial(Integer number) {
        if (number == 0 || number == 1) return BigInteger.ONE;

        BigInteger result = cache.get(number);

        if (result != null) {
            return result;
        }

        setCurrentFactorial(getKeyOfMaxFactFromCache());
        result = (result = cache.get(getCurrentFactorial())) != null ? result : BigInteger.ONE;

        for (int i = getCurrentFactorial() + 1; i <= number; i++) {
            result = result.multiply(BigInteger.valueOf(i));
            cache.put(i, result);
        }

        return result;
    }

    /**
     * Получить ключ максимально большого значения вычисленного факториала из кеша
     */
    private Integer getKeyOfMaxFactFromCache() {
        int maxKeyFact = 1;

        if (cache.keySet().size() != 0) {
            maxKeyFact = Collections.max(cache.keySet());
        }
        return maxKeyFact;
    }

    private Integer getCurrentFactorial() {
        return currentFactorial;
    }

    private void setCurrentFactorial(Integer currentFactorial) {
        this.currentFactorial = currentFactorial;
    }

}
