import algorithms.Algorithm;
import algorithms.NormalPrefixes;
import algorithms.binaryTreeAlgorithm.BinaryTreePrefixes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int TEST_NR = 100;
        Algorithm NORMAL_PREFIXES = new NormalPrefixes();
        Algorithm LOGARITHMIC_PREFIXES = new BinaryTreePrefixes();

        List<Integer> input = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        List<Integer> result = LOGARITHMIC_PREFIXES.getPrefixes(input, 1);

        System.out.println("input              = " + input);
        System.out.println("result             = " + result);


        List<List<Integer>> inputs = new ArrayList();

        for (int i = 1; i <= TEST_NR; i++){
            inputs.add(getRandomList(i * 100, 100, 900));
        }

        System.out.println("\nNormal O(N) algorithm");
        batchTesting(inputs, 1, NORMAL_PREFIXES);

        System.out.println("\nLogarithmic algorithm - sequential");
        batchTesting(inputs, 1, LOGARITHMIC_PREFIXES);

        System.out.println("\nLogarithmic algorithm - parallelized");
        batchTesting(inputs, 4, LOGARITHMIC_PREFIXES);

    }


    private static void batchTesting(List<List<Integer>> inputs, int threadCount, Algorithm algorithm){
        for (int i = 0; i < inputs.size(); i++){
            test(i, inputs.get(i), threadCount, algorithm);
        }
    }

    private static void test(int level, List<Integer> input, int threadCount, Algorithm algorithm) {
        long startTime = System.nanoTime();
        algorithm.getPrefixes(input, threadCount);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        if (level == 0 || level == 49 || level == 99)
            System.out.println("Level " + level + ": " + duration + " ms");
    }

    private static List<Integer> getRandomList(int size, int min, int max) {

        List<Integer> result = new ArrayList<>(size);

        Random random = new Random();

        for (int i = 0; i < size; i++) {
            result.add(random.nextInt(max) + min);
        }

        return result;
    }
}