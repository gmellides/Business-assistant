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
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SPL_Database {
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/app_data.accdb").getAbsolutePath();
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
                rs = statement.executeQuery("SELECT COUNT(*) FROM supplier WHERE spl_isCompany = 0;");
                while(rs.next()){
                    suppliers = rs.getInt(1);
                }
                rs = statement.executeQuery("SELECT COUNT(*) FROM supplier WHERE spl_isCompany = -1;");
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
        public ObservableList<Map> select_suppliersBackUp(){
            ObservableList<Map> ret = FXCollections.observableArrayList();
                try{
                   Connect();
                   statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT * FROM supplier;");
                     while(rs.next()){
                         Map<String,String> row = new HashMap();
                            row.put("spl_isCompany",rs.getString("spl_isCompany"));
                            row.put("spl_name", rs.getString("spl_name"));
                            row.put("spl_lastname", rs.getString("spl_lastname"));
                            row.put("spl_sex",rs.getString("spl_sex"));
                            row.put("spl_address", rs.getString("spl_address"));
                            row.put("spl_zipcode", String.valueOf(rs.getInt("spl_zipcode")));
                            row.put("spl_city", rs.getString("spl_city"));
                            row.put("spl_state", rs.getString("spl_state"));
                            row.put("spl_country", rs.getString("spl_country"));
                            row.put("spl_supplierType", rs.getString("spl_supplierType"));
                            row.put("spl_phone", rs.getString("spl_phone"));    
                            row.put("spl_mail", rs.getString("spl_mail"));
                            row.put("spl_fax", rs.getString("spl_fax"));
                            row.put("spl_bank", rs.getString("spl_bank"));
                            row.put("spl_iban", rs.getString("spl_iban"));
                        ret.add(row);
                     }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return ret;
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
