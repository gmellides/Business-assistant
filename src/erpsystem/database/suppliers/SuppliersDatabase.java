/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.suppliers;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SuppliersDatabase {
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/data.accdb").getAbsolutePath();
    private final String Username = null;
    private final String Password = null;
 
    protected Connection connection;
    protected Statement statement;
    protected ResultSet rs;
    protected PreparedStatement insert_statement; 

    protected void Connect(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection(database_driver+database_path);
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }        
        public int[] count_suppliers(){
            int companies = 0;
            int suppliers = 0;
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT COUNT(*) FROM Suppliers_Person");
                while(rs.next()){
                    suppliers = rs.getInt(1);
                }
                rs = statement.executeQuery("SELECT COUNT(*) FROM Supplier_Company");
                while(rs.next()){
                    companies = rs.getInt(1);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }       
            int[] res = {suppliers,companies,suppliers+companies}; 
            return res;
        }
    protected void Disconnect() throws SQLException{
        if (connection != null)
            connection.close();
        
        if (statement!=null)
            statement.close();
        
        if (insert_statement !=null)
            insert_statement.close();
        
        if (rs !=null)
            rs.close();
    }
}
