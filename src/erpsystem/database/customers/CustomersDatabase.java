/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomersDatabase {
    
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/app_data.accdb").getAbsolutePath();
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
        public int[] count_customers(){
            int Customers = 0;
            int Companies = 0;
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT COUNT(*) FROM customer WHERE cst_isCompany = 0;");
                while (rs.next()){
                    Customers = rs.getInt(1);
                }
                rs = null;
                rs = statement.executeQuery("SELECT COUNT(*) FROM customer WHERE cst_isCompany = -1");
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
        
        public ObservableList<Map> select_customersBackUp(){
        ObservableList<Map> table_data = FXCollections.observableArrayList();
        try{
            Connect();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM customer;");
            while (rs.next()){
                Map<String,String> Row_Data = new HashMap();
                    Row_Data.put("cst_id", String.valueOf(rs.getInt("cst_id")));
                    Row_Data.put("cst_isCompany", rs.getString("cst_isCompany"));
                    Row_Data.put("cst_name", rs.getString("cst_name"));
                    Row_Data.put("cst_lastname", rs.getString("cst_lastname"));
                    Row_Data.put("cst_sex", rs.getString("cst_sex"));
                    Row_Data.put("cst_address", rs.getString("cst_address"));
                    Row_Data.put("cst_zipcode", String.valueOf(rs.getInt("cst_zipcode")));                      
                    Row_Data.put("cst_city", rs.getString("cst_city"));
                    Row_Data.put("cst_state", rs.getString("cst_state"));
                    Row_Data.put("cst_country", rs.getString("cst_country"));
                    Row_Data.put("cst_customerType", rs.getString("cst_customerType"));
                    Row_Data.put("cst_phone", rs.getString("cst_phone"));
                    Row_Data.put("cst_fax", rs.getString("cst_fax"));
                    Row_Data.put("cst_mail", rs.getString("cst_mail"));
                    Row_Data.put("cst_date", String.valueOf(rs.getDate("cst_date")));
                table_data.add(Row_Data);
            }
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return table_data;
    } 
    protected void Disconnect() throws SQLException{
        if (connection != null)
            connection.close();
        
        if (prepared_statement !=null)
            prepared_statement.close();
        
        if (statement != null)
            statement.close();
        
        if (rs != null)
            rs.close();
        
    }
}
