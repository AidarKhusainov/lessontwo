package part1.lesson03.task01;

import part1.lesson03.task02.ObjectBox;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;


public class MathBox<E extends Number> extends ObjectBox {

    private Set<E> numberSet = new HashSet();

    public MathBox(E[] array) {
        super();
        this.numberSet.addAll(Arrays.asList(array));
    }

    public void checkDuplicatesAndDelete(Integer number) {
        this.numberSet.remove(number);
    }

    public <T extends Number> Number summator(Set<T> numberSet) {
        int sumOfInt = 0;
        double sumOfDouble = 0;

        for (T number : numberSet) {

            if (number.getClass() == Integer.class) {
                sumOfInt += number.intValue();
            } else if (number.getClass() == Double.class) {
                sumOfDouble += number.doubleValue();
            }
        }
        return sumOfInt + sumOfDouble;
    }

    public <T extends Number> void splitter(T denominator) {
        Predicate<Object> compareClass = (o) -> o.getClass() == Integer.class;

        Number[] tmpArr = this.numberSet
                .stream()
                .map(v -> (compareClass.test(v) ? v.intValue() : v.doubleValue()) /
                        (compareClass.test(denominator) ?
                                denominator.intValue() :
                                denominator.doubleValue())).toArray(Number[]::new);

        this.numberSet.clear();
        setNumberSet(tmpArr);
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
        int result = 17;
        return getNumberSet() != null ? 31 * result + getNumberSet().hashCode() : 0;
    }

    public Set<E> getNumberSet() {
        return numberSet;
    }

    public void setNumberSet(Set<E> numberSet) {
        this.numberSet = numberSet;
    }

    private void setNumberSet(Number[] numbers) {
        this.numberSet.addAll(Arrays.asList((E[]) numbers));
    }
}