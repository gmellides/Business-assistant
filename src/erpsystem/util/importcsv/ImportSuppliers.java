/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.importcsv;

import erpsystem.database.suppliers.SPL_Companies;
import erpsystem.database.suppliers.SPL_Individual;
import erpsystem.database.suppliers.SPL_Database;
import erpsystem.entities.corpotations.SupplierCompany;
import erpsystem.entities.people.Supplier;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportSuppliers {
    SPL_Database spl_db;
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
        spl_db = new SPL_Database();
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine(); // For the First line of CSV File.
            
            while ((csv_line = reader.readLine())!=null){
                String[] val = csv_line.split(",");
                    SupplierCompany csv_spl = new SupplierCompany();
                    
                new SPL_Companies().insert_supplier(csv_spl);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void import_person(String file_path){
        spl_db = new SPL_Database();
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine(); // For the First line of CSV File.
            
            while ((csv_line = reader.readLine())!=null){
                String[] val = csv_line.split(",");
                Supplier spl = new Supplier();
                    spl.setFirstName(val[0]);
                    spl.setLastName(val[1]);
                    
                new SPL_Individual().insert_supplier(spl);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
