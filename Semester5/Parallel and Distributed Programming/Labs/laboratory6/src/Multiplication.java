import sun.awt.Mutex;

import java.util.List;

public class Multiplication implements Runnable {
    private Polynomial a;
    private Polynomial b;
    private Polynomial result;
    private int coefA;
    private int coefB;
    private List<Mutex> coefMutex;
    private Algorithm algorithm;

    public Multiplication(Polynomial a, Polynomial b, Polynomial result, int coefA, int coefB, List<Mutex> coefMutex, Algorithm algorthm) {
        this.a = a;
        this.b = b;
        this.result = result;
        this.coefA = coefA;
        this.coefB = coefB;
        this.coefMutex = coefMutex;
        this.algorithm = algorthm;
    }

    @Override
    public void run() {
        int newCoef = coefA + coefB;
        this.coefMutex.get(newCoef).lock();
        result.addCoef(coefA + coefB, algorithm.multiply(a.getCoef(coefA),  b.getCoef(coefB)));
        this.coefMutex.get(newCoef).unlock();
    }
}