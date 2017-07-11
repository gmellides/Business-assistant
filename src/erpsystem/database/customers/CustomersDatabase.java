/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import erpsystem.entities.corpotations.CustomerCompany;
import erpsystem.entities.people.Customer;
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

public class CustomersDatabase {
    
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/data.accdb").getAbsolutePath();
    private final String Username = null;
    private final String Password = null;
    
    private final String COUNT_CUST_QUERY = "SELECT COUNT(*) FROM Customer_Person";
    private final String COUNT_COMP_QUERY = "SELECT COUNT(*) FROM Customer_Companies";
    private final String CUST_QUERY = "SELECT * FROM Customer_Person";
    private final String COMP_QUERY = "SELECT * FROM Customer_Companies";
    
    private final String INSERT_CUSTOMER = "INSERT INTO Customer_Person"
    + "(customer_id,firstname,lastname,sex,address,zipcode,city,state,country,customer_type,phone,fax,mail,import_date) "
    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String INSERT_COMPANY = "INSERT INTO Customer_Companies"
    +"(company_id,name,address,zipcode,city,state,country,customer_type,phone,fax,mail,import_date) "
    +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    
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
        public boolean insert_customer(Customer input){
            boolean flag = false;
            try{
                Connect();
                insert_statement = connection.prepareStatement(INSERT_CUSTOMER);
                    insert_statement.setInt(1, 0);
                    insert_statement.setString(2, input.getFirstName());
                    insert_statement.setString(3, input.getLastName());
                    insert_statement.setString(4, input.getSex());
                    insert_statement.setString(5, input.getAddress());
                    insert_statement.setInt(6, input.getZipCode());
                    insert_statement.setString(7,input.getCity());
                    insert_statement.setString(8, input.getState());
                    insert_statement.setString(9, input.getCountry());
                    insert_statement.setString(10, input.getCustomer_Type());
                    insert_statement.setString(11, input.getPhone());
                    insert_statement.setString(12, input.getFax());
                    insert_statement.setString(13, input.getMail());
                    insert_statement.setTimestamp(14, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                flag = insert_statement.execute();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return flag;
        }
        public boolean insert_company(CustomerCompany input){
            boolean flag = false;
            try{
                Connect();
                insert_statement = connection.prepareStatement(INSERT_COMPANY);
                    insert_statement.setInt(1,0);
                    insert_statement.setString(2,input.getCompanyName());
                    insert_statement.setString(3, input.getAddress());
                    insert_statement.setInt(4, input.getZipCode());
                    insert_statement.setString(5, input.getCity());
                    insert_statement.setString(6, input.getState());
                    insert_statement.setString(7, input.getCountry());
                    insert_statement.setString(8, input.getCustomer_type());
                    insert_statement.setString(9, input.getPhone());
                    insert_statement.setString(10, input.getFax());
                    insert_statement.setString(11, input.getMail());
                    insert_statement.setTimestamp(12, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                flag = insert_statement.execute();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return flag;
        }
        public int[] count_customers(){
            int Customers = 0;
            int Companies = 0;
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(COUNT_CUST_QUERY);
                while (rs.next()){
                    Customers = rs.getInt(1);
                }
                rs = null;
                rs = statement.executeQuery(COUNT_COMP_QUERY);
                while (rs.next()){
                    Companies = rs.getInt(1);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            int[] result = {Customers,Companies,Customers+Companies};
            return result;
        }
        public ObservableList<Map> select_person(){
            ObservableList<Map> table_data = FXCollections.observableArrayList();
                try{
                    Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery(CUST_QUERY);
                    while (rs.next()){
                        Map<String,String> Row_Data = new HashMap();
                            Row_Data.put("customer_id", String.valueOf(rs.getInt("customer_id")));
                            Row_Data.put("firstname", rs.getString("firstname"));
                            Row_Data.put("lastname", rs.getString("lastname"));
                            Row_Data.put("sex", rs.getString("sex") );
                            Row_Data.put("address", rs.getString("address") );
                            Row_Data.put("zipcode", String.valueOf(rs.getInt("zipcode")));
                            Row_Data.put("city", rs.getString("city"));
                            Row_Data.put("state", rs.getString("state"));
                            Row_Data.put("country", rs.getString("country"));
                            Row_Data.put("customer_type", rs.getString("customer_type"));
                            Row_Data.put("phone", rs.getString("phone"));
                            Row_Data.put("fax", rs.getString("fax"));
                            Row_Data.put("mail", rs.getString("mail"));
                            Row_Data.put("import_date", String.valueOf(rs.getDate("import_date")));
                        table_data.add(Row_Data);
                    }
                    Disconnect();
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return table_data;
        }
        public ObservableList<Map> select_company(){
            ObservableList<Map> table_data = FXCollections.observableArrayList();
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(COMP_QUERY);
                while (rs.next()){
                    Map<String,String> Row_Data = new HashMap();
                        Row_Data.put("company_id", String.valueOf(rs.getInt("company_id")));
                        Row_Data.put("name", rs.getString("name"));
                        Row_Data.put("address", rs.getString("address"));
                        Row_Data.put("zipcode", String.valueOf(rs.getInt("zipcode")));                      
                        Row_Data.put("city", rs.getString("city"));
                        Row_Data.put("state", rs.getString("state"));
                        Row_Data.put("country", rs.getString("country"));
                        Row_Data.put("customer_type", rs.getString("customer_type"));
                        Row_Data.put("phone", rs.getString("phone"));
                        Row_Data.put("fax", rs.getString("fax"));
                        Row_Data.put("mail", rs.getString("mail"));
                        Row_Data.put("import_date", String.valueOf(rs.getDate("import_date")));
                    table_data.add(Row_Data);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return table_data;
        } 
    private void Disconnect() throws SQLException{
        if (connection != null)
            connection.close();
        
        if (insert_statement !=null)
            insert_statement.close();
        
        if (statement != null)
            statement.close();
        
        if (rs != null)
            rs.close();
        
    }
}
