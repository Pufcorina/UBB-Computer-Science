package algorithms.binaryTreeAlgorithm;

import sun.awt.Mutex;

import java.util.ArrayList;
import java.util.List;

public class Vector {
    private List<Integer> content;
    private List<Mutex> mutexes;

    Vector(List<Integer> content) {
        this.content = new ArrayList<>();

        this.mutexes = new ArrayList<>();

        for (int item: content) {
            this.content.add(item);
            mutexes.add(new Mutex());
        }
    }

    Integer get(int index){
        return this.content.get(index);
    }

    void add(int index, int value){
        mutexes.get(index).lock();
        this.content.set(index, this.content.get(index) + value);
        mutexes.get(index).unlock();
    }

    int size() {
        return this.content.size();
    }

    List<Integer> asList(){
        return this.content;
    }

    @Override
    public String toString() {
        return this.content.toString();
    }

}