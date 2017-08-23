/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.contacts;

import erpsystem.entities.people.Contact;
import java.sql.SQLException;

public class ContactsView extends ContactsDatabase {
    
    private final String UPDATE_QUERY = "UPDATE Contacts "
                                        +"SET firstname = ?,"
                                        + "lastname = ? ,sex = ?, address = ?,"
                                        + "zipcode = ?,country = ?,greek_state = ?,"
                                        + "city = ? ,mail = ? ,phone1 = ?,phone1_type = ?,"
                                        + "phone2 = ?,phone2_type = ?,comments = ?,"
                                        + "website = ?"
                                        +"WHERE contact_id = ?";
    
    public boolean update_contact(int id,Contact input){
        try{
            Connect();
            prepared_statement = connection.prepareStatement(UPDATE_QUERY);
                prepared_statement.setString(1, input.getFirstName());
                prepared_statement.setString(2, input.getLastName());
                prepared_statement.setString(3, input.getSex());
                prepared_statement.setString(4, input.getAddress());
                prepared_statement.setInt(5, input.getZipCode());
                prepared_statement.setString(6, input.getCountry());
                prepared_statement.setString(7, input.getState());
                prepared_statement.setString(8, input.getCity());
                prepared_statement.setString(9, input.getMail());
                prepared_statement.setString(10, input.getPhone_1());
                prepared_statement.setString(11, input.getPhone_1_type());
                prepared_statement.setString(12, input.getPhone_2());
                prepared_statement.setString(13, input.getPhone_2_type());
                prepared_statement.setString(14, input.getComments());
                prepared_statement.setString(15, input.getWebsite());
                prepared_statement.setInt(16, id);
            prepared_statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    
    public boolean delete_contact(int contact_id){
       try{
            Connect();
            prepared_statement = connection.prepareStatement("DELETE FROM Contacts WHERE contact_id = ?");
            prepared_statement.setInt(1, contact_id);
            prepared_statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}
