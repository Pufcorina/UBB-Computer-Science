package domain;

import java.io.*;
import java.util.Objects;

public class PPM {
    private String filename;
    private int width;
    private int height;
    private int maxValue;
    private String format;
    private int[][] r;
    private int[][] g;
    private int[][] b;
    private double[][] y;
    private double[][] u;
    private double[][] v;

    private PPM(PPM ppm){
        this.filename = ppm.filename;
        this.width = ppm.width;
        this.height = ppm.height;
        this.maxValue = ppm.maxValue;
        this.format = ppm.format;
        this.r = ppm.r;
        this.g = ppm.g;
        this.b = ppm.b;
        this.y = ppm.y;
        this.u = ppm.u;
        this.v = ppm.v;
    }

    public PPM(String filename) throws FileNotFoundException {
        this.filename = filename;
        this.readPPM(filename);
        this.perfomConversionToYUV();
    }

    private void perfomConversionToYUV() {
        for (int line = 0; line < height; line++)
            for (int column = 0; column < width; column++) {
                y[line][column] = 0.299 * r[line][column] + 0.587 * g[line][column] + 0.114 * b[line][column];
                u[line][column] = 128 - 0.168736 * r[line][column] - 0.331264 * g[line][column] + 0.5 * b[line][column];
                v[line][column] = 128 + 0.5 * r[line][column] - 0.418688 * g[line][column] - 0.081312 * b[line][column];
            }
    }

    private void readPPM(String filename) throws FileNotFoundException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        String st;
        try {
            String firstLine = Objects.requireNonNull(br).readLine();
            br.readLine();

            String thirdLine = br.readLine();
            height = Integer.parseInt(thirdLine.split(" ")[1]);
            width = Integer.parseInt(thirdLine.split(" ")[0]);
            maxValue = Integer.parseInt(br.readLine());
            r = new int[height][width];
            g = new int[height][width];
            b = new int[height][width];

            y = new double[height][width];
            u = new double[height][width];
            v = new double[height][width];

            int line = 0;
            int column = 0;
            while ((st = br.readLine()) != null && line != height) {
                if (column == width) {
                    column = 0;
                    line ++;
                }

                r[line][column] = Integer.parseInt(st);
                g[line][column] = Integer.parseInt(br.readLine());
                b[line][column] = Integer.parseInt(br.readLine());
                column ++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void writePPM(String filename) throws IOException {
        FileWriter fileWriter = new FileWriter("./data/" + filename);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.println("P3");
        printWriter.println("800 600");
        printWriter.println("255");
        for (int line = 0; line < height; line++) {
            for (int column = 0; column < width; column++) {
                printWriter.println(this.r[line][column]);
                printWriter.println(this.g[line][column]);
                printWriter.println(this.b[line][column]);
            }
        }

        printWriter.close();
    }

    public PPM getImageConvertedToRGB() {
        PPM newPPM = new PPM(this);
        for (int line = 0; line < height; line++)
            for (int column = 0; column < width; column++) {
                Double R = y[line][column] + 1.402 * (v[line][column] - 128);
                Double G = y[line][column] - 0.344136 * (u[line][column] - 128) - 0.714136 * (v[line][column] - 128);
                Double B = y[line][column] + 1.7790 * (u[line][column] - 128);

                if (R > 255) R = 255.0;
                if (G > 255) G = 255.0;
                if (B > 255) B = 255.0;

                if (R < 0) R = 0.0;
                if (G < 0) G = 0.0;
                if (B < 0) B = 0.0;

                newPPM.r[line][column] = R.intValue();
                newPPM.g[line][column] = G.intValue();
                newPPM.b[line][column] = B.intValue();
            }
        return newPPM;
    }

    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    double[][] getY() {
        return y;
    }

    double[][] getU() {
        return u;
    }

    double[][] getV() {
        return v;
    }

    void setY(double[][] y) {
        this.y = y;
    }

    void setU(double[][] u) {
        this.u = u;
    }

    void setV(double[][] v) {
        this.v = v;
    }
}
