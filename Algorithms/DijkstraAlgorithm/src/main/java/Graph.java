import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
    private Map<String, Integer> graph = new HashMap<>();
    private List<Vertex> vertices = new ArrayList<>();

    public Graph(Vertex[] vertices) {
    for (Vertex v : vertices) {
        for (Vertex vertex : v.getNeighbors()) {
            graph.put(v.getName() + vertex.getName(), 0);
            }
        if (!this.vertices.contains(v)) {
            this.vertices.add(v);
        }
    }
    }

    public void addCost(String edge, Integer cost) {
        if (graph.containsKey(edge)) {
            graph.put(edge, cost);
        } else {
            System.out.println("There isn't such edge");
        }
    }

    public void print() {
        for (Map.Entry<String, Integer> pair : graph.entrySet()) {
            System.out.println(pair.getKey() + " - " + pair.getValue());
        }
    }

    public List<Vertex> getVertices() {
        return vertices;
    }

    public Map<String, Integer> getGraph() {
        return graph;
    }
}
