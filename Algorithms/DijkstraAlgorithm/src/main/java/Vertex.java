import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex {
    private String name;
    private List<Vertex> neighbors = new ArrayList<>();

    public Vertex(String name) {
        this.name = name;
    }

    public void addNeighbor(Vertex... vertexes) {
        for (Vertex v : vertexes) {
            neighbors.add(v);
        }
    }

    public String getName() {
        return name;
    }

    public List<Vertex> getNeighbors() {
        return neighbors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return Objects.equals(name, vertex.name) &&
                Objects.equals(neighbors, vertex.neighbors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, neighbors);
    }
}
