package myCollections;

import java.util.ArrayList;

public class MyList<E> implements MyIList<E> {
    ArrayList<E> list;

    public MyList() {list = new ArrayList<>();}

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean add(E element) {
        return list.add(element);
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public String toString()
    {

        StringBuilder buffer = new StringBuilder();

        for(E element : list)
            buffer.append(element.toString()).append(" | ");
        return buffer.toString();
    }
}
