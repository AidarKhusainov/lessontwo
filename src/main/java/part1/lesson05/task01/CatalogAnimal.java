package part1.lesson05.task01;

import part1.lesson02.task01.MyException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Картотека домашних животных
 *
 * @param <E>
 * @param <T>
 * @param <S>
 */
public class CatalogAnimal<E extends Map<Integer, Animal>, T, S extends Animal> extends Catalog<E, T, S> {
    private Map<Integer, Animal> pet = new HashMap<>();

    public Map<Integer, Animal> getPet() {
        return pet;
    }

    private void setPet(S pet) {
        this.pet.put(pet.getId(), pet);
    }

    public S getCardById(T idCard) {
        return (S) this.pet.get(idCard);
    }

    @Override
    public void addCardToCatalog(S data) throws MyException {
        if (this.pet.get(data.getId()) != null) {
            throw new MyException("Найден дубликат");
        }
        setPet(data);
    }

    @Override
    public void modifiedDataById(S data) {
        S animal = getCardById((T) (Integer) data.getId());
        Nameable nameAnimal = pet.get(animal.getId());

        nameAnimal.setName(data.getName());
        this.pet.get(animal.getId()).setId(data.getId());
        this.pet.get(animal.getId()).setWeight(data.getWeight());
        this.pet.get(animal.getId()).setOwner(data.getOwner());
    }

    public void printAnimalToConsole() {
        sortPet(this.pet).forEach((key, val) -> {
            System.out.println(key + " = " + val.toString());
        });
    }

    public Map.Entry<Integer, Animal> getPetByName(String petName) {
        Map.Entry<Integer, Animal> animalTmp = pet.entrySet()
                .stream()
                .filter(e -> e.getValue().getName() == petName).findFirst().get();

        System.out.println(animalTmp);
        return animalTmp;
    }

    private Map<Integer, Animal> sortPet(Map<Integer, Animal> pet) {
        AnimalComparator animalComparator = new AnimalComparator();

        return pet
                .entrySet()
                .stream()
                .sorted((e1, e2) -> animalComparator.compare(e1.getValue(), e2.getValue()))
                .collect(Collectors
                        .toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
