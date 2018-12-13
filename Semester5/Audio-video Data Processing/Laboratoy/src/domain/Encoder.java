package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Encoder {
    private PPM image;
    private List<BlockStore> encodedY;
    private List<BlockStore> encodedU;
    private List<BlockStore> encodedV;
    private HashMap<Integer, List<Integer>> amplitudes = new HashMap<>();
    private List<Integer> entropy = new ArrayList<>();

    private double[][] Q = {
            {6, 4, 4, 6, 10, 16, 20, 24},
            {5, 5, 6, 8, 10, 23, 24, 22},
            {6, 5, 6, 10, 16, 23, 28, 22},
            {6, 7, 9, 12, 20, 35, 32, 25},
            {7, 9, 15, 22, 27, 44, 41, 31},
            {10, 14, 22, 26, 32, 42, 45, 37},
            {20, 26, 31, 35, 41, 48, 48, 40},
            {29, 37, 38, 39, 45, 40, 41, 40}
    };

    public Encoder(PPM image) throws IOException {
        amplitudes.put(1, Arrays.asList(-1, 1));
        amplitudes.put(2, Arrays.asList(-3, -2, 2, 3));
        amplitudes.put(3, Arrays.asList(-7, -4, 4, 7));
        amplitudes.put(4, Arrays.asList(-15, -8, 8, 15));
        amplitudes.put(5, Arrays.asList(-31, -16, 16, 31));
        amplitudes.put(6, Arrays.asList(-63, -32, 32, 63));
        amplitudes.put(7, Arrays.asList(-127, -64, 64, 127));
        amplitudes.put(8, Arrays.asList(-225, -128, 128, 255));
        amplitudes.put(9, Arrays.asList(-511, -256, 256, 511));
        amplitudes.put(10, Arrays.asList(-1023, -512, 512, 1023));

        this.image = image;

        encodedY = BlockManipulation.splitInBlocks(image, "Y", image.getY());
        encodedU = BlockManipulation.splitInBlocks(image, "U", image.getU());
        encodedV = BlockManipulation.splitInBlocks(image, "V", image.getV());

        encodedU = BlockManipulation.getListResized(encodedU);
        encodedV = BlockManipulation.getListResized(encodedV);

        substractValue(encodedY);
        substractValue(encodedU);
        substractValue(encodedV);

        forwardDCT(encodedY);
        forwardDCT(encodedU);
        forwardDCT(encodedV);

        quantizationPhase(encodedY);
        quantizationPhase(encodedU);
        quantizationPhase(encodedV);

        entropyEncoding();

        FileWriter fileWriter = new FileWriter("./data/entropy");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println(entropy);

        printWriter.close();
    }

    private void entropyEncoding() {
        for (int i = 0; i < encodedY.size(); i++) {
            addEntropy(encodedY.get(i).getgStore());
            addEntropy(encodedU.get(i).getgStore());
            addEntropy(encodedV.get(i).getgStore());
        }
    }

    private void addEntropy(int[][] matrix) {
        int[] lista = parcurgereMatrice(matrix);

        int DC_size = getSize(lista[0]);
        entropy.addAll(Arrays.asList(DC_size, lista[0]));

        for(int i = 1; i < 64; i++)
        {
            int cnt = 0;
            while( lista[i] == 0) {
                cnt++;
                i++;
                if ( i == 64 ) {
                    break;
                }
            }
            if (i == 64 )
                entropy.addAll(Arrays.asList(0, 0));
            else
            {
                entropy.addAll(Arrays.asList(cnt, getSize(lista[i]), lista[i]));
            }
        }
    }

    private int getSize(int amplitude_value) {
        if (amplitude_value == 0) return 0;
        for (Integer k : amplitudes.keySet())
            if (amplitude_value == 1 || amplitude_value == -1) {
                return 1;
            }
            else if (k != 1) {
                if (amplitudes.get(k).get(0).compareTo(amplitude_value) == 0
                        || amplitudes.get(k).get(1).compareTo(amplitude_value) == 0
                        || amplitudes.get(k).get(2).compareTo(amplitude_value) == 0
                        || amplitudes.get(k).get(3).compareTo(amplitude_value) == 0
                        || amplitudes.get(k).get(0).compareTo(amplitude_value) == (-1) *  amplitudes.get(k).get(1).compareTo(amplitude_value)
                        || amplitudes.get(k).get(2).compareTo(amplitude_value) == (-1) *  amplitudes.get(k).get(3).compareTo(amplitude_value))
                    return k;
            }
        return -1;
    }

    private int[] parcurgereMatrice(int[][] matrix) {
        int[] lista = new int[64];
        int k = 0;
        int column = 0;
        int row = 0;
        lista[k] = matrix[row][column];
        do {
            k++;
            column++;
            lista[k] = matrix[row][column];
            do {
                k++;
                column--;
                row++;
                lista[k] = matrix[row][column];
            } while (column != 0);

            if (row == 7 )
                break;
            row++;
            k++;
            lista[k] = matrix[row][column];
            do {
                row--;
                column++;
                k++;
                lista[k] = matrix[row][column];
            } while (row != 0);
        } while (true);

        do {

            k++;
            column++;
            lista[k] = matrix[row][column];
            if (column == 7)
                break;
            do {
                k++;
                column++;
                row--;
                lista[k] = matrix[row][column];
            } while (column != 7);
            row++;
            k++;
            lista[k] = matrix[row][column];
            do {
                row++;
                column--;
                k++;
                lista[k] = matrix[row][column];
            } while (row != 7);
        } while (true);

        return lista;
    }

    private void quantizationPhase(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            block.setgStore(MatrixOperation.divideMatrixes(block.getgStore(), Q));
    }



    private void forwardDCT(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            block.setgStore(fDCT(block.getStore()));
    }

    int[][] fDCT(double[][] matrix) {
        int[][] G = new int[8][8];
        double constant = (double) 1 / 4;

        for (int u = 0; u < 8; u++)
            for (int v = 0; v < 8; v++)
            {
                G[u][v] = (int) (constant * alpha(u) * alpha(v) * outerSum(matrix, u, v));
            }

        return G;
    }

    private double outerSum(double[][] matrix, int u, int v) {
        double sum = 0.0;
        for (int x = 0; x < 8; x++)
            sum += innerSum(matrix, u, v, x);
        return sum;
    }

    private double innerSum(double[][] matrix, int u, int v, int x) {
        double sum = 0.0;
        for (int y = 0; y < 8; y++)
            sum += product(matrix[x][y], x, y, u, v);
        return sum;
    }

    private double product(double matrixValue, int x, int y, int u, int v) {
        double cosU = Math.cos(
                ((2 * x + 1) * u * Math.PI) / 16
        );

        double cosV = Math.cos(
                ((2 * y + 1) * v * Math.PI) / 16
        );

        return matrixValue * cosU * cosV;
    }

    private double alpha(int value) {
        return value > 0 ? 1 : (1 / Math.sqrt(2.0));
    }

    private void substractValue(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    block.getStore()[i][j] -= 128.0;
    }



    public void printMatrixes() throws IOException {
        printMatrix(encodedY, "encodedY");
        printMatrix(encodedU, "encodedU");
        printMatrix(encodedV, "encodedV");
    }

    private void printMatrix(List<BlockStore> encoded, String filename) throws IOException {
        FileWriter fileWriter = new FileWriter("./data/" + filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (BlockStore block: encoded ) {
            printWriter.println(block);
        }

        printWriter.close();
    }

    PPM getImage() {
        return image;
    }

    void setY(double[][] matrix) {
        image.setY(matrix);
    }

    void setU(double[][] matrix) {
        image.setU(matrix);
    }

    void setV(double[][] matrix) {
        image.setV(matrix);
    }

    public List<Integer> getEntropy() {
        return entropy;
    }
}
