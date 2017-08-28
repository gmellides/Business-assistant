/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.storage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StorageDatabase {
    
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/data.accdb").getAbsolutePath();
    
    private final String COUNT_PROD_QUERY = "SELECT COUNT(*) FROM Products";
    private final String SELECT_PROD_QUERY = "SELECT * FROM Products";
    
    protected Connection connection;
    protected Statement statement;
    protected ResultSet results;
    
    protected void Connect(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection(database_driver+database_path);
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
        public ObservableList<Map> select_products(){
            ObservableList<Map> table_data = FXCollections.observableArrayList();
                try{
                    Connect();
                    statement = connection.createStatement();
                    results = statement.executeQuery(SELECT_PROD_QUERY);
                    while (results.next()){
                        Map<String,String> row = new HashMap();
                        table_data.add(row);
                    }
                    Disconnect();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return table_data;
        }
        public int count_products(){
            int Products = 0;
            try{
                Connect();
                statement = connection.createStatement();
                results = statement.executeQuery(COUNT_PROD_QUERY);
                while(results.next()){
                    Products = results.getInt(1);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            return Products;
        }
    protected void Disconnect(){
        try{
        if (connection != null)
            connection.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
