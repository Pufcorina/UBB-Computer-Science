import model.Bill;
import model.Inventory;
import model.Product;
import model.Transaction;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
    1000 of records


    Threads:
        5 -> 0.08, 0.64 seconds average: 0.072
        500 -> 0.176 seconds
        1000 -> 0.208 seconds
 */

public class Main {
    private static final int NUMBER__OF_THREADS = 5;
    private static Inventory deposit = new Inventory();
    private static List<Product> products = new ArrayList<>();
    private static List<Bill> recordOfSales = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {
        WriteInFile();
        ReadFromFile();


        float start =  System.nanoTime() / 1000000;
        for (int i = 0; i < NUMBER__OF_THREADS; i++) {
            Transaction t = new Transaction(deposit, "t" + i);
            int product = new Random().nextInt(10);
            if (product < 0 ) product *= -1;
            for (int j = 0; j < product; j++) {
                int quantity = new Random().nextInt(10);
                if (quantity < 0 ) quantity *= -1;
                int productId = new Random().nextInt(products.size());
                if (productId < 0 ) productId *= -1;
                t.add(products.get(productId), quantity);
            }
            transactions.add(t);
        }


        List<Thread> threads = new ArrayList<>();

        transactions.stream().forEach(t -> threads.add(new Thread(t)));

        for (Thread thread : threads){
            thread.start();
        }

        for (Thread thread : threads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

        verify();

        float end = System.nanoTime() / 1000000;
        System.out.println("\n End work: " + (end - start) / 1000 + " seconds");



    }

    static void verify() {
        System.err.println("Verifying the stock...");

        int expectedSum = 0;
        double sum = recordOfSales.stream().mapToDouble(i -> i.getProducts().stream().mapToDouble(j -> j.getPrice()).sum()).sum();
        if(transactions.stream().mapToDouble(i ->{
            if (i == null)
                return 0.0f;
            else
                return i.getTotalPrice();
        }).sum() == sum) {
            System.err.println("Stock verification failed!");
        }
        else {
            System.err.println("Verification Successful!");
        }
    }

    private static void WriteInFile() {
        int i = 0;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\todor\\OneDrive\\Documents\\GitHub\\UBB\\Semester5\\Parallel and Distributed Programming\\Labs\\laboratory1\\src\\data\\products.txt"));
            while ( i < 1000 ) {
                Random r = new Random();
                Integer quantity = r.nextInt();
                if (quantity < 0)
                    quantity =  (quantity * -1) % 100;
                else
                    quantity %= 100;
                String s = new RandomString().generateRandomString() +  ' ' + r.nextDouble() + ' ' +  quantity + '\n';
                writer.write(s);
                i += 1;
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void ReadFromFile() {
        File file = new File("C:\\Users\\todor\\OneDrive\\Documents\\GitHub\\UBB\\Semester5\\Parallel and Distributed Programming\\Labs\\laboratory1\\src\\data\\products.txt");
        try {
            Scanner sc = new Scanner(file);
            while(sc.hasNext()){
                Product p = new Product(sc.next(), sc.nextFloat());
                products.add(p);
                deposit.add(p, sc.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}

class RandomString {
    private static final String CHAR_LIST =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;

    String generateRandomString(){
        StringBuilder randStr = new StringBuilder();
        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
            int number = getRandomNumber();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }
    private int getRandomNumber() {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
        if (randomInt - 1 == -1) {
            return randomInt;
        } else {
            return randomInt - 1;
        }
    }
}
