/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.csv.contacts;

import erpsystem.util.system.FileManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

public class ContactsExport {
    private FileManager workspace;
    private File csv_file;
    private BufferedWriter buffer;
    private FileWriter writer;
    
    /**
     * Checks if file exists if yes deletes the old file and creates a new
     * one with the values.
     * @param default_strings
     * @param input
     * @return 
     */
    public boolean export_file(ResourceBundle default_strings,ObservableList<Map> input){
        workspace = new FileManager();
        csv_file = new File(workspace.getDocuments_root()+"/"+default_strings.getString("filename_contacts_csv")+"*.csv");
            if (csv_file.exists()){
                csv_file.delete();       
                create_file(csv_file,default_strings,input);
            }else{
                create_file(csv_file,default_strings,input);
            }
        return true;
    }
    
    private void create_file(File csv_file,
                             ResourceBundle default_strings,
                             ObservableList<Map> input){
        String[] csv_columns = {"firstname","lastname","sex","address",
                                "zipcode","country","greek_state","city","mail",
                                "phone1","phone1_type","phone2","phone2_type",
                                "comments","website","import_date"};
        try{        
            writer = new FileWriter(workspace.getDocuments_root()+"/"+FileName(default_strings));
            buffer = new BufferedWriter(writer);
            // Make Header
            for (String item : csv_columns){
                buffer.write(item);
                if(!item.equals("import_date"))
                    buffer.write(",");
            }
            for (Map item : input){
                buffer.write("\n");
                for (String index : csv_columns){  
                    if (index.equals("import_date")){
                        buffer.append(String.valueOf(item.get(index)));
                    }else{
                    buffer.append(String.valueOf(item.get(index)));
                        buffer.write(",");
                    }
                }  
            }
            buffer.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(writer != null)
                    writer.close();
                if (buffer != null)
                    buffer.close();
            }catch(IOException e){
                    e.printStackTrace();
            }
        }
    }
    
    private String FileName(ResourceBundle res){
        return res.getString("filename_contacts_csv")+".csv";
    }
}
