package domain;

import java.util.ArrayList;
import java.util.List;

public class Decoder {
    private Encoder encoder;

    public Decoder(Encoder encoder){
        this.encoder = encoder;
//        decodeBlock("Y");
//        decodeBlock("U");
//        decodeBlock("V");
        decodeEncoded();
    }

    private void decodeEncoded() {
        List<BlockStore> decodeU = new BlockManipulation().getListResized(encoder.getEncodedU());
        List<BlockStore> decodeV = new BlockManipulation().getListResized(encoder.getEncodedV());
        encoder.setY(decodeBlock(this.encoder.getEncodedY()));
        encoder.setU(decodeBlock(decodeU));
        encoder.setV(decodeBlock(decodeV));
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
