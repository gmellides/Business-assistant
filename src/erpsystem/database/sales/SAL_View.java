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
    /*
     String Query = "SELECT sales.sale_id,customer.cst_name,customer.cst_lastname,"
                     + "customer.cst_phone,sales.sal_paymentMethod,sales.sal_finalPrice,"
                     + "sales.sal_date"
                     + "FROM ((sales"
                     + "INNER JOIN customer ON sales.cst_id=customer.cst_id)"
                     + "WHERE customer.cst_isCompany=0;)";
    
    SELECT sales.sale_id,customer.cst_name,customer.cst_lastname,
customer.cst_phone,sales.sal_paymentMethod,sales.sal_finalPrice,
sales.sal_date
FROM sales,customer WHERE customer.cst_isCompany = 0;
    */
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
}
