import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

public class Task implements Runnable {

    private DirectedGraph graph;
    private int startingNode;
    private List<Integer> path;
    private Lock lock;
    private List<Integer> result;

    Task(DirectedGraph graph, int node, List<Integer> result, Lock lock) {
        this.graph = graph;
        this.startingNode = node;
        path = new ArrayList<>();
        this.lock = lock;
        this.result = result;
    }

    @Override
    public void run() {
        visit(startingNode);
    }

    private void setResult() {
        this.lock.lock();
        this.result.clear();
        this.result.addAll(this.path);
        this.lock.unlock();
    }
    private void visit(int node) {
        path.add(node);

        if (path.size() == graph.size()) {
            if (graph.neighboursOf(node).contains(startingNode)){
                setResult();
            }
            return;
        }

        for (int neighbour : graph.neighboursOf(node)) {
            if (!this.path.contains(neighbour)){
                visit(neighbour);
            }
        }
    }
}