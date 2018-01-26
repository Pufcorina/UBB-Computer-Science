package myCollections;

import java.util.List;

public interface MyIStack<E> {
    E pop();
    void push(E element);
    boolean isEmpty();

    List<E> getAll();
}
