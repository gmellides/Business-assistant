/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import erpsystem.entities.corpotations.CustomerCompany;
import erpsystem.entities.people.Customer;
import java.sql.SQLException;

public class CustomerView extends CustomersDatabase{

    public boolean update_customer_individual(int id,Customer input){
        String UPDATE_QUERY = "UPDATE Customer_Person "
                             +"SET firstname = ?,"
                             + "lastname = ? ,sex = ?, address = ?,"
                             + "zipcode = ?,city = ?,state = ?,"
                             + "country = ? ,customer_type = ?  ,phone = ?,"
                             + "fax = ?,mail = ?"
                             +"WHERE cust_customer_id = ?";
        try{
            Connect();
            prepared_statement = connection.prepareStatement(UPDATE_QUERY);
                prepared_statement.setString(1, input.getFirstName());
                prepared_statement.setString(2, input.getLastName());
                prepared_statement.setString(3, input.getSex());
                prepared_statement.setString(4, input.getAddress());
                prepared_statement.setInt(5, input.getZipCode());
                prepared_statement.setString(6, input.getCity());
                prepared_statement.setString(7, input.getState());
                prepared_statement.setString(8, input.getCountry());
                prepared_statement.setString(9, input.getCustomer_Type());
                prepared_statement.setString(10, input.getPhone());
                prepared_statement.setString(11, input.getFax());
                prepared_statement.setString(12, input.getMail());
                prepared_statement.setInt(13, id);
            prepared_statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update_customer_company(int id,CustomerCompany input){
        String UPDATE_QUERY = "UPDATE Customer_Companies "
                             +"SET name = ?,"
                             + "address = ?,"
                             + "zipcode = ?,city = ?,state = ?,"
                             + "country = ? ,customer_type = ? ,phone = ?,fax = ?,"
                             + "mail = ?"
                             +"WHERE cust_company_id = ?";
        try{
            Connect();
            prepared_statement = connection.prepareStatement(UPDATE_QUERY);
                prepared_statement.setString(1, input.getCompanyName());
                prepared_statement.setString(2, input.getAddress());
                prepared_statement.setInt(3, input.getZipCode());
                prepared_statement.setString(4, input.getCity());
                prepared_statement.setString(5, input.getState());
                prepared_statement.setString(6, input.getCountry());
                prepared_statement.setString(7, input.getCustomer_type());
                prepared_statement.setString(8, input.getPhone());
                prepared_statement.setString(9, input.getFax());
                prepared_statement.setString(10, input.getMail());
                prepared_statement.setInt(11, id);
            prepared_statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete_customer(boolean isCompany,int id){
        try{
            Connect();
            if(!isCompany){
                prepared_statement = connection.prepareStatement("DELETE FROM Customer_Person WHERE cust_customer_id = ?");
            }else{
                prepared_statement = connection.prepareStatement("DELETE FROM Customer_Companies WHERE cust_company_id = ?");
            }
            prepared_statement.setInt(1, id);
            prepared_statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;  
    }  
}
