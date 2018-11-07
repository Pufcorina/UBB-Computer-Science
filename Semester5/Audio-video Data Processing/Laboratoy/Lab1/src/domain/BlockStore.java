package domain;

import java.util.ArrayList;
import java.util.List;

public class BlockStore {
    private int size;
    private double[][] store;
    private String storeType;
    private int position;


    public BlockStore(int size, String storeType, int position) {
        this.size = size;
        this.storeType = storeType;
        this.position = position;

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

    public double[][] getStore() {
        return store;
    }

    public String getStoreType() {
        return storeType;
    }

    public int getPosition() {
        return position;
    }
}
