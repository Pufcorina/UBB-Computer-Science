package Model.Statement.File;

import java.io.BufferedReader;

public class MyFilePair {
    private String fileName;
    private BufferedReader bufferedReader;

    public MyFilePair(String fileName, BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
        this.fileName = fileName;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return " ( " + fileName + ", " + bufferedReader.toString() + " ) ";
    }
}
