import model.Matrix;
import model.MatrixOperation;
import threads.MatrixAdditionThread;
import threads.MatrixMultiplicationThread;
import threads.MatrixOperationThread;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

public class Main {

    private static Matrix operation(MatrixOperation op, Matrix a, Matrix b, int threadCount) throws Exception {
        int rowCount = min(a.getRowsNumber(), b.getRowsNumber());
        int colCount = min(a.getColsNumber(), b.getColsNumber());

        Matrix result = new Matrix(rowCount, colCount);

        List<MatrixOperationThread> threads = new ArrayList<>();

        switch (op){
            case ADD:
                for (int i = 0; i < threadCount; i++){
                    threads.add(new MatrixAdditionThread(a, b, result));
                }
                break;
            case MULTIPLY:
                for (int i = 0; i < threadCount; i++){
                    threads.add(new MatrixMultiplicationThread(a, b, result));
                }
                break;
            default:
                throw new Exception("MatrixOperation not recognizqd");
        }

        for (int row = 0; row < result.getRowsNumber(); row++){
            for(int col = 0; col < result.getRowsNumber(); col++){
                threads.get(result.index(row, col) % threadCount).addPointToWorkload(row, col);
            }
        }

        for (int i = 0; i < threadCount; i++){
            threads.get(i).start();
        }

        for (int i = 0; i < threadCount; i++){
            threads.get(i).join();
        }
        return result;
    }


    public static void main(String[] args) throws Exception {

        Matrix a = new Matrix(500, 500);
        Matrix b = new Matrix(500, 500);

        float start =  System.nanoTime() / 1000000;
        operation(MatrixOperation.MULTIPLY, a, b, 4);
        float end = System.nanoTime() / 1000000;

        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds, " + 4 + " threads");

        start =  System.nanoTime() / 1000000;
        operation(MatrixOperation.ADD, a, b, 4);
        end = System.nanoTime() / 1000000;

        System.out.println(" End work addition:" + (end - start) / 1000 + " seconds, " + 4 + " threads");


        start =  System.nanoTime() / 1000000;
        operation(MatrixOperation.MULTIPLY, a, b, 4);
        end = System.nanoTime() / 1000000;

        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds, " + 8 + " threads");

        start =  System.nanoTime() / 1000000;
        operation(MatrixOperation.ADD, a, b, 4);
        end = System.nanoTime() / 1000000;

        System.out.println(" End work addition:" + (end - start) / 1000 + " seconds, " + 8 + " threads");


        start =  System.nanoTime() / 1000000;
        operation(MatrixOperation.MULTIPLY, a, b, 4);
        end = System.nanoTime() / 1000000;

        System.out.println("\n End work multiplication: " + (end - start) / 1000 + " seconds, " + 16 + " threads");

        start =  System.nanoTime() / 1000000;
        operation(MatrixOperation.ADD, a, b, 4);
        end = System.nanoTime() / 1000000;

        System.out.println(" End work addition: " + (end - start) / 1000 + " seconds, " + 16 + " threads");
    }
}
