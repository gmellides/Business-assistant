/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.contacts;

import com.healthmarketscience.jackcess.Row;
import erpsystem.entities.people.Contact;
import erpsystem.util.datetime.DateTimeProvider;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ContactsConnection {
    
    private final String INSERT_QUERY = "INSERT INTO Contacts(contact_id,firstname,lastname,sex,address,zipcode,"
                                        + "country,greek_state,city,mail,phone1,phone1_type,phone2,"
                                        + "phone2_type,comments,website,import_date)"
                                        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
    private final String COUNT_QUERY = "SELECT COUNT(*) FROM Contacts;";
    private final String SELECT_QUERY = "SELECT * FROM Contacts;";
    private final String UPDATE_QUERY = "UPDATE Contacts "
                                        + "COLUMN .... "
                                        + "WHERE contact_id = ?";
   
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/Contacts_t.accdb").getAbsolutePath();
    private final String Username = null;
    private final String Password = null;
    
    public Connection connection;
    public Statement statement;
    public ResultSet rs;
    public PreparedStatement insert_statement;
 
    private void Connect(){       
            try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                connection = DriverManager.getConnection(database_driver+database_path);
            }catch(ClassNotFoundException|SQLException e){
                e.printStackTrace();
            }
    }
        public boolean insert_contact(Contact input){
            boolean flag = false;
                try{
                    Connect();
                    insert_statement = connection.prepareStatement(INSERT_QUERY);
                        insert_statement.setInt(1, 0);
                        insert_statement.setString(2, input.getFirstName());
                        insert_statement.setString(3, input.getLastName());
                        insert_statement.setString(4, input.getSex());
                        insert_statement.setString(5, input.getAddress());
                        insert_statement.setInt(6, input.getZipCode());
                        insert_statement.setString(7, input.getCountry());               
                        insert_statement.setString(8, input.getState());
                        insert_statement.setString(9, input.getCity());
                        insert_statement.setString(10, input.getMail());
                        insert_statement.setString(11, input.getPhone_1());
                        insert_statement.setString(12, input.getPhone_1_type());
                        insert_statement.setString(13, input.getPhone_2());
                        insert_statement.setString(14, input.getPhone_2_type());
                        insert_statement.setString(15, input.getComments());
                        insert_statement.setString(16, input.getWebsite());
                        insert_statement.setTimestamp(17, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                    flag = insert_statement.execute();
                    Disconnect();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return flag;
        }
        public boolean update_contact(Contact input){return true;}
        public boolean delete_contact(){return true;}
        public int count_contacts(){
            int contacts = 0;
                try{
                    Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(COUNT_QUERY);
                    while (rs.next()){
                        contacts = rs.getInt(1);
                    }
                    Disconnect();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return contacts;
        }
        public ObservableList<Map> select_contacts(){
            ObservableList<Map> table_data = FXCollections.observableArrayList();
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(SELECT_QUERY);
                while (rs.next()){
                    Map<String,String> row_data = new HashMap();
                        row_data.put("contact_id", rs.getString("contact_id"));
                        row_data.put("firstname", rs.getString("firstname"));
                        row_data.put("lastname", rs.getString("lastname"));
                        row_data.put("sex", rs.getString("sex"));
                        row_data.put("address", rs.getString("address"));
                        row_data.put("zipcode", String.valueOf(rs.getInt("zipcode")));
                        row_data.put("country", rs.getString("country"));
                        row_data.put("greek_state", rs.getString("greek_state"));
                        row_data.put("city", rs.getString("city"));
                        row_data.put("phone1", rs.getString("phone1"));
                        row_data.put("phone1_type", rs.getString("phone1_type"));
                        row_data.put("phone2", rs.getString("phone2"));
                        row_data.put("phone2_type", rs.getString("phone2_type"));
                        row_data.put("comments", rs.getString("comments"));
                        row_data.put("website", rs.getString("website"));
                        row_data.put("import_date", String.valueOf(rs.getDate("import_date")));
                    table_data.add(row_data);
                }
            }catch(SQLException e){
                e.printStackTrace();
            }


            return table_data;
        }   
        
    private void Disconnect(){
        try{
            if (connection != null)
                connection.close();
            if (statement != null)
                statement.close();
            if (rs != null)
                rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
