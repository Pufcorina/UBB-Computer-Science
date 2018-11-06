package domain;

import java.util.List;

public class Decoder {
    private Encoder encoder;

    public Decoder(Encoder encoder){
        this.encoder = encoder;
        decoreBlock("Y");
        decoreBlock("U");
        decoreBlock("V");
    }

    private void decoreBlock(String type) {
        int line = 0;
        int column = 0;
        List<BlockStore> encoded;
        switch (type) {
            case "Y":
                encoded = encoder.getEncodedY();
                break;
            case "U":
                encoded = encoder.getEncodedU();
                break;
            default:
                encoded = encoder.getEncodedV();
                break;
        }
        
        
        for (BlockStore blockStore: encoded) {
            double[][] matrix = new double[encoder.getImage().getHeight()][encoder.getImage().getWidth()];
            if (type.equals("U") || type.equals("V"))
                blockStore = resizeBlock(blockStore, 0, type);
            for (int i = 0; i < 8; i++)
                for (int j = 0; j < 8; j++) {
                    matrix[line][column] = blockStore.getStore()[i][j];
                    column++;
                    if (column % 8 == 0) {
                        line++;
                        column -= 8;
                    }
                }
            line -= 8;
            column += 8;
            if (column == encoder.getImage().getWidth()) {
                column = 0;
                line += 8;
            }
        }
    }

    private BlockStore resizeBlock(BlockStore blockStore, int position, String type) {
        BlockStore sampleStore = new BlockStore(8, type, position);
        int line = 0;
        int column = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                double value = blockStore.getStore()[i][j];
                sampleStore.getStore()[line][column] = value;
                sampleStore.getStore()[line][column + 1] = value;
                sampleStore.getStore()[line + 1][column] = value;
                sampleStore.getStore()[line + 1][column + 1] = value;
                column += 2;
            }
            line += 2;
            column = 0;
        }

        return sampleStore;
    }

    public PPM getImage() {
        return encoder.getImage();
    }
}
