package part1.lesson05.task01;

import part1.lesson02.task03.person.Person;

import java.util.Objects;

/**
 * Клас описывает животных.
 * У каждого животного есть
 * уникальный идентификационный номер,
 * кличка,
 * хозяин (объект класс Person с полями – имя, возраст, пол),
 * вес
 */
public class Animal implements Nameable {
    private int id;
    private String name;
    private Person owner;
    private int weight;

    public Animal(int id, String name, Person owner, int weight) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.weight = weight;
    }

    public Animal() {
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return id == animal.id &&
                weight == animal.weight &&
                Objects.equals(name, animal.name) &&
                Objects.equals(owner, animal.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, owner, weight);
    }
}