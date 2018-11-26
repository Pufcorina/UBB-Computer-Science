import java.math.BigInteger;

public class NormalAlgorithm implements Algorithm {
    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }
}