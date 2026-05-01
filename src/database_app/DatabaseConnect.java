package database_app;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnect {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url, user, password);
    }
}
