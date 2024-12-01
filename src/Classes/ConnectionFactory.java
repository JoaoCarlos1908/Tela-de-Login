package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory {
    //Atributos
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/usuario";
    private static final String USER = "root";
    private static final String PASS = "admin";
    
    //Metodos
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão: ",ex);
        }
    } 
    
    public static void closerConnection(Connection con){
        try {
                if(con != null){
                    con.close();
                }   
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closerConnection(Connection con, PreparedStatement stmt){
        closerConnection(con);
        try {
                if(stmt != null){
                    stmt.close();
                }   
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    public static void closerConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        closerConnection(con, stmt);
        try {
                if(rs != null){
                    rs.close();
                }   
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}
