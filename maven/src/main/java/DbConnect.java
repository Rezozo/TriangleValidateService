import java.sql.*;

public class DbConnect {
    private static final String URL = "jdbc:postgresql://localhost:5433/Triangles";
    private static final String USERNAME = "admin1";
    private static final String PASSWORD = "0987";
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
