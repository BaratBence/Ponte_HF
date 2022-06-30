import exception.WrongArgumentException;

import java.sql.*;
import java.util.ArrayList;

//Class that manages the database
public class DataBaseManager {
    private Connection connection = null;

    //creates the connection upon creating the class
    public DataBaseManager() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "admin");
    }


    //Creates a table with the given columns and table name
    public void createTable(String tableName, String[] columnName, String[] type) {
        try {
            if(columnName.length != type.length) throw new WrongArgumentException();
            Statement statement = connection.createStatement();

            StringBuilder sql = new StringBuilder("CREATE TABLE " + tableName +
                    "(ID SERIAL PRIMARY KEY NOT NULL,");
            for(int i =0; i<columnName.length; i++) {
                sql.append(" ").append(columnName[i]).append(" ").append(type[i].toUpperCase()).append(" NOT NULL");
                if(i+1 != columnName.length) sql.append(",");
            }
            sql.append(")");
            statement.executeUpdate(sql.toString());
            statement.close();
        } catch (Exception e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
            System.exit(0);
        }
    }

    //Inserts a record in to the database
    public void addRecord(String tableName, Point start, Point end) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO " + tableName + " (point_start, point_end)" +
                     " VALUES (" + start.getId() + " , " + end.getId() +")";
            statement.executeUpdate(sql);
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //reads all the given input graph
    public ArrayList<Point> readRecords() {
        ArrayList<Point> points = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery( "SELECT * FROM testtable;" );
            while (result.next()) {
                int startId = result.getInt("point_start");
                int endId = result.getInt("point_end");

                //if the point does not exist creates one
                if(getPoint(startId, points)==null) {
                    points.add(new Point(startId));
                }
                if(getPoint(endId, points)==null) {
                    points.add(new Point(endId));
                }
                //sets the neighbors for the points
                Point startPoint = getPoint(startId, points);
                Point endPoint = getPoint(endId, points);
                startPoint.addNeighbor(endPoint);
                endPoint.addNeighbor(startPoint);
            }
            statement.close();
        } catch (Exception e) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
        return points;
    }
    //checks if the table with the given exists
    public boolean tableExists(String tableName) {
        try {
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet result = meta.getTables(null, null, tableName, new String[]{"TABLE"});
            return result.next();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //closes the database connection
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //returns a certain point with the given id from a list of points
    private Point getPoint(int id, ArrayList<Point> points){
        for(Point point: points) {
            if(point.getId() == id) return point;
        }
        return null;
    }
}
