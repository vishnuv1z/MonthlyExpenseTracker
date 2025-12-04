package db;
import java.sql.*;
public class DBConnection {
    private static Connection conn;
    
    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/monthly_expense", "root", "root");
            System.out.println("Connected to Database ✅");
        }
        return conn;
    }
}