/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.csv.contacts;

import com.healthmarketscience.jackcess.Row;
import erpsystem.util.system.FileManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ContactsExport {
    private FileManager workspace;
    private File csv_file;
    private BufferedWriter buffer;
    private FileWriter writer;
    
    public boolean export_file(ResourceBundle default_strings,ArrayList<Row> input){
        workspace = new FileManager();
        csv_file = new File(workspace.getDocuments_root()+"/"+default_strings.getString("backup_filename")+"*.csv");
        
        if (csv_file.exists()){
            csv_file.delete();       
            create_file(csv_file,default_strings,input);
        }else{
            create_file(csv_file,default_strings,input);
        }
        // check if file exists
        // if yes delete and create new 
        // if no create new 
        return true;
    }
    
    private void create_file(File csv_file,
                             ResourceBundle default_strings,
                             ArrayList<Row> input){
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
            for (Row item : input){
                buffer.write("\n");
                for (String index : csv_columns){
                    buffer.write(",");
                    if (index.equals("zipcode")){
                        buffer.append(String.valueOf(item.getInt(index)));
                    }else{
                        buffer.append(item.getString(index));
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
        return res.getString("backup_filename")+".csv";
    }
}
