import java.sql.*;

public class DBConnection {
    public static Connection getConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/parking_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                "root",
                "2514"
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
