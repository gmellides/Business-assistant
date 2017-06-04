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
import erpsystem.entities.people.Contact;
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
                Database contacts_database = new DatabaseBuilder(new File("db/Contacts-Test.accdb").getAbsoluteFile())
                                                   .open();
                Table contacts_table = contacts_database.getTable("Contacts");
                for (Row contact : contacts_table){
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
    public boolean insert_customer(Contact input){
        boolean flag = false;
            try{
                Database contacts_database = new DatabaseBuilder(new File("db/Contacts-Test.accdb").getAbsoluteFile())
                                           .open();
               
                Table contacts_table = contacts_database.getTable("Contacts");
                contacts_table.addRow(Column.AUTO_NUMBER,
                                     input.getFirstName(),
                                     input.getLastName(),
                                     input.getSex(),
                                     input.getAddress(),
                                     input.getZipCode(),
                                     input.getCountry(),
                                     input.getState(),
                                     input.getCity(),
                                     input.getMail(),
                                     input.getPhone_1(),
                                     input.getPhone_1_type(),
                                     input.getPhone_2(),
                                     input.getPhone_2_type(),
                                     input.getComments(),
                                     input.getWebsite(),
                                     input.getImport_date()); 
                contacts_database.close();   
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
                Database contacts_database = new DatabaseBuilder(new File("db/Contacts-Test.accdb").getAbsoluteFile())
                                                 .open();
                Table contacts_table = contacts_database.getTable("Contacts");
                for (Row contact : contacts_table){
                  Map<String,String> row = new HashMap<>();
                    row.put("firstname", contact.getString("firstname"));
                    row.put("lastname", contact.getString("lastname"));
                    row.put("sex", contact.getString("sex"));
                    row.put("address", contact.getString("address"));
                    row.put("zipcode", String.valueOf(contact.getInt("zipcode")));
                    row.put("country", contact.getString("country"));
                    row.put("greek_state", contact.getString("greek_state"));
                    row.put("city", contact.getString("city"));
                    row.put("mail", contact.getString("mail"));
                    row.put("phone1", contact.getString("phone1"));
                    row.put("phone2", contact.getString("phone2"));          
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
                Database Contacts = new DatabaseBuilder(new File("db/Contacts-Test.accdb").getAbsoluteFile())
                                    .open();
                Table contact_tbl = Contacts.getTable("Contacts");
               
                for (Row item : contact_tbl){
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
