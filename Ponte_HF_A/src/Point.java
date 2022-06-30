import java.util.ArrayList;

//Class to represent each point in the graph
public class Point {
    //name of the point
    private final int id;
    //number of edges
    private int edges;
    private ArrayList<Point> neighbors;

    public Point(int id) {
        this.id = id;
        edges = 0;
        neighbors = new ArrayList<>();
    }

    public void addNeighbor(Point point) {
        edges++;
        neighbors.add(point);
    }

    public void deleteNeighbor(Point point) {
        int i =0;
        while (neighbors.get(i).id != point.id) {
            i++;
        }
        neighbors.remove(i);
        edges--;
    }

    public int getEdges() {
        return edges;
    }

    public ArrayList<Point> getNeighbors() {
        return neighbors;
    }

    public int getId() {
        return id;
    }
}
