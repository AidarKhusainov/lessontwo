package part1.lesson05.task01;

import part1.lesson02.task01.MyException;

/**
 * Абстрактный класс описывает картотеку.
 *
 * @param <E>
 * @param <T>
 * @param <S>
 */
public abstract class Catalog<E, T, S> {
    /**
     * Получение карточки из картотеки по идентификатору
     *
     * @param idCard Идентификатор карточки в картотеке
     * @return возвращает карточку из картотеки
     */
    public abstract S getCardById(T idCard);

    /**
     * Добавление карточки в картотеку
     *
     * @param data
     * @throws MyException
     */
    public abstract void addCardToCatalog(S data) throws MyException;

    /**
     * Изменение карточки по идентификатору
     *
     * @param data карточка с ИД, которую нужно изменить
     */
    public abstract void modifiedDataById(S data);
}