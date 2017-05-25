/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseproject;

/**
 *
 * @author yasse
 */
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
public class javaConnectDb {
    
    public static Connection ConnecrDb(){
        
        try{
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@db.ciu.edu.tr:1521:db","c##20131196","p20131196");
            return con;
        }
        
        catch(Exception e){
            
            JOptionPane.showMessageDialog(null,e );            
        }
        return null;
        
    }
    
}
