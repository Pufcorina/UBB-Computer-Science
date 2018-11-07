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
        encodedY = new BlockManipulation().splitInBlocks(image, "Y", image.getY());
        encodedU = new BlockManipulation().splitInBlocks(image, "U", image.getU());
        encodedV = new BlockManipulation().splitInBlocks(image, "V", image.getV());
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

    public void setY(double[][] matrix) {
        image.setY(matrix);
    }

    public void setU(double[][] matrix) {
        image.setU(matrix);
    }

    public void setV(double[][] matrix) {
        image.setV(matrix);
    }
}
