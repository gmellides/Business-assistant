/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.export.csv.customers;

import erpsystem.util.datetime.DateTimeProvider;
import erpsystem.util.system.FileManager;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;


public class CustomersCompaniesExport {

    private FileManager workspace;
    private File csv_file;
    private BufferedWriter buffer;
    private FileWriter writer;
    
    public boolean export_file(ResourceBundle default_strings,
                              ObservableList<Map> data){
        
        return true;
    }
    
    private void create_file(ResourceBundle default_strings,
                             ObservableList<Map> input){
        String[] csv_columns = {"name","address","zipcode","city","state","country",
        "customer_type","phone","fax","mail","import_date"};
        
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
            }catch(IOException e){
                    e.printStackTrace();
            }
    } 
    
    
    private String FileName(ResourceBundle rb){
        return rb.getString("filename_customers_companies_csv")+".csv";
    }
}
