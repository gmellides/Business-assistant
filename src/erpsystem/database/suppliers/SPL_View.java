/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.suppliers;

import erpsystem.entities.corpotations.SupplierCompany;
import erpsystem.entities.people.Supplier;
import java.sql.SQLException;


public class SPL_View extends SPL_Database{
     public boolean update_supplier_ind(int id,Supplier input){
        String Query = "UPDATE supplier "
                       +"SET spl_name = ?,"
                       + "spl_lastname = ? ,spl_sex = ?, spl_address = ?,"
                       + "spl_zipcode = ?,spl_city = ?,spl_state = ?,"
                       + "spl_country = ? ,spl_supplierType = ? ,spl_phone = ?,"
                       + "spl_fax = ?,spl_mail = ?,spl_bank = ?,spl_IBAN = ?"
                       +"WHERE spl_id = ?";
        try{
            Connect();
            insert_statement = connection.prepareStatement(Query);
                insert_statement.setString(1, input.getFirstName());
                insert_statement.setString(2, input.getLastName());
                insert_statement.setString(3, input.getSex());
                insert_statement.setString(4, input.getAddress());
                insert_statement.setInt(5, input.getZipcode());
                insert_statement.setString(6, input.getCity());
                insert_statement.setString(7, input.getState());
                insert_statement.setString(8, input.getCountry());
                insert_statement.setString(9, input.getSupplier_Type());
                insert_statement.setString(10, input.getPhone());
                insert_statement.setString(11, input.getFax());
                insert_statement.setString(12, input.getMail());
                insert_statement.setString(13, input.getBank());
                insert_statement.setString(14, input.getIBAN());
                insert_statement.setInt(15, id);
            insert_statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
     public boolean update_supplier_cmp(int id,SupplierCompany input){
        String Query = "UPDATE supplier "
                       +"SET spl_name = ?,"
                       + "spl_address = ?,"
                       + "spl_zipcode = ?,spl_city = ?,spl_state = ?,"
                       + "spl_country = ? ,spl_supplierType = ? ,spl_phone = ?,"
                       + "spl_fax = ?,spl_mail = ?,spl_bank = ?,spl_IBAN = ?"
                       +"WHERE spl_id = ?";
        try{
            Connect();
            insert_statement = connection.prepareStatement(Query);
                insert_statement.setString(1, input.getCompanyName());
                insert_statement.setString(2, input.getAddress());
                insert_statement.setInt(3, input.getZipCode());
                insert_statement.setString(4, input.getCity());
                insert_statement.setString(5, input.getState());
                insert_statement.setString(6, input.getCountry());
                insert_statement.setString(7, input.getSupplierType());
                insert_statement.setString(8, input.getPhone());
                insert_statement.setString(9, input.getFax());
                insert_statement.setString(10, input.getMail());
                insert_statement.setString(11, input.getBank());
                insert_statement.setString(12, input.getIBAN());
                insert_statement.setInt(13, id);
            insert_statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
      public boolean delete_supplier(int id){
        try{
            Connect();
            insert_statement = connection.prepareStatement("DELETE FROM supplier WHERE spl_id = ?");
            insert_statement.setInt(1, id);
            insert_statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;  
    }  
}
