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

public class CustomersCSV {
    
    private File csv_file;
    private BufferedWriter buffer;
    private FileWriter writer;  
    
    public boolean export_csv(ResourceBundle default_strings,
                              ObservableList<Map> data){
        boolean flag = false;
            csv_file = new File(new FileManager().getDocuments_BackUp_folder()+"/"+default_strings.getString("filename_customersCompanies_csv")+".csv");
            if (csv_file.exists()){
                csv_file.delete();
                flag = create_file(data);
            }else{
                flag = create_file(data);
            }
        return flag;
    }
    
    private boolean create_file(ObservableList<Map> raw_data){
        String[] csv_columns = {"cst_name","cst_isCompany","cst_lastname","cst_sex","cst_address","cst_zipcode",
                                "cst_city","cst_state","cst_country","cst_customerType","cst_phone",
                                "cst_fax","cst_mail"};
        try{
            writer = new FileWriter(csv_file);
            buffer = new BufferedWriter(writer);
            // CSV Header
            for (String item:csv_columns){
                buffer.write(item);
                if(!item.equals("cst_mail"))
                    buffer.write(",");
            }
            // Fill CSV with data.
            for (Map item : raw_data){
                buffer.write("\n");
                for (String index : csv_columns){  
                    if (index.equals("cst_mail")){
                        buffer.append(String.valueOf(item.get(index)));
                    }else{
                        buffer.append(String.valueOf(item.get(index)));
                        buffer.write(",");
                    }
                }
            }
            buffer.flush();
            writer.close();
            buffer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
    
}
