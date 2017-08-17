/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import erpsystem.entities.people.Customer;
import erpsystem.util.datetime.DateTimeProvider;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerIndividual extends CustomersDatabase{
    
    private final String INSERT_CUSTOMER = "INSERT INTO Customer_Person"
    + "(cust_customer_id,firstname,lastname,sex,address,zipcode,city,state,country,customer_type,phone,fax,mail,import_date) "
    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String CUST_QUERY = "SELECT * FROM Customer_Person";
    
    public boolean insert_customer(Customer input){
        boolean flag = false;
        try{
            Connect();
            prepared_statement = connection.prepareStatement(INSERT_CUSTOMER);
                prepared_statement.setInt(1, 0);
                prepared_statement.setString(2, input.getFirstName());
                prepared_statement.setString(3, input.getLastName());
                prepared_statement.setString(4, input.getSex());
                prepared_statement.setString(5, input.getAddress());
                prepared_statement.setInt(6, input.getZipCode());
                prepared_statement.setString(7,input.getCity());
                prepared_statement.setString(8, input.getState());
                prepared_statement.setString(9, input.getCountry());
                prepared_statement.setString(10, input.getCustomer_Type());
                prepared_statement.setString(11, input.getPhone());
                prepared_statement.setString(12, input.getFax());
                prepared_statement.setString(13, input.getMail());
                prepared_statement.setTimestamp(14, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
            flag = prepared_statement.execute();
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
    
    public ObservableList<Map> select_customers(){
        ObservableList<Map> table_data = FXCollections.observableArrayList();
        try{
            Connect();
            statement = connection.createStatement();
             rs = statement.executeQuery(CUST_QUERY);
             while (rs.next()){
                Map<String,String> Row_Data = new HashMap();
                    Row_Data.put("customer_id", String.valueOf(rs.getInt("cust_customer_id")));
                    Row_Data.put("firstname", rs.getString("firstname"));
                    Row_Data.put("lastname", rs.getString("lastname"));
                    Row_Data.put("sex", rs.getString("sex") );
                    Row_Data.put("address", rs.getString("address") );
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
