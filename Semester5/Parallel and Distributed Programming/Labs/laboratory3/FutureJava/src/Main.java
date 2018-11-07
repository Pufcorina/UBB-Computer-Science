import model.Matrix;
import model.MatrixOperationEnum;
import runnables.MatrixAddition;
import runnables.MatrixMultiplication;
import runnables.MatrixOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.FutureTask;

import static java.lang.Math.min;

public class Main {

    private static Matrix operation(MatrixOperationEnum op, Matrix a, Matrix b, int taskCount) throws Exception {
        int rowCount = min(a.getRowsNumber(), b.getRowsNumber());
        int colCount = min(a.getColsNumber(), b.getColsNumber());

        Matrix sum = new Matrix(rowCount, colCount);

        List<MatrixOperation> callables = new ArrayList<>();

        switch (op){
            case ADD:
                for (int i = 0; i < taskCount; i++){
                    callables.add(new MatrixAddition(a, b, sum));
                }
                break;
            case MULTIPLY:
                for (int i = 0; i < taskCount; i++){
                    callables.add(new MatrixMultiplication(a, b, sum));
                }
                break;
            default:
                throw new Exception("MatrixOperationEnum not recognizqd");
        }

        for (int row = 0; row < sum.getRowsNumber(); row++){
            for(int col = 0; col < sum.getRowsNumber(); col++){
                callables.get(sum.index(row, col) % taskCount).addPointToWorkload(row, col);
            }
        }

        List<FutureTask> tasks = new ArrayList<>();

        for (MatrixOperation callable : callables) {
            tasks.add(new FutureTask(callable));
        }

        for (FutureTask task : tasks) {
            Thread thread = new Thread(task);
            thread.start();
        }


        for (FutureTask task : tasks) {
            task.get();
        }


        return sum;
    }

    public static void main(String[] args) throws Exception {

        Matrix a = new Matrix(500, 500);
        Matrix b = new Matrix(500, 500);

        float start =  System.nanoTime() / 1000000;
        operation(MatrixOperationEnum.MULTIPLY, a, b, 8);
        float end = System.nanoTime() / 1000000;

        System.out.println(end - start);
    }
}