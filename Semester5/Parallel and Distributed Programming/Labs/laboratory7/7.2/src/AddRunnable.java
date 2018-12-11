import java.math.BigInteger;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class AddRunnable implements Runnable {
    private ArrayBlockingQueue<BigInteger> queueIN;
    private ArrayBlockingQueue<BigInteger> queueOUT;
    private BigInteger number;
    private BigInteger digitPos = BigInteger.ONE;
    private BigInteger remainder = BigInteger.ZERO;
    private BigInteger sum = BigInteger.ZERO;


    AddRunnable(BigInteger number, BigInteger secondNumber, ArrayBlockingQueue<BigInteger> queueOUT) {
        this.queueIN = new ArrayBlockingQueue<>(secondNumber.toString().length() + 1);
        this.queueOUT = queueOUT;
        this.number = number;
        this.splitNumberInQueueIN(secondNumber);
    }

    AddRunnable(BigInteger number, ArrayBlockingQueue<BigInteger> queueIN, ArrayBlockingQueue<BigInteger> queueOUT) {
        this.queueIN = queueIN;
        this.queueOUT = queueOUT;
        this.number = number;
    }


    @Override
    public void run() {
        try {
            addition();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addition() throws InterruptedException {

        BigInteger digit = this.queueIN.poll(5, TimeUnit.SECONDS);

        while (digit.compareTo(new BigInteger("-1")) != 0) {
            this.digitPos = this.digitPos.multiply(BigInteger.TEN);

            sum = digit
                    .add(this.number.mod(this.digitPos).divide(this.digitPos.divide(BigInteger.TEN)))
                    .add(this.remainder);

            this.setRemainderAndSum();
            queueOUT.offer(sum);

            digit = this.queueIN.poll(5, TimeUnit.SECONDS);
        }

        while (this.digitPos.compareTo(this.number) <= 0 || this.remainder.compareTo(BigInteger.ZERO) != 0){
            if (this.digitPos.compareTo(this.number) <= 0) {
                this.digitPos = this.digitPos.multiply(BigInteger.TEN);

                sum = digit
                        .add(this.number.mod(this.digitPos).divide(this.digitPos.divide(BigInteger.TEN)))
                        .add(this.remainder);

                this.setRemainderAndSum();
                queueOUT.offer(sum);

            } else {
                queueOUT.offer(this.remainder);
                this.remainder = BigInteger.ZERO;
            }

        }

        queueOUT.offer(new BigInteger("-1"));
    }

    private void setRemainderAndSum(){
        if (sum.compareTo(BigInteger.TEN) >= 0) {
            remainder = sum.divide(BigInteger.TEN);
            sum = sum.mod(BigInteger.TEN);
        } else {
            remainder = BigInteger.ZERO;
        }
    }

    private void splitNumberInQueueIN(BigInteger number) {
        this.queueIN.clear();

        while (number.compareTo(BigInteger.ZERO) != 0) {
            this.queueIN.add(number.mod(BigInteger.TEN));
            number = number.divide(BigInteger.TEN);
        }

        this.queueIN.add(new BigInteger("-1"));
    }
}