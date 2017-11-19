/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.purchases;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PRC_View extends PRC_Database{
    public ObservableList<Map> select_purchases(){
        ObservableList<Map> data = FXCollections.observableArrayList();
            String query = "SELECT purchases.[prc_id],supplier.[spl_name],supplier.[spl_lastname],"
                         + "supplier.[spl_address],supplier.[spl_zipcode],supplier.[spl_city],"
                         + "supplier.[spl_phone],"
                         + "purchases.[prc_paymentMethod],purchases.[prc_finalPrice]"
                         + "FROM [purchases] "
                         + "INNER JOIN [supplier] ON supplier.spl_id=purchases.spl_id "
                         + "WHERE supplier.spl_isCompany=0;";
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while(rs.next()){
                    Map<String,String> row = new HashMap<>();
                       row.put("prc_id",String.valueOf(rs.getInt("prc_id")));
                       row.put("spl_name", rs.getString("spl_name"));
                       row.put("spl_lastname", rs.getString("spl_lastname"));
                       row.put("spl_address", rs.getString("spl_address"));
                       row.put("spl_zipcode",String.valueOf(rs.getInt("spl_zipcode")));
                       row.put("spl_city",rs.getString("spl_city"));
                       row.put("spl_phone", rs.getString("spl_phone"));
                       row.put("prc_paymentMethod", rs.getString("prc_paymentMethod"));
                       row.put("prc_finalPrice", String.valueOf(rs.getFloat("prc_finalPrice")));
                    data.add(row);
                }
               Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
    public ObservableList<Map> select_purchases_c(){
        ObservableList<Map> data = FXCollections.observableArrayList();
            String query = "SELECT purchases.[prc_id],supplier.[spl_name],"
                         + "supplier.[spl_address],supplier.[spl_zipcode],supplier.[spl_city]"
                         + "supplier.[spl_phone],"
                         + "purchases.[prc_paymentMethod],purchases.[prc_finalPrice]"
                         + "FROM [purchases] "
                         + "INNER JOIN [supplier] ON supplier.spl_id=purchases.spl_id "
                         + "WHERE supplier.spl_isCompany=-1;";
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while(rs.next()){
                    Map<String,String> row = new HashMap<>();
                       row.put("prc_id",String.valueOf(rs.getInt("prc_id")));
                       row.put("spl_name", rs.getString("spl_name"));
                       row.put("spl_address", rs.getString("spl_address"));
                       row.put("spl_zipcode",String.valueOf(rs.getInt("spl_zipcode")));
                       row.put("spl_city",rs.getString("spl_city"));
                       row.put("spl_phone", rs.getString("spl_phone"));
                       row.put("prc_paymentMethod", rs.getString("prc_paymentMethod"));
                       row.put("prc_finalPrice", String.valueOf(rs.getFloat("prc_finalPrice")));
                    data.add(row);
                }
               Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
    public ObservableList<Map> select_spacific_purchase(int id){
        ObservableList<Map> data = FXCollections.observableArrayList();
        String Query = "SELECT supplier.[spl_name],supplier.[spl_lastname],"
                     + "supplier.[spl_phone],supplier.[spl_fax],supplier.[spl_mail],"
                     + "supplier.[spl_address],supplier.[spl_city],supplier.[spl_state],"
                     + "supplier.[spl_zipcode],supplier.[spl_bank],supplier.[spl_IBAN],"
                     + "supplier.[spl_supplierType],product.[prd_name],product.[prd_quantity],"
                     + "product.[prd_category],product.[prd_description],product.[prd_prcPrice],"
                     + "product.[prd_sellPrice],purchases.[prc_paymentMethod]"
                     + "FROM [purchases]"
                     + "INNER JOIN [supplier] ON purchases.spl_id=supplier.spl_id "
                + "JOIN [product] ON purchases.[prd_id]=product.[prd_id]"
                     + "WHERE purchases.[prc_id]= "+id;
        try{
            Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(Query);
                while(rs.next()){
                    Map<String,String> row = new HashMap<>();
                        row.put("spl_name", rs.getString("spl_name"));
                        row.put("spl_lastname", rs.getString("spl_lastname"));
                        row.put("spl_mail", rs.getString("spl_mail"));
                        row.put("spl_phone", rs.getString("spl_phone"));
                        row.put("spl_fax", rs.getString("spl_fax"));                        
                        row.put("spl_address", rs.getString("spl_address"));
                        row.put("spl_city", rs.getString("spl_city"));
                        row.put("spl_state", rs.getString("spl_state"));
                        row.put("spl_zipcode", String.valueOf(rs.getInt("spl_zipcode")));
                        row.put("spl_bank", rs.getString("spl_bank"));
                        row.put("spl_IBAN", rs.getString("spl_IBAN"));
                        row.put("spl_supplierType", rs.getString("spl_supplierType"));
                        row.put("prd_name", rs.getString("prd_name"));
                        row.put("prd_quantity", rs.getString("prd_quantity"));
                        row.put("prd_category", rs.getString("prd_category"));
                        row.put("prd_description", rs.getString("prd_description"));
                        row.put("prd_prcPrice", String.valueOf(rs.getFloat("prd_prcPrice")));
                        row.put("prd_sellPrice", String.valueOf(rs.getFloat("prd_sellPrice")));
                        row.put("prc_paymentMethod", rs.getString("prc_paymentMethod"));
                    data.add(row);
                }
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return data;
    }
}
