/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.sales;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReceiptDatabase extends SalesDatabase{
    public String get_cstName(int sale_id){
        String data = null;
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT customer.cst_name,customer.cst_lastname"
                        + " FROM sales INNER JOIN customer ON sales.cst_id = customer.cst_id "
                        + "WHERE sales.sale_id ="+sale_id);
                while(rs.next()){
                    data = rs.getString("cst_name")+""+rs.getString("cst_lastname");
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
    public ObservableList<Map> get_cstInfo(int sale_id){
        ObservableList<Map> data = FXCollections.observableArrayList();
        // SELECT cst_name,cst_lastname,cst
        return data;
    }
    public ObservableList<Map> get_prdInfo(int sale_id){
        ObservableList<Map> data = FXCollections.observableArrayList();
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT product.prd_name,prd_category,"
                        + "sal_prd.sal_quantity,product.prd_sellPrice,sal_prd.sal_price"
                        + " FROM sal_prd INNER JOIN product ON sal_prd.prd_id = product.prd_id"
                        + " WHERE sale_id = "+sale_id);
                while(rs.next()){
                    Map<String,String> row = new HashMap<>(); 
                        row.put("prd_name", rs.getString("prd_name"));
                        row.put("prd_category", rs.getString("prd_category"));
                        row.put("sal_quantity", String.valueOf(rs.getInt("sal_quantity")));
                        row.put("prd_sellPrice", String.valueOf(rs.getFloat("prd_sellPrice")));
                        row.put("sal_price", String.valueOf(rs.getFloat("sal_price")));
                    data.add(row);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
}
