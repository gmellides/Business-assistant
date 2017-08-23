package erpsystem.util.importcsv;

import erpsystem.database.contacts.ContactsDatabase;
import erpsystem.entities.people.Contact;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportContacts {
    
    private BufferedReader reader;
    private ContactsDatabase contacts_db;
    
    public boolean import_csv(String file_path){
        contacts_db = new ContactsDatabase();
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine();
                while((csv_line = reader.readLine())!= null){
                    String[] values = csv_line.split(",");
                    Contact obj = new Contact();
                        obj.setFirstName(values[0]);
                        obj.setLastName(values[1]);
                        obj.setSex(values[2]);
                        obj.setAddress(values[3]);
                        obj.setZipCode(Integer.parseInt(values[4]));
                        obj.setCountry(values[5]);
                        obj.setState(values[6]);
                        obj.setCity(values[7]);
                        obj.setMail(values[8]);
                        obj.setPhone_1(values[9]);
                        obj.setPhone_1_type(values[10]);
                        obj.setPhone_2(values[11]);
                        obj.setPhone_2_type(values[12]);
                        obj.setComments(values[13]);
                        obj.setWebsite(values[14]);
                    contacts_db.insert_contact(obj);
                }      
            reader.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
}
