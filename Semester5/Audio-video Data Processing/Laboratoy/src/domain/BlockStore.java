package domain;

public class BlockStore {
    private int size;
    private int[][] gStore;
    private double[][] store;
    private String storeType;


    BlockStore(int size, String storeType) {
        this.size = size;
        this.storeType = storeType;

        store = new double[size][size];
        gStore = new int[size][size];
    }

    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                msg.append((int) gStore[i][j]).append(" ");
            }
            msg.append("\n");
        }
        return msg.toString();
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

    public int[][] getgStore() {
        return gStore;
    }

    public void setgStore(int[][] gStore) {
        this.gStore = gStore;
    }
}
