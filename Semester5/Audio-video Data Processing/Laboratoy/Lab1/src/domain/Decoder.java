package domain;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Decoder {
    private Encoder encoder;

    private List<BlockStore> encodedY = new ArrayList<>();
    private List<BlockStore> encodedU = new ArrayList<>();
    private List<BlockStore> encodedV = new ArrayList<>();

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

    private void decodeEncoded() {
        entropyDecoding(encoder.getEntropy());

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

    private void entropyDecoding(List<Pair<Pair<Integer, Integer>, Integer>> entropy) {
        int pos = 0;
        while (pos < entropy.size())
        {
            pos = getBlock(entropy, pos, encodedY, "Y");
            pos = getBlock(entropy, pos, encodedU, "U");
            pos = getBlock(entropy, pos, encodedV, "V");
        }
    }

    private int getBlock(List<Pair<Pair<Integer, Integer>, Integer>> entropy, int pos, List<BlockStore> encoded, String blockType) {
        int colum = 1;
        int row = 0;

        BlockStore block = new BlockStore(8, blockType);
        double[][] matrix = new double[8][8];

        matrix[0][0] = entropy.get(pos).getValue();
        pos++;

        do {
            int amplitude = entropy.get(pos).getValue();
            int runlength = entropy.get(pos).getKey().getKey();

            if ( amplitude == 0 && runlength == 0) {
                do {
                    matrix[row][colum] = 0;
                    colum++;
                    if ( colum == 8 ){
                        colum = 0;
                        row++;
                    }
                } while (colum != 7 && row != 7);
                pos++;
                break;
            }

            while (runlength != 0)
            {
                matrix[row][colum] = 0;
                runlength--;
                colum++;
                if ( colum == 8 ){
                    colum = 0;
                    row++;
                }
            }

            matrix[row][colum] = amplitude;
            pos++;
            colum++;
            if ( colum == 8 ){
                colum = 0;
                row++;
            }
            if (colum == 7 && row == 7)
                break;
        } while (true);
        block.setgStore(matrix);
        encoded.add(block);
        return pos;
    }

    private void deQuantizationPhase(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            block.setgStore(MatrixOperation.multiplyMatrixes(block.getgStore(), Q));
    }


    private void inverseDCT(List<BlockStore> encoded) {
        for (BlockStore block: encoded)
            block.setStore(iDCT(block.getgStore()));
    }

    private double[][] iDCT(double[][] matrix) {
        double[][] f = new double[8][8];
        double constant = (double) 1 / 4;

        for (int x = 0; x < 8; x++)
            for (int y = 0; y < 8; y++)
            {
                f[x][y] = constant * outerSum(matrix, x, y);
            }

        return f;
    }

    private double outerSum(double[][] matrix, int x, int y) {
        double sum = 0.0;
        for (int u = 0; u < 8; u++)
            sum += innerSum(matrix, x, y, u);
        return sum;
    }

    private double innerSum(double[][] matrix, int x, int y, int u) {
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
                    block.getStore()[i][j] += 128.0;
    }


    private double[][] decodeBlock(List<BlockStore> encoded) {
        double[][] matrix = new double[encoder.getImage().getHeight()][encoder.getImage().getWidth()];

        int line = 0;
        int column = 0;

        for (BlockStore blockStore : encoded)
        {
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++)
                    matrix[line + i][column + j] = blockStore.getStore()[i][j];
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
