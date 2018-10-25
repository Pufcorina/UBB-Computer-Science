package threads;

import javafx.util.Pair;
import model.Matrix;

import java.util.ArrayList;
import java.util.List;

public class MatrixOperationThread extends Thread {

    Matrix a;
    Matrix b;
    Matrix result;
    List<Pair<Integer, Integer>> workload;

    MatrixOperationThread(Matrix a, Matrix b, Matrix result) {
        this.a = a;
        this.b = b;
        this.result = result;
        workload = new ArrayList<>();
    }

    public void addPointToWorkload(int row, int col){
        this.workload.add(new Pair<>(row, col));
    }

}
