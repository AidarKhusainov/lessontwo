package part1.lesson03.task01;


public class Main {
    public static void main(String[] args) {

        Number[] arrNum = new Number[]{1, 2.55, 0xaf};
        MathBox mathBox = new MathBox(arrNum);

        mathBox.checkDuplicatesAndDelete(1);
        System.out.println(mathBox.getNumberSet());

        System.out.println(mathBox.summator(mathBox.getNumberSet()));


//        System.out.println(0xaf + ((Integer) 0xaf) + 1 + 1.5);
    }
}