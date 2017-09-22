/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.sales;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;

public class SalesDatabase {
    
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/app_data.accdb").getAbsolutePath();
    private final String Username = null;
    private final String Password = null;
    
    private Connection connection;
    private Statement statement;
    private ResultSet rs;
    
    private void Connect(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection(database_driver+database_path);
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
        public void get_customers_person(){}
        public void get_customers_companies(){}
        public void get_products(){
           
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM products");
                while(rs.next()){
                    
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    private void Disconnect(){
        
    }
}
