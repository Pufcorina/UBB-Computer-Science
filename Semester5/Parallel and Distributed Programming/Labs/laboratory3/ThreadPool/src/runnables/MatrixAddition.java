package runnables;

import javafx.util.Pair;
import model.Matrix;

public class MatrixAddition extends MatrixOperation {
    public MatrixAddition(Matrix a, Matrix b, Matrix result) {
        super(a, b, result);
    }

    @Override
    public void run() {
        for (Pair<Integer, Integer> point : this.workload){
            this.result.set(point.getKey(), point.getValue(), a.get(point.getKey(), point.getValue()) + b.get(point.getKey(), point.getValue()));
        }
    }

}
