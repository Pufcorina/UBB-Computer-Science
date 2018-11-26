
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

public class Main {
    private static int[][] matrix_a = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    private static int[][] matrix_b = {
            {0, 7, 5},
            {2, 6, 1},
            {0, 8, 1}
    };
    private static int[][] matrix_c = {
            {2, 9, 7},
            {0, 6, 1},
            {3, 0, 6}
    };
    private static int[][] matrix_prod_ab = new int[matrix_a[0].length][matrix_b.length];
    private static int[][] matrix_prod_abc = new int[matrix_a[0].length][matrix_b.length];

    private static final Lock lock = new ReentrantLock();

    private static final Condition rowDone = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        //start timer
        long startTime;
        long stopTime;
        long elapsedTime;

        startTime = System.currentTimeMillis();
        //Create all threads for product
        ExecutorService executorServiceAB = Executors.newFixedThreadPool(6);
        for (int rows = 0; rows < matrix_a.length; rows++) {
            Main.MatrixProdAB thr = new MatrixProdAB(rows);
            executorServiceAB.submit(thr);
        }


        ExecutorService executorServiceABC = Executors.newFixedThreadPool(6);
        for (int rows = 0; rows < matrix_a.length; rows++) {
            Main.MatrixProdABC thr = new MatrixProdABC(rows);
            executorServiceABC.submit(thr);
        }


        if (!executorServiceAB.awaitTermination(10, TimeUnit.MILLISECONDS))
            executorServiceAB.shutdown();
        if (!executorServiceABC.awaitTermination(10, TimeUnit.MILLISECONDS))
            executorServiceABC.shutdown();


        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Elapsed time for product computation: " + elapsedTime);



        System.out.println("\nA*B");
        for (int i = 0; i < matrix_prod_ab.length; i++) {
            for (int j = 0; j < matrix_prod_ab[0].length; j++) {
                System.out.print(matrix_prod_ab[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("\nA*B*C");
        for (int i = 0; i < matrix_prod_abc.length; i++) {
            for (int j = 0; j < matrix_prod_abc[0].length; j++) {
                System.out.print(matrix_prod_abc[i][j]+" ");
            }
            System.out.println();
        }
    }

    static class MatrixProdAB extends Thread {
        int row;

        MatrixProdAB(int row) {
            this.row = row;
        }

        public void run() {
            lock.lock();

            for (int j = 0; j < matrix_b[row].length; j++) { // bColumn
                for (int k = 0; k < matrix_a[row].length; k++) { // aColumn
                    matrix_prod_ab[row][j] += matrix_a[row][k] * matrix_b[k][j];
                }
            }
            rowDone.signal();
            lock.unlock();
        }
    }
    static class MatrixProdABC extends Thread {
        int row;

        MatrixProdABC(int row) {
            this.row = row;
        }

        public void run() {
            lock.lock();

            try {
                while (!isFilledRow(matrix_prod_ab, row)) {
                    rowDone.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < matrix_prod_ab[row].length; j++) { // bColumn
                for (int k = 0; k < matrix_c[row].length; k++) { // aColumn
                    matrix_prod_abc[row][j] += matrix_prod_ab[row][k] * matrix_c[k][j];
                }
            }
            lock.unlock();
        }
    }

    private static boolean isFilledRow(int[][] mat, int row) {
        for (int i = 0; i < mat.length; i++) {
            if (mat[row][i] == 0) return false;
        }
        return true;
    }


}
