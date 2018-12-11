import java.util.ArrayList;
import java.util.List;

class DirectedGraph {
    private List<List<Integer>> container;
    private List<Integer> nodes;

    DirectedGraph(int nodeCount) {
        this.container = new ArrayList<>(nodeCount);
        this.nodes = new ArrayList<>();

        for (int i = 0; i < nodeCount; i++) {
            this.container.add(new ArrayList<>());
            this.nodes.add(i);
        }
    }

    void addEdge(int nodeA, int nodeB) {
        this.container.get(nodeA).add(nodeB);
    }

    List<Integer> neighboursOf(int node) {
        return this.container.get(node);
    }

    List<Integer> getNodes(){
        return nodes;
    }

    int size() {
        return this.container.size();
    }

}