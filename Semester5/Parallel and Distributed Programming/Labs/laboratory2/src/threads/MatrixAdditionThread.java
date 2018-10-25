package threads;

import javafx.util.Pair;
import model.Matrix;

public class MatrixAdditionThread extends MatrixOperationThread {
    public MatrixAdditionThread(Matrix a, Matrix b, Matrix sum) {
        super(a, b, sum);
    }

    @Override
    public void run() {
        for (Pair<Integer, Integer> point : this.workload){
            this.result.set(point.getKey(), point.getValue(), a.get(point.getKey(), point.getValue()) + b.get(point.getKey(), point.getValue()));
        }
    }

}
