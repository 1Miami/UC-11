/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sosrs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/sosrs";
    private static final String USER = "root"; // Substitua pelo seu usuário do MySQL
    private static final String PASSWORD = "root"; // Substitua pela sua senha do MySQL
     public static Connection getConnection() throws SQLException { 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do MySQL não encontrado!", e);
        }
        
    }
     public class Main {
    public static void main(String[] args) {
        try (Connection connection = Conexao.getConnection()) {
            System.out.println("Connected to the database!");
            // Your database operations here
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

}
