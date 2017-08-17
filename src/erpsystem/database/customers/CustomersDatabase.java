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

public class CustomersDatabase {
    
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/data.accdb").getAbsolutePath();
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
                rs = statement.executeQuery("SELECT COUNT(*) FROM Customer_Person");
                while (rs.next()){
                    Customers = rs.getInt(1);
                }
                rs = null;
                rs = statement.executeQuery("SELECT COUNT(*) FROM Customer_Companies");
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
