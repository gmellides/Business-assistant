/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.csv.suppliers;

import erpsystem.util.system.FileManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

public class SuppliersCSV {

    private File csv_file;
    private BufferedWriter buffer;
    private FileWriter writer;
    
    public boolean export_file(boolean isCompany,
                               ResourceBundle default_strings,
                               ObservableList<Map> data){
        if(isCompany){
            csv_file = new File(new FileManager().getDocuments_BackUp_folder()+"/"+default_strings.getString("")+".csv");
            if (csv_file.exists()){
                csv_file.delete();
                create_companies(data);
            }else{
                create_companies(data);
            }
        }else{
            csv_file = new File(new FileManager().getApp_data_root()+"/"+default_strings.getString("")+".csv");
            if (csv_file.exists()){
                csv_file.delete();
                create_individuals(data);
            }else{
                create_individuals(data);
            }
            
        }
        return true;
    }
    
    private void create_individuals(ObservableList<Map> raw_data){
        String[] csv_columns = {"firstname","lastname","sex","address","zipcode",
                                "country","state","supplier_type","city","phone",
                                "mail","fax","bank","IBAN","import_date"};
        try{
            writer = new FileWriter(csv_file);
            buffer = new BufferedWriter(writer);
            // CSV Header
            for (String item : csv_columns){
                buffer.write(item);
                if(!item.equals("import_date"))
                    buffer.write(",");
            }
            for (Map item : raw_data){
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
            buffer.close();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void create_companies(ObservableList<Map> raw_data){
        String[] csv_columns = {"name","address","zipcode","city","state","country",
                                "supplier_type","phone","mail","fax","bank","IBAN","import_date"};
        try{
            writer = new FileWriter(csv_file);
            buffer = new BufferedWriter(writer);
            // CSV Header
            for (String item : csv_columns){
                buffer.write(item);
                if(!item.equals("import_date"))
                    buffer.write(",");
            }
            for (Map item : raw_data){
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
            buffer.close();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
