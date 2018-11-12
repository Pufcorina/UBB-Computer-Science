import model.Matrix;
import runnables.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main {
    public static void main(String[] args) {
        try{
            Matrix A = new Matrix(3, 3, 1, 5);

            A.setRow(0, Arrays.asList(1, 2, 3));
            A.setRow(1, Arrays.asList(4, 5, 6));
            A.setRow(2, Arrays.asList(7, 8, 9));

            Matrix B = new Matrix(3, 3, 3, 6);

            B.setRow(0, Arrays.asList(0, 7, 5));
            B.setRow(1, Arrays.asList(2, 6, 1));
            B.setRow(2, Arrays.asList(0, 8, 1));

            Matrix C = new Matrix(3, 3, 1, 3);

            C.setRow(0, Arrays.asList(2, 9, 7));
            C.setRow(1, Arrays.asList(0, 6, 1));
            C.setRow(2, Arrays.asList(3, 0, 6));

            float start = System.nanoTime() /1000000;

            Matrix result = matrixMultiply(Arrays.asList(A, B, C), 8);

            float finish = System.nanoTime() /1000000;

            System.out.println("Seconds: " + (finish - start));

            System.out.println(result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static Matrix matrixMultiply(List<Matrix> matrices, int threadCount) {
        int rowCount = getFinalMatrixRowCount(matrices);
        int columnCount = getFinalMatrixColumnCount(matrices);

        List<Matrix> results = new ArrayList<>();
        results.add(matrices.get(0));

        while (results.size() < matrices.size())
            results.add(new Matrix(rowCount, columnCount, 0, 0));

        ConcurrentLinkedQueue<Task> backlog = new ConcurrentLinkedQueue<>();

        for (int )

        return null;
    }

    private static int getFinalMatrixRowCount(List<Matrix> matrices) {
        int row = matrices.get(0).getRowsNumber();

        for (Matrix m: matrices)
            row = Math.min(m.getRowsNumber(), row);

        return row;
    }

    private static int getFinalMatrixColumnCount(List<Matrix> matrices) {
        int column = matrices.get(0).getColsNumber();

        for (Matrix m: matrices)
            column = Math.min(m.getColsNumber(), column);

        return column;
    }
}
