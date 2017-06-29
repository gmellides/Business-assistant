/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.suppliers;

import erpsystem.entities.corpotations.SupplierCompany;
import erpsystem.entities.people.Supplier;
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

public class SuppliersDatabase {
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/Suppliers_t.accdb").getAbsolutePath();
    private final String Username = null;
    private final String Password = null;
    private final String COUNT_SUPP_QUERY = "SELECT COUNT(*) FROM Suppliers_Person";
    private final String COUNT_COMP_QUERY = "SELECT COUNT(*) FROM Supplier_Company";
    private final String SELECT_SUPP_QUERY = "SELECT * FROM Suppliers_Person";
    private final String SELECT_COMP_QUERY = "SELECT * FROM Supplier_Company";
    private final String INSERT_SUPP_QUERY = "INSERT INTO Suppliers_Person(supl_person_id,firstname,lastname,sex,address,zipcode,country,state,supplier_type,city,phone,mail,fax,bank,iban,import_date)" +
    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String INSERT_COMP_QUERY = "INSERT INTO Supplier_Company(supl_company_id,name,address,zipcode,city,state,country,supplier_type,phone,fax,mail,bank,iban,import_date)" +
    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
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
        public void insert_supplier_person(Supplier input){
            try{
                Connect();
                insert_statement = connection.prepareStatement(INSERT_SUPP_QUERY);
                    insert_statement.setInt(1, 0);
                    insert_statement.setString(2, input.getFirstName());
                    insert_statement.setString(3, input.getLastName());
                    insert_statement.setString(4, input.getSex());
                    insert_statement.setString(5, input.getAddress());
                    insert_statement.setInt(6, input.getZipcode());
                    insert_statement.setString(7, input.getCountry());
                    insert_statement.setString(8, input.getState());
                    insert_statement.setString(9, input.getSupplier_Type());
                    insert_statement.setString(10, input.getCity());
                    insert_statement.setString(11, input.getPhone());
                    insert_statement.setString(12, input.getMail());
                    insert_statement.setString(13, input.getFax());
                    insert_statement.setString(14, input.getBank());
                    insert_statement.setString(15, input.getIBAN());
                    insert_statement.setTimestamp(16, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                insert_statement.execute();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        public void insert_supplier_company(SupplierCompany input){
            try{
                Connect();
                insert_statement = connection.prepareStatement(INSERT_COMP_QUERY);
                    insert_statement.setInt(1, 0);
                    insert_statement.setString(2, input.getCompanyName() );
                    insert_statement.setString(3, input.getAddress());
                    insert_statement.setInt(4, input.getZipCode());
                    insert_statement.setString(5, input.getCity());
                    insert_statement.setString(6, input.getState());
                    insert_statement.setString(7, input.getCountry());
                    insert_statement.setString(8, input.getSupplierType());
                    insert_statement.setString(9, input.getPhone());           
                    insert_statement.setString(10, input.getFax());
                    insert_statement.setString(11, input.getMail());
                    insert_statement.setString(12, input.getBank());
                    insert_statement.setString(13, input.getIBAN());
                    insert_statement.setTimestamp(14, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                insert_statement.execute();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        public ObservableList<Map> get_Suppliers_Persons(){
            ObservableList<Map> ret = FXCollections.observableArrayList();
                try{
                   Connect();
                   statement = connection.createStatement();
                    rs = statement.executeQuery(SELECT_SUPP_QUERY);
                     while(rs.next()){
                         Map<String,String> row = new HashMap();
                            row.put("supl_person_id", rs.getString("supl_person_id"));
                            row.put("firstname", rs.getString("firstname"));
                            row.put("lastname", rs.getString("lastname"));
                            row.put("sex", rs.getString("sex"));
                            row.put("address", rs.getString("address"));
                            row.put("zipcode", String.valueOf(rs.getInt("zipcode")));
                            row.put("country", rs.getString("country"));
                            row.put("state", rs.getString("state"));
                            row.put("supplier_type", rs.getString("supplier_type"));
                            row.put("city", rs.getString("city"));
                            row.put("phone", rs.getString("phone"));    
                            row.put("mail", rs.getString("mail"));
                            row.put("fax", rs.getString("fax"));
                            row.put("bank", rs.getString("bank"));
                            row.put("iban", rs.getString("iban"));
                            row.put("import_date",String.valueOf(rs.getDate("import_date")));
                        ret.add(row);
                     }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return ret;
        }
        public ObservableList<Map> get_Suppliers_Companies(){
            ObservableList<Map> ret = FXCollections.observableArrayList();
                try{
                   Connect();
                   statement = connection.createStatement();
                    rs = statement.executeQuery(SELECT_COMP_QUERY);
                     while(rs.next()){
                         Map<String,String> row = new HashMap();
                            row.put("supl_company_id", rs.getString("supl_company_id"));
                            row.put("name", rs.getString("name"));
                            row.put("address", rs.getString("address"));
                            row.put("zipcode", String.valueOf(rs.getInt("zipcode")));
                            row.put("city", rs.getString("city"));
                            row.put("state", rs.getString("state"));
                            row.put("country", rs.getString("country"));
                            row.put("supplier_type", rs.getString("supplier_type"));
                            row.put("phone", rs.getString("phone"));    
                            row.put("mail", rs.getString("mail"));
                            row.put("fax", rs.getString("fax"));
                            row.put("bank", rs.getString("bank"));
                            row.put("iban", rs.getString("iban"));
                            row.put("import_date",String.valueOf(rs.getDate("import_date")));
                        ret.add(row);
                     }
                }catch(SQLException e){
                    e.printStackTrace();
                }
            return ret;
        }
        public int[] count_suppliers(){
            int companies = 0;
            int suppliers = 0;
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery(COUNT_SUPP_QUERY);
                while(rs.next()){
                    suppliers = rs.getInt(1);
                }
                rs = statement.executeQuery(COUNT_COMP_QUERY);
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
    private void Disconnect() throws SQLException{
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
