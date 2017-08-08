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
    
    public boolean export_csv(boolean isCompany,
                             ResourceBundle default_strings,
                             ObservableList<Map> data){
        boolean flag = false;
            if (isCompany){
                csv_file = new File(new FileManager().getDocuments_root()+"/"+default_strings.getString("filename_customersCompanies_csv")+".csv");
                if (csv_file.exists()){
                    csv_file.delete();
                    flag = export_companies(data);
                }else{
                    flag = export_companies(data);
                }
            }else{
                csv_file = new File(new FileManager().getDocuments_root()+"/"+default_strings.getString("filename_customersIndevidual_csv")+".csv");
                if(csv_file.exists()){
                    csv_file.delete();
                    flag = export_indeviduals(data);   
                }else{
                    flag = export_indeviduals(data);
                }
            }
        return flag;
    }
    
    private boolean export_indeviduals(ObservableList<Map> raw_data){
        String[] csv_columns = {"firstname","lastname","sex","address","zipcode",
                                "city","state","country","customer_type","phone",
                                "fax","mail","import_date"};
        try{
            writer = new FileWriter(csv_file);
            buffer = new BufferedWriter(writer);
            // CSV Header
            for (String item:csv_columns){
                buffer.write(item);
                if(!item.equals("import_date"))
                    buffer.write(",");
            }
            // Fill CSV with data.
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
            writer.close();
            buffer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
    private boolean export_companies(ObservableList<Map> raw_data){
        String[] csv_columns = {"name","address","zipcode","city","state","country",
                                "customer_type","phone","fax","mail","import_date"};
        try{
            writer = new FileWriter(csv_file);
            buffer = new BufferedWriter(writer);
            // CSV Header
            for (String item:csv_columns){
                buffer.write(item);
                if(!item.equals("import_date"))
                    buffer.write(",");
            }
            // Fill CSV with data.
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
            writer.close();
            buffer.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return true;
    }
}
