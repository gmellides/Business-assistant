/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CST_Sales extends CST_Database{
    /**
     * Data from database for ComboBox on Sales Window. 
     * For Customers.
     * @return 
     */
    public ObservableList<String> select_cst_individual(){
        ObservableList<String> data = FXCollections.observableArrayList();
            try{
                Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT cst_id,cst_name,cst_lastname,"
                                                + "cst_address,cst_zipcode,cst_city,cst_phone"
                                                + " FROM customer WHERE cst_isCompany = 0;");
                    while (rs.next()){
                        data.add(String.valueOf(rs.getInt("cst_id"))+" "+
                                                rs.getString("cst_name")+" "+
                                                rs.getString("cst_lastname")+" ("+
                                                rs.getString("cst_address")+","+
                                                rs.getInt("cst_zipcode")+","+
                                                rs.getString("cst_city")+","+
                                                rs.getString("cst_phone")+")");  
                    }
                Disconnect();    
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
    /**
     * Data from database for ComboBox on Sales Window. 
     * For Customers Companies.
     * @return 
     */
    public ObservableList<String> select_cst_companies(){
        ObservableList<String> data = FXCollections.observableArrayList();
            try{
                Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT cst_id,cst_name,cst_address,cst_zipcode,"
                                               + "cst_city,cst_customerType,cst_phone "
                                               + "from customer WHERE cst_isCompany = -1;");
                    while (rs.next()){
                        data.add(String.valueOf(rs.getInt("cst_id"))+" "
                                                +rs.getString("cst_name")+" ("
                                                +rs.getString("cst_address")+","
                                                +rs.getInt("cst_zipcode")+","
                                                +rs.getString("cst_city")+","
                                                +rs.getString("cst_customerType")+","
                                                +rs.getString("cst_phone")+")");  
                    }
                Disconnect();    
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
}
