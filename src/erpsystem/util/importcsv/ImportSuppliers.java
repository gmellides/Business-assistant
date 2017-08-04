/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.importcsv;

import erpsystem.database.suppliers.SuppliersDatabase;
import erpsystem.entities.corpotations.SupplierCompany;
import erpsystem.entities.people.Supplier;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportSuppliers {
    SuppliersDatabase spl_db;
    BufferedReader reader;
    public boolean import_csv(boolean isCompany,String file_path){
        if(isCompany){
            import_company(file_path);
        }else{
            import_person(file_path);
        }
       return true;
    }
    
    private void import_company(String file_path){
        spl_db = new SuppliersDatabase();
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine(); // For the First line of CSV File.
            
            while ((csv_line = reader.readLine())!=null){
                String[] val = csv_line.split(",");
                    SupplierCompany csv_spl = new SupplierCompany();
                    
                spl_db.insert_supplier_company(csv_spl);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void import_person(String file_path){
        spl_db = new SuppliersDatabase();
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine(); // For the First line of CSV File.
            
            while ((csv_line = reader.readLine())!=null){
                String[] val = csv_line.split(",");
                Supplier csv_spl = new Supplier();
                    
                spl_db.insert_supplier_person(csv_spl);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
