package Assgt1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLExceptionExample {
    public static void main(String[] args) {
        try {
            // Attempt to connect to a non-existent database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nonexistentdb", "user", "password");
        } catch (SQLException e) {
            // Handle the exception
            System.out.println("SQLException caught: " + e.getMessage());
        }
    }
}

