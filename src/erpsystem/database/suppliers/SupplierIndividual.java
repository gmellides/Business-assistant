/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.suppliers;

import erpsystem.entities.people.Supplier;
import erpsystem.util.datetime.DateTimeProvider;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SupplierIndividual extends SuppliersDatabase{
    
    private final String INSERT_SUPP_QUERY = "INSERT INTO supplier(spl_id,spl_isCompany,spl_name,spl_lastname," +
                                             "spl_sex,spl_address,spl_zipcode,spl_city,spl_state," +
                                             "spl_country,spl_supplierType,spl_phone,spl_fax,spl_mail," +
                                             "spl_bank,spl_IBAN,spl_date) VALUES(?,0,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SELECT_SUPP_QUERY = "SELECT * FROM supplier WHERE spl_isCompany = 0;";
    
    public void insert_supplier(Supplier input){
        try{
            Connect();
                insert_statement = connection.prepareStatement(INSERT_SUPP_QUERY);
                insert_statement.setInt(1, 0);  
                insert_statement.setString(2, input.getFirstName());
                insert_statement.setString(3, input.getLastName());
                insert_statement.setString(4, input.getSex());
                insert_statement.setString(5, input.getAddress());
                insert_statement.setInt(6, input.getZipcode());
                insert_statement.setString(7, input.getCity());
                insert_statement.setString(8, input.getState());
                insert_statement.setString(9,input.getCountry());
                insert_statement.setString(10, input.getSupplier_Type());
                insert_statement.setString(11, input.getPhone());
                insert_statement.setString(12, input.getFax());
                insert_statement.setString(13, input.getMail());
                insert_statement.setString(14, input.getBank());
                insert_statement.setString(15, input.getIBAN());
                insert_statement.setTimestamp(16, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
            insert_statement.execute();
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public ObservableList<Map> select_suppliers(){
        ObservableList<Map> ret = FXCollections.observableArrayList();
        try{
            Connect();
            statement = connection.createStatement();
            rs = statement.executeQuery(SELECT_SUPP_QUERY);
            while(rs.next()){
                Map<String,String> row = new HashMap();
                    row.put("spl_id", rs.getString("spl_id"));
                    row.put("spl_name", rs.getString("spl_name"));
                    row.put("spl_lastname", rs.getString("spl_lastname"));
                    row.put("spl_sex", rs.getString("spl_sex"));
                    row.put("spl_address", rs.getString("spl_address"));
                    row.put("spl_zipcode", String.valueOf(rs.getInt("spl_zipcode")));
                    row.put("spl_country", rs.getString("spl_country"));
                    row.put("spl_state", rs.getString("spl_state"));
                    row.put("spl_supplierType", rs.getString("spl_supplierType"));
                    row.put("spl_city", rs.getString("spl_city"));
                    row.put("spl_phone", rs.getString("spl_phone"));
                    row.put("spl_mail", rs.getString("spl_mail"));
                    row.put("spl_fax", rs.getString("spl_fax"));
                    row.put("spl_bank", rs.getString("spl_bank"));
                    row.put("spl_iban", rs.getString("spl_iban"));
                    row.put("spl_date",String.valueOf(rs.getDate("spl_date")));
                ret.add(row);
            }
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }  
        return ret;
      }
}
