/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.importcsv;

import erpsystem.database.suppliers.SuppliersDatabase;
import erpsystem.entities.people.Supplier;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportSuppliers {
    SuppliersDatabase spl_db;
    BufferedReader reader;
    public boolean parse_csv(String file_path){
        spl_db = new SuppliersDatabase();
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine(); // For the First line of CSV File.
            
            while ((csv_line = reader.readLine())!=null){
                String[] val = csv_line.split(",");
                    Supplier obj = new Supplier();
                    
                spl_db.insert_supplier_person(obj);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
       return true;
    }
}
