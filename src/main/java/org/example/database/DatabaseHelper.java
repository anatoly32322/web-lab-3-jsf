package org.example.database;

import org.example.model.PointEntry;
import java.sql.*;

import java.util.ArrayList;

public class DatabaseHelper {
    private String jdbcURL = "jdbc:postgresql://localhost:9999/studs";
    private String username = "s311710";
    private String password = "lfd100";
    private Connection connection;
    private static final String INSERT_POINT = "INSERT INTO RESULTS (x, y, r, time, conclusion) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ALL = "SELECT * FROM RESULTS";

    public DatabaseHelper() {
        connectionToDatabase();
    }

    private void connectionToDatabase() {
        try {
            connection = DriverManager.getConnection(jdbcURL, username, password);
        } catch (SQLException err){
            err.printStackTrace();
            System.err.println("Ты совсем еблан? Как можно было не подключиться?");
            System.exit(-1);
        }
    }

    public void save(PointEntry entry) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_POINT);
        statement.setInt(1, entry.getX());
        statement.setDouble(2, entry.getY());
        statement.setInt(3, entry.getR());
        statement.setString(4, entry.getCurrentTime());
        statement.setBoolean(5, entry.isHit());
        statement.executeUpdate();
        statement.close();
    }

    public ArrayList<PointEntry> getAll() throws SQLException {

        PreparedStatement statement = connection.prepareStatement(GET_ALL);
        ResultSet result = statement.executeQuery();
        ArrayList<PointEntry> resultPoints = new ArrayList<>();
        while (result.next()){
            resultPoints.add(extractPoint(result));
        }
        result.close();
        return resultPoints;
    }

    private PointEntry extractPoint(ResultSet point) throws SQLException {
        int x = point.getInt("x");
        double y = point.getDouble("y");
        int r = point.getInt("r");
        String time = point.getString("time");
        boolean isHit = point.getBoolean("conclusion");
        PointEntry resultPoint = new PointEntry(x, y, r, time, isHit);
        return resultPoint;
    }
}