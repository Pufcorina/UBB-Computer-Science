package myCollections;

import java.util.List;

public interface MyIList<E>{
    int size();
    boolean isEmpty();
    boolean add(E element);
    E get(int index);
    String toString();
}
