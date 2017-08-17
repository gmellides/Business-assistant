/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import erpsystem.entities.corpotations.CustomerCompany;
import erpsystem.util.datetime.DateTimeProvider;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerCompanies extends CustomersDatabase {
    
    private final String COMP_QUERY = "SELECT * FROM Customer_Companies";
    
    private final String INSERT_COMPANY = "INSERT INTO Customer_Companies"
    +"(cust_company_id,name,address,zipcode,city,state,country,customer_type,phone,fax,mail,import_date) "
    +"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public boolean insert_company(CustomerCompany input){
        boolean flag = false;
        try{
            Connect();
            prepared_statement = connection.prepareStatement(INSERT_COMPANY);
                prepared_statement.setInt(1,0);
                prepared_statement.setString(2,input.getCompanyName());
                prepared_statement.setString(3, input.getAddress());
                prepared_statement.setInt(4, input.getZipCode());
                prepared_statement.setString(5, input.getCity());
                prepared_statement.setString(6, input.getState());
                prepared_statement.setString(7, input.getCountry());
                prepared_statement.setString(8, input.getCustomer_type());
                prepared_statement.setString(9, input.getPhone());
                prepared_statement.setString(10, input.getFax());
                prepared_statement.setString(11, input.getMail());
                prepared_statement.setTimestamp(12, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
            flag = prepared_statement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
    public ObservableList<Map> select_company(){
        ObservableList<Map> table_data = FXCollections.observableArrayList();
        try{
            Connect();
            statement = connection.createStatement();
            rs = statement.executeQuery(COMP_QUERY);
            while (rs.next()){
                Map<String,String> Row_Data = new HashMap();
                    Row_Data.put("company_id", String.valueOf(rs.getInt("cust_company_id")));
                    Row_Data.put("name", rs.getString("name"));
                    Row_Data.put("address", rs.getString("address"));
                    Row_Data.put("zipcode", String.valueOf(rs.getInt("zipcode")));                      
                    Row_Data.put("city", rs.getString("city"));
                    Row_Data.put("state", rs.getString("state"));
                    Row_Data.put("country", rs.getString("country"));
                    Row_Data.put("customer_type", rs.getString("customer_type"));
                    Row_Data.put("phone", rs.getString("phone"));
                    Row_Data.put("fax", rs.getString("fax"));
                    Row_Data.put("mail", rs.getString("mail"));
                    Row_Data.put("import_date", String.valueOf(rs.getDate("import_date")));
                table_data.add(Row_Data);
            }
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return table_data;
    } 
}
