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
                rs = statement.executeQuery("SELECT supl_person_id,"
                                            + "firstname,"
                                            + "lastname,"
                                            + "address,"
                                            + "zipcode,"
                                            + "city,"
                                            + "phone,"
                                            + "supplier_type FROM Suppliers_Person;");
                while(rs.next()){
                    data.add(String.valueOf(rs.getInt("supl_person_id"))+" "+
                             rs.getString("firstname")+" "+
                             rs.getString("lastname")+" ("+
                             rs.getString("address")+","+
                             String.valueOf(rs.getInt("zipcode"))+","+
                             rs.getString("city")+","+
                             rs.getString("phone")+","+
                             rs.getString("supplier_type")+")");
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
                rs = statement.executeQuery("SELECT supl_company_id,"
                                            + "name,"
                                            + "address,"
                                            + "zipcode,"
                                            + "city,"
                                            + "phone,"
                                            + "supplier_type FROM Supplier_Company;");
            while(rs.next()){
                data.add(String.valueOf(rs.getInt("supl_company_id"))+" "+
                         rs.getString("name")+" ("+
                             rs.getString("address")+","+
                             String.valueOf(rs.getInt("zipcode"))+","+
                             rs.getString("city")+","+
                             rs.getString("phone")+","+
                      rs.getString("supplier_type")+")");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return data;
    }
}
