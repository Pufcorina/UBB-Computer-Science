package domain;

import java.util.ArrayList;
import java.util.List;

public class BlockStore {
    private int size;
    private double[][] store;
    private double[][] gStore;
    private String storeType;


    BlockStore(int size, String storeType) {
        this.size = size;
        this.storeType = storeType;

        store = new double[size][size];
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                msg.append((int) store[i][j]).append(" ");
            }
            msg.append("\n");
        }
        return msg.toString();
    }

    public int getSize() {
        return size;
    }

    double[][] getStore() {
        return store;
    }

    String getStoreType() {
        return storeType;
    }

    void setStore(double[][] matrix) {
        store = matrix;
    }

    public double[][] getgStore() {
        return gStore;
    }

    public void setgStore(double[][] gStore) {
        this.gStore = gStore;
    }
}
