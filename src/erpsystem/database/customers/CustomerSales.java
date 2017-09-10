/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerSales extends CustomersDatabase{
    public ObservableList<String> select_cst_individual(){
        ObservableList<String> data = FXCollections.observableArrayList();
            try{
                Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT cust_customer_id,firstname,lastname,address,zipcode,city,phone from Customer_Person;");
                    while (rs.next()){
                        data.add(String.valueOf(rs.getInt("cust_customer_id"))+" "+
                                                rs.getString("firstname")+" "+
                                                rs.getString("lastname")+" ("+
                                                rs.getString("address")+","+
                                                rs.getInt("zipcode")+","+
                                                rs.getString("city")+","+
                                                rs.getString("phone")+")");  
                    }
                Disconnect();    
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
    public ObservableList<String> select_cst_companies(){
        ObservableList<String> data = FXCollections.observableArrayList();
            try{
                Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT cust_company_id,name,address,zipcode,city,customer_type,phone from Customer_Companies;");
                    while (rs.next()){
                        data.add(String.valueOf(rs.getInt("cust_company_id"))+" "
                                                +rs.getString("name")+" ("
                                                +rs.getString("name")+","
                                                +rs.getInt("zipcode")+","
                                                +rs.getString("city")+","
                                                +rs.getString("customer_type")+","
                                                +rs.getString("phone")+")");  
                    }
                Disconnect();    
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
}
