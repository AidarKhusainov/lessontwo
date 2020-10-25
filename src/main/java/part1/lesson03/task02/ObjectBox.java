package part1.lesson03.task02;

import java.util.*;

public class ObjectBox<T> {
    private Set<T> object = new HashSet<>();

    ObjectBox(T object) {
        this.object.add(object);
    }

    public ObjectBox() {
        this.object = null;
    }

    public void addObject(T obj) {
        this.object.add(obj);
    }

    public void deleteObject(T obj) {
        this.object.remove(obj);
    }

    public void dump() {
        System.out.println(this.object);
    }
}
