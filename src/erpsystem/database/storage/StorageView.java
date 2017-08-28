/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.storage;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class StorageView extends StorageDatabase{
    
    
    public ObservableList<Map> select_products(){
        ObservableList<Map> table_data = FXCollections.observableArrayList();
            try{
                Connect();
                statement = connection.createStatement();
                results = statement.executeQuery("SELECT * FROM Products WHERE prd_quantity>0");
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
    
    
}
