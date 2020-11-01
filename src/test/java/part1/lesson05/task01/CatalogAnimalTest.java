package part1.lesson05.task01;

import org.junit.jupiter.api.Test;
import part1.lesson02.task01.MyException;
import part1.lesson02.task03.Sex;
import part1.lesson02.task03.person.Person;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CatalogAnimalTest {

    @Test
    void printAnimalToConsole() throws MyException {
        CatalogAnimal<Map<Integer, Animal>, Integer, Animal> printAnimal = new CatalogAnimal<>();
        fillingCatalogAnimal(printAnimal);

        printAnimal.printAnimalToConsole();

    }

    @Test
    void getPetByNameTest() throws MyException {
        CatalogAnimal<Map<Integer, Animal>, Integer, Animal> catalogAnimal = new CatalogAnimal<>();
        fillingCatalogAnimal(catalogAnimal);

        Map.Entry<Integer, Animal> animalGetName = catalogAnimal.getPetByName("Rex2");

        assertEquals(new Animal(2, "Rex2", new Person(30, "Ivan", Sex.WOMAN), 15), animalGetName.getValue());
    }

    @Test
    void addCardToCatalog() throws MyException {

        boolean isException = false;
        CatalogAnimal<Map<Integer, Animal>, Integer, Animal> correctCatalogAnimal = new CatalogAnimal<>();
        CatalogAnimal<Map<Integer, Animal>, Integer, Animal> addNewCardAnimalCatalog = new CatalogAnimal<>();

        correctCatalogAnimal
                .getPet()
                .put(4, new Animal(4, "Sharik", new Person(50, "Vova", Sex.MAN), 30));


        addNewCardAnimalCatalog
                .addCardToCatalog(new Animal(4, "Sharik", new Person(50, "Vova", Sex.MAN), 30));

        try {
            addNewCardAnimalCatalog
                    .addCardToCatalog(new Animal(4, "Sharik", new Person(50, "Vova", Sex.MAN), 30));
        } catch (MyException e) {
            isException = true;
            assertNotNull(e);
            System.out.println("Test");
        }
        if (!isException) {
            fail("Дубликат не найден!");
        }
    }

    @Test
    void modifiedDataById() throws MyException {
        CatalogAnimal<Map<Integer, Animal>, Integer, Animal> correctCatalogAnimal = new CatalogAnimal<>();
        CatalogAnimal<Map<Integer, Animal>, Integer, Animal> modifiedAnimalCatalog = new CatalogAnimal<>();

        fillingCatalogAnimal(correctCatalogAnimal);
        getModifiedAnimalCatalog(modifiedAnimalCatalog);

        correctCatalogAnimal.modifiedDataById(
                new Animal(1, "May", new Person(99, "Olga", Sex.WOMAN), 88));

        assertEquals(correctCatalogAnimal.getPet(), modifiedAnimalCatalog.getPet());
    }

    private void fillingCatalogAnimal(CatalogAnimal<Map<Integer, Animal>, Integer, Animal> correctCatalogAnimal) throws MyException {
        Animal[] animals = new Animal[]{
                new Animal(1, "Rex1", new Person(50, "Pola", Sex.WOMAN), 80),
                new Animal(3, "Rex3", new Person(40, "Alex", Sex.MAN), 20),
                new Animal(4, "Rex4", new Person(30, "Ivan", Sex.WOMAN), 15),
                new Animal(2, "Rex2", new Person(30, "Ivan", Sex.WOMAN), 15),
        };

        for (int i = 0; i < animals.length; i++) {
            correctCatalogAnimal.addCardToCatalog(animals[i]);
        }
    }

    private void getModifiedAnimalCatalog(CatalogAnimal<Map<Integer, Animal>, Integer, Animal> modifiedAnimalCatalog) {
        Animal[] animals = new Animal[]{
                new Animal(1, "May", new Person(99, "Olga", Sex.WOMAN), 88),
                new Animal(3, "Rex3", new Person(40, "Alex", Sex.MAN), 20),
                new Animal(4, "Rex4", new Person(30, "Ivan", Sex.WOMAN), 15),
                new Animal(2, "Rex2", new Person(30, "Ivan", Sex.WOMAN), 15),
        };

        for (int i = 0; i < animals.length; i++) {
            modifiedAnimalCatalog.getPet().put(animals[i].getId(), animals[i]);
        }
    }

    private void sortedCatalogAnimal(CatalogAnimal<Map<Integer, Animal>, Integer, Animal> correctCatalogAnimal) throws MyException {
        Animal[] animals = new Animal[]{
                new Animal(2, "Rex2", new Person(30, "Ivan", Sex.WOMAN), 15),
                new Animal(4, "Rex4", new Person(30, "Ivan", Sex.WOMAN), 15),
                new Animal(3, "Rex3", new Person(40, "Alex", Sex.MAN), 20),
                new Animal(1, "Rex1", new Person(50, "Pola", Sex.WOMAN), 80),
        };

        for (int i = 0; i < animals.length; i++) {
            correctCatalogAnimal.addCardToCatalog(animals[i]);
        }
    }
}