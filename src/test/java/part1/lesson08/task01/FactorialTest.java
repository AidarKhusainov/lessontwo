package part1.lesson08.task01;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.*;
import static part1.lesson02.task02.Main.getRandNumber;

class FactorialTest {
    int SIZE_OF_ARRAY = 100;

    @Test
    void callTestWithRandomNumbers() {
        Integer[] arrayOfNumber = new Integer[SIZE_OF_ARRAY];
        for (int i = 0; i < SIZE_OF_ARRAY; i++) {
            arrayOfNumber[i] = getRandNumber(0, 500);
        }
        Factorial factorial = new Factorial(arrayOfNumber);
        BigInteger[] calcFactorial = factorial.call();
        System.out.println(Arrays.toString(calcFactorial));
    }

    @Test
    void call() throws ExecutionException, InterruptedException {
        Integer[] arrayOfNumber = new Integer[]{4, 5, 0, 8, 3, 9, 1, 2};
        Factorial factorial = new Factorial(arrayOfNumber);

        final ExecutorService service = Executors.newCachedThreadPool();
        Future<BigInteger[]> future = service.submit(factorial);

        assertArrayEquals(new BigInteger[]{
                        BigInteger.valueOf(24),
                        BigInteger.valueOf(120),
                        BigInteger.valueOf(1),
                        BigInteger.valueOf(40320),
                        BigInteger.valueOf(6),
                        BigInteger.valueOf(362880),
                        BigInteger.valueOf(1),
                        BigInteger.valueOf(2)},
                future.get());
        service.shutdown();
    }
}