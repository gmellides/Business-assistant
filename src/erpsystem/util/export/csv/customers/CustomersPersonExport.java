/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.csv.customers;

import erpsystem.util.system.FileManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

public class CustomersPersonExport {
    
    private FileManager workspace;
    private File csv_file;
    private BufferedWriter buffer;
    private FileWriter writer;
    
    public boolean export_file(ResourceBundle default_strings,
                              ObservableList<Map> data){
        workspace = new FileManager();
        csv_file = new File(workspace.getDocuments_root()+"/"+default_strings.getString("backup_filename")+"*.csv");
            if (csv_file.exists()){
                csv_file.delete();       
                create_file(csv_file,default_strings,data);
            }else{
                create_file(csv_file,default_strings,data);
            }
            // check if file exists
            // if yes delete and create new 
            // if no create new 
        return true;
    }
    
    private void create_file(File csv_file,
                             ResourceBundle default_strings,
                             ObservableList<Map> input){
        String[] csv_columns = {"firstname","lastname","sex","address","zipcode",
        "city","state","country","customer_type","phone","fax","mail",
        "import_date"};
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
        return res.getString("filename_customers_person_csv")+".csv";
    }
}
