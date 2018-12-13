package parser.utils;

public class Delta {
    private String x;
    private String y;
    private String result;

    public Delta(String x, String y, String result) {
        this.x = x;
        this.y = y;
        this.result = result;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String toString() {
        return "δ(" + x + "," + y + ") = " + result;
    }
}

