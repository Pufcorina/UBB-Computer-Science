package myCollections;

import javafx.util.Pair;

import java.util.List;

public interface MyISemaphore {
    void setSemaphore(MyIDictionary<Integer, Pair<Integer, List<Integer>>> semaphore);
    MyIDictionary<Integer, Pair<Integer, List<Integer>>> getSemaphore();
    Integer getSemaphorAddress();

    void put(Integer foundIndex, Pair<Integer, List<Integer>> integerListPair);
}
