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

public class CST_Companies extends CST_Database {
    
    private final String COMP_QUERY = "SELECT * FROM customer WHERE cst_isCompany = -1;";
    private final String INSERT_COMPANY = "INSERT INTO customer(cst_id,cst_isCompany,cst_name,cst_lastname,cst_sex,"+
                                          "cst_address,cst_zipcode,cst_city,cst_state,cst_country,"+
                                          "cst_customerType,cst_phone,cst_fax,cst_mail,cst_date) "+
                                          "VALUES (?,-1,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    
    public boolean insert_company(CustomerCompany input){
        boolean flag = false;
        try{
            Connect();
            prepared_statement = connection.prepareStatement(INSERT_COMPANY);
                prepared_statement.setInt(1,0);
                prepared_statement.setString(2,input.getCompanyName());
                prepared_statement.setString(3,null);
                prepared_statement.setString(4,null);
                prepared_statement.setString(5, input.getAddress());
                prepared_statement.setInt(6, input.getZipCode());
                prepared_statement.setString(7, input.getCity());
                prepared_statement.setString(8, input.getState());
                prepared_statement.setString(9, input.getCountry());
                prepared_statement.setString(10, input.getCustomer_type());
                prepared_statement.setString(11, input.getPhone());
                prepared_statement.setString(12, input.getFax());
                prepared_statement.setString(13, input.getMail());
                prepared_statement.setTimestamp(14, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
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
                    Row_Data.put("cst_id", String.valueOf(rs.getInt("cst_id")));
                    Row_Data.put("cst_name", rs.getString("cst_name"));
                    Row_Data.put("cst_address", rs.getString("cst_address"));
                    Row_Data.put("cst_zipcode", String.valueOf(rs.getInt("cst_zipcode")));                      
                    Row_Data.put("cst_city", rs.getString("cst_city"));
                    Row_Data.put("cst_state", rs.getString("cst_state"));
                    Row_Data.put("cst_country", rs.getString("cst_country"));
                    Row_Data.put("cst_customerType", rs.getString("cst_customerType"));
                    Row_Data.put("cst_phone", rs.getString("cst_phone"));
                    Row_Data.put("cst_fax", rs.getString("cst_fax"));
                    Row_Data.put("cst_mail", rs.getString("cst_mail"));
                    Row_Data.put("cst_date", String.valueOf(rs.getTimestamp("cst_date")));
                table_data.add(Row_Data);
            }
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return table_data;
    } 
}
