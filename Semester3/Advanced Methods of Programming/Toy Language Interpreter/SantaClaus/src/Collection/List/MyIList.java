package Collection.List;

public interface MyIList<T> {
    int size();
    boolean isEmpty();
    boolean add(T e);
    void clear();
    T get(int index);
    String toString();
}
 