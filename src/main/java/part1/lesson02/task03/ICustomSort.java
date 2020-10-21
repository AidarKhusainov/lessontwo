package part1.lesson02.task03;

import part1.lesson02.task03.person.Person;


public interface ICustomSort { /*перед интерфейсами можно не использовать букву указывающая что это интерфейс.
                                подробнее в книге Мартин Фаулер - "Чистый код", в пункте "Интерфейсы и реализация" */
    void customSort(Person[] persons);
}

