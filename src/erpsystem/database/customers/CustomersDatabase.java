/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import erpsystem.entities.business.Company;
import erpsystem.entities.people.Customer;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CustomersDatabase {
    
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/-----.accdb").getAbsolutePath();
    private final String Username = null;
    private final String Password = null;
    
    private final String COUNT_CUST_QUERY = "SELECT COUNT(*) FROM Customers";
    private final String COUNT_COMP_QUERY = "SELECT COUNT(*) FROM Companies";
    
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
        return true;
    }
    
    public boolean insert_company(Company input){
        return true;
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
            rs = statement.executeQuery(COUNT_COMP_QUERY);
            while (rs.next()){
                Companies = rs.getInt(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        int[] result = {Customers,Customers,Customers+Companies};
        return result;
    }
    
    
    private void Disconnect(){
        
    }
}
