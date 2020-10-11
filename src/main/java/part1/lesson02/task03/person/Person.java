package part1.lesson02.task03.person;

import part1.lesson02.task03.Sex;

/**
 * Класс Person характеризуется полями:
 * age (возраст, целое число 0-100)
 * sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN)
 * name (имя - строка).
 *
 * @author Aidar
 */
public class Person implements Comparable<Person> {
    private int age;
    private Sex sex;
    private String name;

    public Person(int age, String name, Sex sex) {
        super();
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sex getSex() {
        return this.sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int compareTo(Person o) {
        return Integer.compare(age, o.age);
    }
}
