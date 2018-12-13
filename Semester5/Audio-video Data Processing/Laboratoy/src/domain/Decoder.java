package domain;

import java.util.ArrayList;
import java.util.List;

public class Decoder {
    private Encoder encoder;
    private int pos;

    private List<BlockStore> encodedY = new ArrayList<>();
    private List<BlockStore> encodedU = new ArrayList<>();
    private List<BlockStore> encodedV = new ArrayList<>();

    List<Integer> entropy;

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

    public Decoder(Encoder encoder){
        this.encoder = encoder;
        decodeEncoded();
    }

    private void decodeEncoded(){
        this.entropy = encoder.getEntropy();
        entropyDecoding();

        deQuantizationPhase(encodedY);
        deQuantizationPhase(encodedU);
        deQuantizationPhase(encodedV);

        inverseDCT(encodedY);
        inverseDCT(encodedU);
        inverseDCT(encodedV);

        addValue(encodedY);
        addValue(encodedU);
        addValue(encodedV);

        encoder.setY(decodeBlock(encodedY));
        encoder.setU(decodeBlock(encodedU));
        encoder.setV(decodeBlock(encodedV));


    }

    private void entropyDecoding() {
        pos = 0;
        while (pos < entropy.size())
        {
            BlockStore blockY = new BlockStore(8, "Y");
            blockY.setgStore(getBlock());
            encodedY.add(blockY);

            BlockStore blockU = new BlockStore(8, "U");
            blockU.setgStore(getBlock());
            encodedU.add(blockU);

            BlockStore blockV = new BlockStore(8, "V");
            blockV.setgStore(getBlock());
            encodedV.add(blockV);
        }
    }

    private int[][] getBlock() {
        int[][] matrix = new int[8][8];

        pos++;
        matrix[0][0] = entropy.get(pos++);

        if (entropy.get(pos) == 0 && entropy.get(pos + 1) == 0) {
            pos += 2;
            return matrix;
        }

        //upper zig-zag

        int column = 0;
        int row = 0;

        do {
            column++;
            if (setMatrix(row, column, matrix)) return matrix;

            do {
                row++;
                column--;
                if (setMatrix(row, column, matrix)) return matrix;
            } while (column != 0);

            if (row == 7 )
                break;
            row++;
            if (setMatrix(row, column, matrix)) return matrix;
            do {
                row--;
                column++;
                if (setMatrix(row, column, matrix)) return matrix;
            } while (row != 0);
        } while (true);


        do {
            column++;
            if (setMatrix(row, column, matrix)) return matrix;
            if (column == 7)
                break;
            do {
                row--;
                column++;
                if (setMatrix(row, column, matrix)) return matrix;
            } while (column != 7);
            row++;
            if (setMatrix(row, column, matrix)) return matrix;
            do {
                row++;
                column--;
                if (setMatrix(row, column, matrix)) return matrix;
            } while (row != 7);
        } while (true);

        return matrix;
    }

    private boolean setMatrix(int row, int column, int[][] matrix) {
        if (entropy.get(pos) == 0 && entropy.get(pos + 1) == 0) {
            pos += 2;
            return true;
        }
        matrix[row][column] = entropy.get(pos) == 0 ? entropy.get(pos + 2): 0;
        if (entropy.get(pos) != 0)
            entropy.set(pos, entropy.get(pos) - 1);
        else
            pos += 3;
        return false;
    }


    private void deQuantizationPhase(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            block.setgStore(MatrixOperation.multiplyMatrixes(block.getgStore(), Q));
    }


    private void inverseDCT(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            block.setgStore(iDCT(block.getgStore()));
    }

    private int[][] iDCT(int[][] matrix) {
        int[][] f = new int[8][8];
        double constant = (double) 1 / 4;

        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
            {
                f[x][y] = (int) (constant * outerSum(matrix, x, y));
            }

        return f;
    }

    private double outerSum(int[][] matrix, int x, int y) {
        double sum = 0.0;
        for (int u = 0; u < 8; u++)
            sum += innerSum(matrix, x, y, u);
        return sum;
    }

    private double innerSum(int[][] matrix, int x, int y, int u) {
        double sum = 0.0;
        for (int v = 0; v < 8; v++)
            sum += product(matrix[u][v], x, y, u, v);
        return sum;
    }

    private double product(double matrixValue, int x, int y, int u, int v) {
        double cosU = Math.cos(
                ((2 * x + 1) * u * Math.PI) / 16
        );

        double cosV = Math.cos(
                ((2 * y + 1) * v * Math.PI) / 16
        );

        return alpha(u) * alpha(v) * matrixValue * cosU * cosV;
    }

    private double alpha(int value) {
        return value > 0 ? 1 : (1 / Math.sqrt(2.0));
    }

    private void addValue(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    block.getgStore()[i][j] += 128.0;
    }


    private double[][] decodeBlock(List<BlockStore> encoded) {
        double[][] matrix = new double[encoder.getImage().getHeight()][encoder.getImage().getWidth()];

        int line = 0;
        int column = 0;

        for (BlockStore blockStore : encoded)
        {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    matrix[line + i][column + j] = blockStore.getgStore()[i][j];
            column += 8;
            if (column == encoder.getImage().getWidth())
            {
                line += 8;
                column = 0;
            }
        }

        return matrix;
    }



    public PPM getImage() {
        return encoder.getImage();
    }
}
