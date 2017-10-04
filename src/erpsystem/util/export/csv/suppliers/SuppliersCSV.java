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
    
    public boolean export_file(ResourceBundle default_strings,
                               ObservableList<Map> data){
     
            csv_file = new File(new FileManager().getDocuments_BackUp_folder()+"/"+default_strings.getString("filename_suppliers_csv")+".csv");
            if (csv_file.exists()){
                csv_file.delete();
                export_file(data);
            }else{
                export_file(data);
            }
        return true;
    }
    
    private void export_file(ObservableList<Map> raw_data){
        String[] csv_columns = {"spl_isCompany","spl_name","spl_lastname",
            "spl_sex","spl_address","spl_zipcode","spl_city","spl_state",
            "spl_country","spl_supplierType","spl_phone","spl_fax",
            "spl_mail","spl_bank","spl_IBAN"};
        try{
            writer = new FileWriter(csv_file);
            buffer = new BufferedWriter(writer);
            // CSV Header
            for (String item : csv_columns){
                buffer.write(item);
                if(!item.equals("spl_IBAN"))
                    buffer.write(",");
            }
            for (Map item : raw_data){
                buffer.write("\n");
                for (String index : csv_columns){  
                    if (index.equals("spl_IBAN")){
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
