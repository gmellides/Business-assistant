/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.products;

import erpsystem.entities.product.Product;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PRD_Database {
    
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/app_data.accdb").getAbsolutePath();
    
    protected Connection connection;
    protected Statement statement;
    protected ResultSet rs;
    protected PreparedStatement prepered_stm;
    
    protected void Connect(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection(database_driver+database_path);
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }       
    }
        public void insert_product(Product input){
            
        }
        public void select_products(){
            
        }
        public int[] count_products(){
            int[] values = null;
                try{
                    Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT COUNT(*) FROM Products");
                    while (rs.next()){
                        values[0] = rs.getInt(1);
                    }
                    rs = null;
                    rs = statement.executeQuery("SELECT COUNT(*) FROM Products WHERE quantity > 0");
                    while (rs.next()){
                        values[1] = rs.getInt(1);
                    }
                    rs = null;
                    rs = statement.executeQuery("SELECT COUNT(*) FROM Products WHERE quantity > 0 and quantity <=10");
                    while (rs.next()){
                        values[2] = rs.getInt(1);
                    }
                    Disconnect();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return values;
        }
    protected void Disconnect() throws SQLException{
        if (connection != null)
            connection.close();
        
        if (prepered_stm !=null)
            prepered_stm.close();
        
        if (statement != null)
            statement.close();
        
        if (rs != null)
            rs.close();
    }
}
