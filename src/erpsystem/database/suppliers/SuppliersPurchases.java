/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.suppliers;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SuppliersPurchases extends SuppliersDatabase{
    public ObservableList<String> select_Indevidual_combobox(){
        ObservableList<String> data = FXCollections.observableArrayList();
            try{ 
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT spl_id,"
                                            + "spl_name,"
                                            + "spl_lastname,"
                                            + "spl_address,"
                                            + "spl_zipcode,"
                                            + "spl_city,"
                                            + "spl_phone,"
                                            + "spl_supplierType FROM supplier WHERE spl_isCompany = 0;");
                while(rs.next()){
                    data.add(String.valueOf(rs.getInt("spl_id"))+" "+
                             rs.getString("spl_name")+" "+
                             rs.getString("spl_lastname")+" ("+
                             rs.getString("spl_address")+","+
                             String.valueOf(rs.getInt("spl_zipcode"))+","+
                             rs.getString("spl_city")+","+
                             rs.getString("spl_phone")+","+
                             rs.getString("spl_supplierType")+")");
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
    public ObservableList<String> select_Companies_combobox(){
        ObservableList<String> data = FXCollections.observableArrayList();
        try{ 
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT spl_id,"
                                            + "spl_name,"
                                            + "spl_address,"
                                            + "spl_zipcode,"
                                            + "spl_city,"
                                            + "spl_phone,"
                                            + "spl_supplierType FROM supplier WHERE spl_isCompany = -1;");
            while(rs.next()){
                data.add(String.valueOf(rs.getInt("spl_id"))+" "+
                         rs.getString("spl_name")+" ("+
                             rs.getString("spl_address")+","+
                             String.valueOf(rs.getInt("spl_zipcode"))+","+
                             rs.getString("spl_city")+","+
                             rs.getString("spl_phone")+","+
                      rs.getString("spl_supplierType")+")");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return data;
    }
}
