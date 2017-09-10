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
    
    private final String INSERT_SUPP_QUERY = "INSERT INTO Suppliers_Person(supl_person_id,firstname,lastname,sex,address,zipcode,country,state,supplier_type,city,phone,mail,fax,bank,iban,import_date)" +
    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String SELECT_SUPP_QUERY = "SELECT * FROM Suppliers_Person";
    
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
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }  
        return ret;
      }
}
