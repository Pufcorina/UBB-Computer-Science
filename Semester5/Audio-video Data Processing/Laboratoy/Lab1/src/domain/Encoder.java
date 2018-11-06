package domain;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Encoder {
    private PPM image;
    private List<BlockStore> encodedY = new ArrayList<>();
    private List<BlockStore> encodedU = new ArrayList<>();
    private List<BlockStore> encodedV = new ArrayList<>();

    public Encoder(PPM image) {
        this.image = image;
        encodeBlocks("Y");
        encodeBlocks("U");
        encodeBlocks("V");
    }

    private void encodeBlocks(String type) {
        int length = image.getWidth() * image.getHeight() / 64;

        int line = 0;
        int column = 0;

        for (int pos = 0; pos < length && line != image.getHeight(); pos++) {
            BlockStore store = new BlockStore(8, type, pos);
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++) {
                    switch (type) {
                        case "Y":
                            store.getStore()[i][j] = image.getY()[line][column];
                            break;
                        case "U":
                            store.getStore()[i][j] = image.getU()[line][column];
                            break;
                        default:
                            store.getStore()[i][j] = image.getV()[line][column];
                            break;
                    }

                    column++;
                    if (column % 8 == 0) {
                        line++;
                        column -= 8;
                    }
                }

                line -= 8;
                column += 8;

            switch (type) {
                case "Y":
                    encodedY.add(store);
                    break;
                case "U":
                    encodedU.add(sampleBlock(store));
                    break;
                default:
                    encodedV.add(sampleBlock(store));
                    break;
            }

            if (column == image.getWidth()) {
                column = 0;
                line += 8;
            }
        }
    }

    private BlockStore sampleBlock(BlockStore toSample) {
        BlockStore sampleStore = new BlockStore(4, toSample.getStoreType(), toSample.getPosition());
        int line = 0;
        int column = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                sampleStore.getStore()[i][j] = (toSample.getStore()[line][column] +
                                                toSample.getStore()[line][column + 1] +
                                                toSample.getStore()[line + 1][column] +
                                                toSample.getStore()[line + 1][column + 1])
                                                / 4.0;
                column += 2;
            }
            line += 2;
            column = 0;
        }
        return sampleStore;
    }

    public PPM getImage() {
        return image;
    }

    public List<BlockStore> getEncodedY() {
        return encodedY;
    }

    public List<BlockStore> getEncodedU() {
        return encodedU;
    }

    public List<BlockStore> getEncodedV() {
        return encodedV;
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
}
