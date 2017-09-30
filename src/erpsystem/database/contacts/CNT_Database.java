/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.contacts;

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
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CNT_Database {
    
    private final String INSERT_QUERY = "INSERT INTO Contacts(contact_id,firstname,lastname,sex,address,zipcode,"
                                        + "country,greek_state,city,mail,phone1,phone1_type,phone2,"
                                        + "phone2_type,comments,website,import_date)"
                                        + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/Cnt_db.accdb").getAbsolutePath();
    private final String Username = null;
    private final String Password = null;
    
    protected Connection connection;
    protected Statement statement;
    protected ResultSet rs;
    protected PreparedStatement prepared_statement;
 
    protected void Connect(){       
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
                    prepared_statement = connection.prepareStatement(INSERT_QUERY);
                        prepared_statement.setInt(1, 0);
                        prepared_statement.setString(2, input.getFirstName());
                        prepared_statement.setString(3, input.getLastName());
                        prepared_statement.setString(4, input.getSex());
                        prepared_statement.setString(5, input.getAddress());
                        prepared_statement.setInt(6, input.getZipCode());
                        prepared_statement.setString(7, input.getCountry());               
                        prepared_statement.setString(8, input.getState());
                        prepared_statement.setString(9, input.getCity());
                        prepared_statement.setString(10, input.getMail());
                        prepared_statement.setString(11, input.getPhone_1());
                        prepared_statement.setString(12, input.getPhone_1_type());
                        prepared_statement.setString(13, input.getPhone_2());
                        prepared_statement.setString(14, input.getPhone_2_type());
                        prepared_statement.setString(15, input.getComments());
                        prepared_statement.setString(16, input.getWebsite());
                        prepared_statement.setTimestamp(17, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                    flag = prepared_statement.execute();
                    Disconnect();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return flag;
        }
        public int count_contacts(){
            int contacts = 0;
                try{
                    Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT COUNT(*) FROM Contacts;");
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
                rs = statement.executeQuery("SELECT * FROM Contacts;");
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
                        row_data.put("mail", rs.getString("mail"));
                        row_data.put("phone1", rs.getString("phone1"));
                        row_data.put("phone1_type", rs.getString("phone1_type"));
                        row_data.put("phone2", rs.getString("phone2"));
                        row_data.put("phone2_type", rs.getString("phone2_type"));
                        row_data.put("comments", rs.getString("comments"));
                        row_data.put("website", rs.getString("website"));
                        row_data.put("import_date", String.valueOf(rs.getDate("import_date")));
                    table_data.add(row_data);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return table_data;
        }   
    private void Disconnect(){
        try{
            if (connection != null)
                connection.close();
            if (prepared_statement != null)
                prepared_statement.close();
            if (statement != null)
                statement.close();
            if (rs != null)
                rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
