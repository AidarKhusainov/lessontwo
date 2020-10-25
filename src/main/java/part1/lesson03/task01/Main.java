package part1.lesson03.task01;

public class Main {
    public static void main(String[] args) {
        final int NUMBER_TO_FIND_DUPLICATE = 100;
        final Number DENOMINATOR = 0.2;

        Number[] arrOfNumY = new Number[]{100, 0.4, 0xaf, 10.5, 100};
        Number[] arrOfNumX = new Number[]{100, 0.4, 0xaf, 10.5, 100};
        Number[] arrOfNumZ = new Number[]{100, 0.4, 0xaf, 10.5, 100};

        MathBox<Number> mathBoxX = new MathBox<>(arrOfNumX);
        MathBox<Number> mathBoxY = new MathBox<>(arrOfNumY);
        MathBox<Number> mathBoxZ = new MathBox<>(arrOfNumZ);

        System.out.println("Проверка Equals на рефлексивность: " + checkEqualsReflexivity(mathBoxX));
        System.out.println("Проверка Equals на симметричность: " + checkEqualsSymmetry(mathBoxX, mathBoxY));
        System.out.println("Проверка Equals на транзитивность: " + checkEqualsTransitivity(mathBoxX, mathBoxY, mathBoxZ));
        System.out.println("Проверка Equals на консистентность: " + checkEqualsConsistency(mathBoxX, mathBoxY));
        System.out.println("Проверка Equals на сравнение с Null: " + checkEqualsCompareWithNull(mathBoxX));
        System.out.println("Проверка HashCode на консистентность: " + checkHashCodeConsistency(mathBoxX));
        System.out.println("Проверка HashCode на то, что схожие объекты имеют одинаковый хэш: " + checkHashCodesForObjects(mathBoxX, mathBoxY));

        System.out.println("Начальная коллекция: " + mathBoxY.getNumberSet());

        mathBoxY.checkDuplicatesAndDelete(NUMBER_TO_FIND_DUPLICATE);
        System.out.println("Проверка и удаление дубликата " + NUMBER_TO_FIND_DUPLICATE + ": " + mathBoxY.getNumberSet());

        System.out.println("Сумма элементов: " + mathBoxY.summator(mathBoxY.getNumberSet()));

        mathBoxY.splitter(DENOMINATOR);

        System.out.println("Поочередное деление: " + mathBoxY.getNumberSet());


//        MathBox objectMathBox = new MathBox((Number[]) new Object());
    }

    private static boolean checkHashCodesForObjects(MathBox<Number> mathBoxX, MathBox<Number> mathBoxY) {
        return mathBoxX.equals(mathBoxY) && mathBoxX.hashCode() == mathBoxY.hashCode();
    }

    private static boolean checkHashCodeConsistency(MathBox<Number> mathBoxX) {
        int hash = mathBoxX.hashCode();
        return hash == mathBoxX.hashCode();
    }

    private static boolean checkEqualsCompareWithNull(MathBox<Number> mathBoxX) {
        return mathBoxX.equals(null);
    }

    private static boolean checkEqualsConsistency(MathBox<Number> mathBoxX, MathBox<Number> mathBoxY) {
        if (mathBoxX.equals(mathBoxY)) {
            return mathBoxX.equals(mathBoxY);
        }
        return false;
    }

    private static boolean checkEqualsTransitivity(MathBox<Number> mathBoxX, MathBox<Number> mathBoxY, MathBox<Number> mathBoxZ) {
        if (mathBoxX.equals(mathBoxY) && mathBoxY.equals(mathBoxZ)) {
            return mathBoxX.equals(mathBoxZ);
        }
        return false;
    }

    private static boolean checkEqualsSymmetry(MathBox<Number> mathBox1, MathBox<Number> mathBox2) {
        if (mathBox1.equals(mathBox2)) {
            return mathBox2.equals(mathBox1);
        }
        return false;
    }

    private static boolean checkEqualsReflexivity(MathBox<Number> mathBox) {
        return mathBox.equals(mathBox);
    }
}