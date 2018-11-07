import model.Matrix;
import model.MatrixOperationEnum;
import runnables.MatrixAddition;
import runnables.MatrixMultiplication;
import runnables.MatrixOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Math.min;

public class Main {

    private static Matrix operation(MatrixOperationEnum op, Matrix a, Matrix b, int taskCount, int threadCount) throws Exception {
        int rowCount = min(a.getRowsNumber(), b.getRowsNumber());
        int colCount = min(a.getColsNumber(), b.getColsNumber());

        Matrix sum = new Matrix(rowCount, colCount);

        List<MatrixOperation> tasks = new ArrayList<>();

        switch (op){
            case ADD:
                for (int i = 0; i < taskCount; i++){
                    tasks.add(new MatrixAddition(a, b, sum));
                }
                break;
            case MULTIPLY:
                for (int i = 0; i < taskCount; i++){
                    tasks.add(new MatrixMultiplication(a, b, sum));
                }
                break;
            default:
                throw new Exception("MatrixOperationEnum not recognizqd");
        }

        for (int row = 0; row < sum.getRowsNumber(); row++){
            for(int col = 0; col < sum.getRowsNumber(); col++){
                tasks.get(sum.index(row, col) % taskCount).addPointToWorkload(row, col);
            }
        }

        ExecutorService pool = Executors.newFixedThreadPool(threadCount);

        for (MatrixOperation task : tasks){
            pool.execute(task);
        }

        pool.shutdown();

        return sum;
    }


    public static void main(String[] args) throws Exception {

        Matrix a = new Matrix(500, 500);
        Matrix b = new Matrix(500, 500);

        float start =  System.nanoTime() / 1000000;
        operation(MatrixOperationEnum.ADD, a, b, 99, 4);
        float end = System.nanoTime() / 1000000;

        System.out.println(end - start);
    }
}
