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
    
    public ObservableList<String> select_product_list(){
        ObservableList<String> data = FXCollections.observableArrayList();
            try{
                Connect();
                statement = connection.createStatement();
                results = statement.executeQuery("SELECT prd_id,prd_name FROM product WHERE prd_quantity>0");
                while (results.next()){
                    data.add(String.valueOf(results.getInt("prd_id"))+" "+results.getString("prd_name"));
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
    public Map<String,String> select_product_info(String id){
        Map<String,String> prd_data = new HashMap();
        try{
            Connect();
            statement = connection.createStatement();
                results = statement.executeQuery("SELECT prd_name,"
                                                + "prd_description,"
                                                + "prd_Category,"
                                                + "prd_quantity,"
                                                + "prd_prcPrice,"
                                                + "prd_sellPrice,"
                                                + "prd_VAT"
                                                + " FROM product "
                                                + "WHERE prd_quantity>0 AND prd_id = "+id);
                while (results.next()){
                    prd_data.put("prd_name",results.getString("prd_name"));
                    prd_data.put("prd_description",results.getString("prd_description"));
                    prd_data.put("prd_Category",results.getString("prd_Category"));
                    prd_data.put("prd_quantity",String.valueOf(results.getInt("prd_quantity")));
                    prd_data.put("prd_purchasePrice",String.valueOf(results.getFloat("prd_prcPrice")));
                    prd_data.put("prd_sellPrice",String.valueOf(results.getFloat("prd_sellPrice")));   
                    prd_data.put("prd_VAT", String.valueOf(results.getInt("prd_VAT")));
                }
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return prd_data;
    }
    public ObservableList<Map> select_products(){
        ObservableList<Map> table_data = FXCollections.observableArrayList();
            try{
                Connect();
                statement = connection.createStatement();
                results = statement.executeQuery("SELECT * FROM products WHERE prd_quantity>0");
                while (results.next()){
                    Map<String,String> row = new HashMap();
                        row.put("prd_id",String.valueOf(results.getInt("prd_id")));
                        row.put("prd_name",results.getString("prd_name"));
                        row.put("prd_description",results.getString("prd_description"));
                        row.put("prd_Category",results.getString("prd_Category"));
                        row.put("prd_quantity",results.getString("prd_quantity"));
                        row.put("prd_purchasePrice",results.getString("prd_purchasePrice"));
                        row.put("prd_sellPrice",results.getString("prd_sellPrice"));
                        row.put("prd_importDate",results.getString("prd_importDate"));
                    table_data.add(row);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        return table_data;
    }
    
}
