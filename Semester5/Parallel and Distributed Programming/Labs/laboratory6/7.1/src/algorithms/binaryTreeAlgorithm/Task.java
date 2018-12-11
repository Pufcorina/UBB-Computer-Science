package algorithms.binaryTreeAlgorithm;

public class Task implements Runnable {
    private Vector vector;

    private int halfDistance;
    private int index;

    Task(Vector vector, int halfDistance, int index) {
        this.vector = vector;
        this.halfDistance = halfDistance;
        this.index = index;
    }

    @Override
    public void run() {
        this.vector.add(index, this.vector.get(index - halfDistance));
    }
}