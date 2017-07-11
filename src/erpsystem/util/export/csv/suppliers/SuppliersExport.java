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
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;

public class SuppliersExport {
    private FileManager workspace;
    private File csv_file;
    private BufferedWriter buffer;
    private FileWriter writer;
    
    public boolean export_file(ResourceBundle default_strings,
                              ObservableList<Map> data){
        
        return true;
    }
    
    private void create_file(){
        
    } 
    
    
    private String FileName(){
        return "dd";
    }
}
