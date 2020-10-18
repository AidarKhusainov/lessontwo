package part1.lesson03.task01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class MathBox {

    private Set<Number> numberSet = new HashSet<Number>();

    MathBox(Number[] array) {
        this.numberSet.addAll(Arrays.asList(array));

    }

    public void checkDuplicatesAndDelete(Integer number) {
        if (this.numberSet.contains(number)) this.numberSet.remove(number);
    }

    public <T extends Number> Number summator(Set<Number> numberSet) {
        Number sum = 0;
        for (Number number : numberSet) {
//            sum += number;
        }
        return sum;
    }


    @Override
    public String toString() {
        return "MathBox{" +
                "numberSet=" + numberSet +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MathBox)) return false;

        MathBox mathBox = (MathBox) o;

        return getNumberSet() != null ? getNumberSet().equals(mathBox.getNumberSet()) : mathBox.getNumberSet() == null;
    }

    @Override
    public int hashCode() {
        return getNumberSet() != null ? getNumberSet().hashCode() : 0;
    }

    public Set<Number> getNumberSet() {
        return numberSet;
    }

    public void setNumberSet(Set<Number> numberSet) {
        this.numberSet = numberSet;
    }
}