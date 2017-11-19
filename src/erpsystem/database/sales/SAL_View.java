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

public class SAL_View extends SalesDatabase{

    public ObservableList<Map> select_sales(){
        ObservableList<Map> table_data = FXCollections.observableArrayList();
        String Query = "SELECT sales.[sale_id],customer.[cst_name],customer.[cst_lastname],"
                     + "customer.[cst_phone],sales.[sal_paymentMethod],sales.[sal_finalPrice],"
                     + "sales.[sal_date]"
                     + "FROM [sales] "
                     + "INNER JOIN [customer] ON sales.cst_id=customer.cst_id "
                     + "WHERE customer.cst_isCompany=0;";
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(Query);
                while(rs.next()){
                    Map<String,String> row = new HashMap<>();
                       row.put("sale_id",String.valueOf(rs.getInt("sale_id")));
                       row.put("cst_name", rs.getString("cst_name"));
                       row.put("cst_lastname", rs.getString("cst_lastname"));
                       row.put("cst_phone", rs.getString("cst_phone"));
                       row.put("sal_paymentMethod",rs.getString("sal_paymentMethod"));
                       row.put("sal_finalPrice", String.valueOf(rs.getFloat("sal_finalPrice")));
                       row.put("sal_date", String.valueOf(rs.getTimestamp("sal_date")));
                   table_data.add(row);
                }
               Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        return table_data;
    }
    public ObservableList<Map> select_sales_c(){
        ObservableList<Map> table_data = FXCollections.observableArrayList();
        String Query = "SELECT sales.[sale_id],customer.[cst_name],customer.[cst_phone],"
                     + "sales.[sal_paymentMethod],sales.[sal_finalPrice],sales.[sal_date]"
                     + "FROM [sales] "
                     + "INNER JOIN [customer] ON sales.cst_id=customer.cst_id "
                     + "WHERE customer.cst_isCompany=-1;";
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(Query);
                while(rs.next()){
                    Map<String,String> row = new HashMap<>();
                        row.put("sale_id",String.valueOf(rs.getInt("sale_id")));
                        row.put("cst_name", rs.getString("cst_name"));
                        row.put("cst_phone", rs.getString("cst_phone"));
                        row.put("sal_paymentMethod",rs.getString("sal_paymentMethod"));
                        row.put("sal_finalPrice", String.valueOf(rs.getFloat("sal_finalPrice")));
                        row.put("sal_date", String.valueOf(rs.getTimestamp("sal_date")));
                    table_data.add(row);
                }
                Disconnect();
             }catch(SQLException e){
                 e.printStackTrace();
             }
        return table_data;
    }
    public ObservableList<Map> select_specific_sale(int id){
        ObservableList<Map> data = FXCollections.observableArrayList();
        String Query = "SELECT sales.[sale_id],customer.[cst_name],customer.[cst_lastname],"
                     + "customer.[cst_sex],customer.[cst_customerType],customer.[cst_address],"
                     + "customer.[cst_zipcode],customer.[cst_city],customer.[cst_state],"
                     + "customer.[cst_phone],customer.[cst_fax],sales.[sal_paymentMethod],"
                     + "customer.[cst_mail],sales.[sal_finalPrice],sales.[sal_finalPrice],"
                     + "sales.[sal_date]"
                     + "FROM sales "
                     + "INNER JOIN [customer] ON sales.cst_id = customer.cst_id "
                     + "WHERE sales.sale_id = "+id;
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(Query);
                while(rs.next()){
                    Map<String,String> row = new HashMap<>();
                       row.put("sale_id",String.valueOf(rs.getInt("sale_id")));
                       row.put("cst_name", rs.getString("cst_name"));
                       row.put("cst_lastname", rs.getString("cst_lastname"));
                       row.put("cst_sex",rs.getString("cst_sex"));
                       row.put("cst_customerType",rs.getString("cst_customerType"));
                       row.put("cst_address",rs.getString("cst_address"));
                       row.put("cst_zipcode",String.valueOf(rs.getInt("cst_zipcode")));
                       row.put("cst_city",rs.getString("cst_city"));
                       row.put("cst_state",rs.getString("cst_state"));
                       row.put("cst_phone", rs.getString("cst_phone"));
                       row.put("cst_fax",rs.getString("cst_fax"));
                       row.put("sal_paymentMethod",rs.getString("sal_paymentMethod"));
                       row.put("cst_mail",rs.getString("cst_mail"));
                       row.put("sal_finalPrice", String.valueOf(rs.getFloat("sal_finalPrice")));
                       row.put("sal_date", String.valueOf(rs.getTimestamp("sal_date")));
                    data.add(row);
                }
               Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        
        return data;
    }
    
}
