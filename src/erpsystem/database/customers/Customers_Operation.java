/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import erpsystem.entities.people.Customer;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class Customers_Operation {
    /**
     * Returns the number of saved contacts.
     * @return 
     */
    public int count_customers(){
        int saved_contacts = 0;
            try{
                Database customers_database = new DatabaseBuilder(new File("db/Customers-Test.accdb").getAbsoluteFile())
                                                   .open();
                Table customers_table = customers_database.getTable("Customers");
                for (Row customer : customers_table){
                  saved_contacts++;
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        return saved_contacts;
    }
    /**
     * Insert data into contacts Database.
     * @param input
     * @return 
     */
    public boolean insert_customer(Customer input){
        boolean flag = false;
            try{
                Database customers_database = new DatabaseBuilder(new File("db/Customers-Test.accdb").getAbsoluteFile())
                                                 .open();
                Table customers_table = customers_database.getTable("Customers");
                customers_table.addRow(Column.AUTO_NUMBER
                                      ); 
                customers_database.close();   
                flag = true;
            }catch(IOException e){
                e.printStackTrace();
            }
        return flag;
    }
    /**
     * Returns data on observable list for the table view.
     * @return 
     */
    public ObservableList<Map> select_table_data(){
        ObservableList<Map> table_data = FXCollections.observableArrayList();
            try{
                Database customers_database = new DatabaseBuilder(new File("db/Customers-Test.accdb").getAbsoluteFile())
                                                 .open();
                Table customers_table = customers_database.getTable("Customers");
                for (Row customer : customers_table){
                  Map<String,String> row = new HashMap<>();
                    row.put("firstname", customer.getString("firstname"));
                    row.put("lastname", customer.getString("lastname"));
                    row.put("sex", customer.getString("sex"));
                    row.put("address", customer.getString("address"));
                    row.put("zipcode", String.valueOf(customer.getInt("zipcode")));
                    row.put("mail", customer.getString("mail"));
                    row.put("phone1", customer.getString("phone1"));
                    row.put("phone2", customer.getString("phone2"));          
                 table_data.add(row);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        return table_data;
    }
    /**
     * returns all data from the database for creating the CSV file
     * @return 
     */
    public ArrayList<Row> select_data_csv(){
        ArrayList<Row> data = new ArrayList<>();
            try{
                Database customers_database = new DatabaseBuilder(new File("db/Customers-Test.accdb").getAbsoluteFile())
                                    .open();
                Table customers_table = customers_database.getTable("Customers");
               
                for (Row item : customers_table){
                    data.add(item);
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        return data;
    }
    
    public void update_contact(){
        
    }
    public void delete_contact(){
    }

}
