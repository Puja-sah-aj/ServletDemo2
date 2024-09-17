package conn.text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbConnection {

    // Constructor to load the MySQL driver
    public DbConnection() {
        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to establish a connection with the database
    public Connection getConnection() throws SQLException {
        // Provide your database URL, username, and password
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/dbconnect", "root", "Puja2002");
    }

    // Method to save user details into the database
    public void saveUser(String name, String email, String dob, String gender, String phone, String train, 
                         String Class, String departure, String departure_time, String arrival_time) {

        String sql = "INSERT INTO register(name, email, dob, gender, phone, train, class, departure, departure_time, arrival_time) "
                   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Using try-with-resources to ensure the connection and statement are closed automatically
        try{
        	Connection conn = getConnection();
        	PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1, name);
            pre.setString(2, email);
            pre.setString(3, dob);
            pre.setString(4, gender);
            pre.setString(5, phone);
            pre.setString(6, train);
            pre.setString(7, Class);
            pre.setString(8, departure);
            pre.setString(9, departure_time);
            pre.setString(10, arrival_time);

            int rowsAffected = pre.executeUpdate();  // Execute the query

            // Check if the insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Registration successful.");
            } else {
                System.out.println("Registration failed.");
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception
        }
    }
}
