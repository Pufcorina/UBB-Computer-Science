package runnables;

import javafx.util.Pair;
import model.Matrix;

import javafx.util.Pair;
import model.Matrix;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixAddition extends MatrixOperation {
    private static Lock mutex = new ReentrantLock();

    public MatrixAddition(Matrix a, Matrix b, Matrix result) {
        super(a, b, result);
    }

    @Override
    public Integer call() {
        for (Pair<Integer, Integer> point : this.workload){
            mutex.lock();
            this.result.set(point.getKey(), point.getValue(), a.get(point.getKey(), point.getValue()) + b.get(point.getKey(), point.getValue()));
            mutex.unlock();
        }

        return 0;
    }

}
