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
                                        +"SET .... "
                                        +"WHERE contact_id = ?";
    
    public boolean update_contact(int id,Contact input){return true;}
    
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
