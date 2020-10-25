package part1.lesson02.task03.person;

import part1.lesson02.task03.Sex;

import java.util.Objects;

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

    public static void printPersonToConsole(String message, Person[] persons) {
        System.out.println(message);
        for (Person person : persons)
            System.out.println(person);
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sex=" + sex +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                sex == person.sex &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, sex, name);
    }
}











