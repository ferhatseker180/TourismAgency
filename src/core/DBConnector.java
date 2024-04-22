package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DBConnector {
    private Connection connection = null;
    private static DBConnector instance = null;
    private final static String PROJECT_TITLE = "Tourism Agency";
    private final static String DB_URL = "jdbc:postgresql://localhost:5432/tourismagencyproject";
    private final static String DB_USERNAME = "postgres";
    private final static String DB_PASSWORD = "123456789";

    private DBConnector() {
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

        } catch (Exception e) {
            Helper.showMsg("Error", "Database Connection Error");
        }
    }

    // Get the current connection instance
    public Connection getConnection() {
        return connection;
    }

    // Get a singleton instance of the database connection
    public static Connection getConnectionInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DBConnector();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return instance.getConnection();
    }

    // Get a PreparedStatement for a given SQL query
    public static PreparedStatement getPreparedStatement(String query) {
        PreparedStatement ps = null;
        try {
            ps = DBConnector.getConnectionInstance().prepareStatement(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ps;
    }
}
