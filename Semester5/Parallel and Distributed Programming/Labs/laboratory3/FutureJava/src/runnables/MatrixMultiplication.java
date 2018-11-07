package runnables;

import javafx.util.Pair;
import model.Matrix;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixMultiplication extends MatrixOperation{
    private static Lock mutex = new ReentrantLock();

    public MatrixMultiplication(Matrix a, Matrix b, Matrix result) {
        super(a, b, result);
    }


    @Override
    public Integer call() {
        for (Pair<Integer, Integer> point : this.workload){
            int result = 0;
            for (int i = 0; i < this.result.getRowsNumber(); i++){
                result += (a.get(point.getKey(), i) * b.get(i, point.getValue()));
            }

            mutex.lock();
            this.result.set(point.getKey(), point.getValue(), result);
            mutex.unlock();
        }

        return 0;
    }
}
