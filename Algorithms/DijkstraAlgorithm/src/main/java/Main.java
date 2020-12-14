import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private static Map<Vertex, Integer> minimalCost = new HashMap<>();
    private static Map<Vertex, Vertex> parent = new HashMap<>();

    public static void main(String[] args) {
        //Creating vertexes
        Vertex book = new Vertex("Book");
        Vertex guitar = new Vertex("Guitar");
        Vertex piano = new Vertex("Piano");
        Vertex record = new Vertex("Record");
        Vertex picture = new Vertex("Picture");
        Vertex drum = new Vertex("Dram");

        //Adding neighbors
        book.addNeighbor(record, picture);
        record.addNeighbor(drum, guitar);
        picture.addNeighbor(guitar, drum);
        guitar.addNeighbor(piano);
        drum.addNeighbor(piano);

        //Create graph
        Graph graph = new Graph(new Vertex[]{book, record, picture, guitar, drum, piano});
        graph.addCost("RecordDram", 20);
        graph.addCost("BookPicture", 0);
        graph.addCost("BookRecord", 5);
        graph.addCost("RecordGuitar", 15);
        graph.addCost("PictureGuitar", 30);
        graph.addCost("PictureDram", 35);
        graph.addCost("GuitarPiano", 20);
        graph.addCost("DramPiano", 10);

        for (Vertex v : graph.getVertices()) {
            minimalCost.put(v, Integer.MAX_VALUE);
            parent.put(v, null);
        }

        findGoodWay(graph);

    }

    public static void findGoodWay(Graph graph) {
        //Which vertexes are in the graph
        ArrayList<Vertex> vertexes = (ArrayList<Vertex>) graph.getVertices();
        var temp = new ArrayList<Vertex>();
        String result = "";

        //Check each vertex
        for(Vertex v : vertexes) {
            //if vertex have neighbors
            if (v.getNeighbors().size() > 0) {
                ArrayList<Vertex> neighbors = (ArrayList<Vertex>) v.getNeighbors();
                //find cost of each edge
                for (Vertex n : neighbors) {
                    //if value in minimalCost is more than cost refresh it to value
                    String edgeName = v.getName() + n.getName();
                    int cost = graph.getGraph().get(edgeName);
                    if (cost < minimalCost.get(n)) {
                        minimalCost.put(n, cost);
                        //in parents refresh value for neighbor key
                        parent.put(n, v);
                    }
                }
            }
        }
        //till parent not a first vertex in list
        Vertex vertex = vertexes.get(vertexes.size() - 1);
        temp.add(vertex);
        while (!vertex.equals(vertexes.get(0))) {
            //for last vertex in list find parent and add it's name to result
            temp.add(parent.get(vertex));
            vertex = parent.get(vertex);
        }

        //print result
        for (int i = temp.size() - 1; i >= 0; i--) {
            result += temp.get(i).getName() + " ";
        }
        System.out.println(result);
    }
}
