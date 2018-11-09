package domain;

import java.util.ArrayList;
import java.util.List;

class BlockManipulation {
    static List<BlockStore> splitInBlocks(PPM image, String type, double[][] matrix) {
        List<BlockStore> encoded = new ArrayList<>();

        for (int i = 0; i < image.getHeight(); i += 8)
            for (int j = 0; j < image.getWidth(); j += 8) {
                BlockStore store = subMatrix(type, i, j, matrix);
                if (type.equals("Y"))
                    encoded.add(store);
                else
                    encoded.add(average4Block(store));
            }

        return encoded;
    }

    private static BlockStore subMatrix(String type, int i_pos, int j_pos, double[][] matrix) {
        BlockStore store = new BlockStore(8, type);

        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                store.getStore()[i][j] = matrix[i + i_pos][j + j_pos];

        return store;
    }

    private static BlockStore average4Block(BlockStore toSample) {
        BlockStore sampleStore = new BlockStore(4, toSample.getStoreType());
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

    static List<BlockStore> getListResized(List<BlockStore> encoded) {
        List<BlockStore> resized = new ArrayList<>();
        encoded.forEach(b -> resized.add(resizeBlock(b)));
        return resized;
    }

    private static BlockStore resizeBlock(BlockStore blockStore) {
        BlockStore sampleStore = new BlockStore(8, blockStore.getStoreType());
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
}