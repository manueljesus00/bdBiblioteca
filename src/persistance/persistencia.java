/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistance;
import java.sql.*;
/**
 *
 * @author Manuel
 */
public class persistencia {
    private static String login = "root";
    private static String password = "AN@0505023023";
    private static String url = "jdbc:mysql://192.168.1.149/biblioteca?serverTimezone=UTC";
    private static Connection conn = null;
    
    public Connection createConnection() throws Exception{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, login, password);
        } catch(SQLException | ClassNotFoundException ex){
            System.out.println(ex);
        }
        return (conn!=null)?conn:null;
    }
    
    public void closeConnection() throws SQLException{
        try{
            conn.close();
            System.out.println("Conexion cerrada correctamente");
        }catch(SQLException ex){
            System.out.println(ex);
        }
        
    }
}
