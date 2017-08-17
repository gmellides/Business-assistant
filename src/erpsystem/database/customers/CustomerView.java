/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.customers;

import erpsystem.entities.people.Customer;
import java.sql.SQLException;

public class CustomerView extends CustomersDatabase{
   
    private void update_customer(int id,Customer input){
        
    }
    public boolean delete_customer(int id){
        try{
            Connect();
            if(true){
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
