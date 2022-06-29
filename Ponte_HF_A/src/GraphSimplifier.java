import java.sql.SQLException;
import java.util.ArrayList;

public class GraphSimplifier {

    private ArrayList<Point> points;
    private final DataBaseManager dataBaseManager;

    public GraphSimplifier(){
        try {
            dataBaseManager = new DataBaseManager();
            points = dataBaseManager.readRecords();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void simplify() {
        ArrayList<Point> connectionPoints = new ArrayList<>();
        for(Point point: points) {
            if(point.getEdges() == 2) {
                Point start = point.getNeighbors().get(0);
                Point end = point.getNeighbors().get(1);

                start.addNeighbor(end);
                start.deleteNeighbor(point);
                end.addNeighbor(start);
                end.deleteNeighbor(point);

                connectionPoints.add(point);
            }
        }
        points.removeAll(connectionPoints);


        if(!dataBaseManager.tableExists("outtable")) {
            dataBaseManager.createTable("outtable", new String[]{"point_start", "point_end"}, new String[]{"INT", "INT"});
        }

        for (Point point : points) {
            for(Point neighbor: point.getNeighbors()) {
                dataBaseManager.addRecord("outtable",point, neighbor);
                if(neighbor.getId() != point.getId()) neighbor.deleteNeighbor(point);
            }
            point.getNeighbors().removeAll(point.getNeighbors());
        }
        dataBaseManager.closeConnection();
    }
}
