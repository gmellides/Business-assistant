/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.sales;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SalesDatabase {
    
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
        /**
         * Counts the Sales (used on finance window)
         * @return 
         */
        public int count_sales(){
            int sales = 0;
             try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT COUNT(*) FROM sales");
                while(rs.next()){
                    sales = rs.getInt(1);
                }
                Disconnect();
             }catch(SQLException e){
                 e.printStackTrace();
             }
            return sales;
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
