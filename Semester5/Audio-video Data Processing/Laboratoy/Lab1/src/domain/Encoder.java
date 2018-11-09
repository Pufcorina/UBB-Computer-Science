package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Encoder {
    private PPM image;
    private List<BlockStore> encodedY;
    private List<BlockStore> encodedU;
    private List<BlockStore> encodedV;

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

    public Encoder(PPM image) {
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
    }

    private void quantizationPhase(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            block.setgStore(MatrixOperation.divideMatrixes(block.getgStore(), Q));
    }



    private void forwardDCT(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            block.setgStore(fDCT(block.getStore()));
    }

    double[][] fDCT(double[][] matrix) {
        double[][] G = new double[8][8];
        double constant = (double) 1 / 4;

        for (int u = 0; u < 8; u++)
            for (int v = 0; v < 8; v++)
            {
                G[u][v] = constant * alpha(u) * alpha(v) * outerSum(matrix, u, v);
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

    List<BlockStore> getEncodedY() {
        return encodedY;
    }

    List<BlockStore> getEncodedU() {
        return encodedU;
    }

    List<BlockStore> getEncodedV() {
        return encodedV;
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

    public void setEncodedY(List<BlockStore> encodedY) {
        this.encodedY = encodedY;
    }

    public void setEncodedU(List<BlockStore> encodedU) {
        this.encodedU = encodedU;
    }

    public void setEncodedV(List<BlockStore> encodedV) {
        this.encodedV = encodedV;
    }
}
