package algorithms.binaryTreeAlgorithm;

import algorithms.Algorithm;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BinaryTreePrefixes implements Algorithm {

    @Override
    public List<Integer> getPrefixes(List<Integer> input, int threadCount) {

        Vector result = new Vector(input);
        ExecutorService pool;

        int k;
        for(k = 1; k < result.size(); k *= 2) {
            pool = Executors.newFixedThreadPool(threadCount);

            for(int i=2*k; i<result.size() ; i+=2*k) {
                pool.execute(new Task(result, k, i));
            }

            pool.shutdown();

            try {
                pool.awaitTermination(100, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        for(k = k/4; k>0 ; k = k/2) {
            pool = Executors.newFixedThreadPool(threadCount);

            for(int i=3*k ; i<result.size() ; i+=2*k) {
                pool.execute(new Task(result, k, i));
            }

            pool.shutdown();

            try {
                pool.awaitTermination(100, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result.asList();
    }
}