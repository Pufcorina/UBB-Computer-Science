package model.util;

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

    public String getFileName() {
        return fileName;
    }

    @Override
    public String toString() {
        return " ( " + fileName + ", " + bufferedReader.toString() + " ) ";
    }
}
