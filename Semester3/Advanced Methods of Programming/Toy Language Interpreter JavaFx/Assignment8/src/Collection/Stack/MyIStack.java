package Collection.Stack;

import java.util.List;

public interface MyIStack<T> {
    T pop();
    void push(T v);
    boolean isEmpty();
    String toString();

    List<T> getAll();
}
