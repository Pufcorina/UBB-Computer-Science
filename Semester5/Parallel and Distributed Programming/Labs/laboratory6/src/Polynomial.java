import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Polynomial {
    private List<BigInteger> coefs;
    private int rank;

    Polynomial(int rank, BigInteger min, BigInteger max) {
        this.rank = rank;
        coefs = new ArrayList<>(rank + 1);

        for (int i = 0; i <= rank; i++) {
            this.coefs.add(this.getRandomBigInt(min, max));
        }
    }

    Polynomial(int rank, int min, int max) {
        this(rank, new BigInteger(String.valueOf(min)), new BigInteger(String.valueOf(max)));
    }

    Polynomial(int rank, String min, String max) {
        this(rank, new BigInteger(min), new BigInteger(max));
    }

    public BigInteger getCoef(int index) {
        return this.coefs.get(index);
    }

    public void setCoef(int index, int value) {
        this.coefs.set(index, new BigInteger(String.valueOf(value)));
    }

    public void setCoef(int index, BigInteger value) {
        this.coefs.set(index, value);
    }

    public int getRank() {
        return this.rank;
    }

    public void addCoef(int index, BigInteger value) {
        this.coefs.set(index, this.coefs.get(index).add(value));
    }

    @Override
    public String toString() {
        StringBuilder ss = new StringBuilder();

        BigInteger ZERO = BigInteger.ZERO;

        for (int i = this.rank; i >= 0; i--){
            BigInteger coef = this.coefs.get(i);
            if (coef.compareTo(ZERO) > 0) {
                ss.append("+").append(this.coefs.get(i)).append("X^").append(i).append(" ");
            }else if (coef.compareTo(ZERO) < 0) {
                ss.append(this.coefs.get(i)).append("X^").append(i).append(" ");
            }
        }
        return ss.toString();
    }

    private BigInteger getRandomBigInt(BigInteger min, BigInteger max) {
        BigInteger result;

        Random random = new Random();

        int numBits = max.bitLength() - 1;
        if (numBits <= 0) {
            numBits = 1;
        }

        do {
            result = new BigInteger(numBits, random);
        } while (result.compareTo(min) >= 0 && result.compareTo(max) <= 0);

        return result;
    }
}