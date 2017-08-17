/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.suppliers;

import erpsystem.entities.corpotations.SupplierCompany;
import erpsystem.util.datetime.DateTimeProvider;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SupplierCompanies extends SuppliersDatabase{
    
    private final String SELECT_COMP_QUERY = "SELECT * FROM Supplier_Company";
    private final String INSERT_COMP_QUERY = "INSERT INTO Supplier_Company(supl_company_id,name,address,zipcode,city,state,country,supplier_type,phone,fax,mail,bank,iban,import_date)" +
    "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public void insert_supplier(SupplierCompany input){
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
    public ObservableList<Map> select_suppliers(){
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
}
