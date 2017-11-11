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
                         + "INNER JOIN [supplier] ON supplier.spl_id=purchase.spl_id "
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
}
