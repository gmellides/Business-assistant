/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.contacts;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.CryptCodecProvider;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import erpsystem.entities.people.Contact_c;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Database Password: %*&#contacts#&*%
 */
public class c_ContactsConnection {
    private String database_path = "jdbc:ucanaccess://"+new File("db/Contacts-Test.accdb").getAbsolutePath()+";jackcessOpener=CryptCodecOpener";
    
    //private String database_path = "jdbc:ucanaccess://"+new File("db/Contacts-Test.accdb").getAbsolutePath();
    private final String database_password = "%*&#contacts#&*%";
    private Connection database_connection;
    private Statement database_statement;
    private ResultSet database_result;
    private PreparedStatement database_prepared;
    
    
    public boolean Connect(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            database_connection = DriverManager.getConnection(database_path," ",database_password);
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
        return true;
    }
   
    public boolean insert(Contact_c input){
        boolean flag = false;
        if (Connect()){
            try{
                database_prepared = database_connection.prepareStatement("INSERT INTO Contacts(customer_id,firstname,lastname,sex,address,zipcode,country,greek_state,city,mail,phone1,phone1_type,phone2,phone2_type,comments,website,import_date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                    database_prepared.setInt(1, 0);
                    database_prepared.setString(2,input.getFirstName());
                    database_prepared.setString(3,input.getLastName());
                    database_prepared.setString(3,input.getSex());
                    database_prepared.setString(4,input.getAddress());
                    database_prepared.setInt(5,input.getZipCode());
                    database_prepared.setString(6,input.getCountry());
                    database_prepared.setString(7,input.getState());
                    database_prepared.setString(8, input.getCity());
                    database_prepared.setString(9, input.getMail());
                    database_prepared.setString(10,input.getPhone_1());
                    database_prepared.setString(11,input.getPhone_1_type());
                    database_prepared.setString(12, input.getPhone_2());
                    database_prepared.setString(13, input.getPhone_2_type());
                    database_prepared.setString(14, input.getComments());
                    database_prepared.setString(15, input.getWebsite());
                    database_prepared.setString(16, input.getImport_date());
                flag = database_prepared.execute();
                Disconnect();
            }catch(SQLException e){
                Disconnect();
                e.printStackTrace();
            }
        }
        return flag;
    }
    
    
    
    public boolean Disconnect(){
        try{
            if(database_connection != null){
                database_connection.close();
            }
            if(database_statement != null){
                database_statement.close();
            }
            if(database_result != null){
                database_result.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
