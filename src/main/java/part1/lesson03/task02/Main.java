package part1.lesson03.task02;

import part1.lesson03.task01.MathBox;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Number[] arrOfNumX = new Number[]{102, 0.5, 0xaf, 10.20};
        Number[] arrOfNumY = new Number[]{100, 0.4, 0xaf, 10.10};
        Number[] arrOfNumZ = new Number[]{103, 0.6, 0xaf, 10.30};

        MathBox<Number> mathBoxX = new MathBox<>(arrOfNumX);
        MathBox<Number> mathBoxY = new MathBox<>(arrOfNumY);
        MathBox<Number> mathBoxZ = new MathBox<>(arrOfNumZ);

        ObjectBox<Object> objectBox = new ObjectBox<>(mathBoxX);

        objectBox.addObject(mathBoxX);
        objectBox.addObject(mathBoxY);
        objectBox.addObject(mathBoxZ);

        objectBox.dump();
        objectBox.deleteObject(mathBoxX);
        objectBox.dump();
    }
}
