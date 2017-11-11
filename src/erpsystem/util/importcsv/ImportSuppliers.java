/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.importcsv;

import erpsystem.database.suppliers.SPL_Companies;
import erpsystem.database.suppliers.SPL_Individual;
import erpsystem.entities.corpotations.SupplierCompany;
import erpsystem.entities.people.Supplier;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportSuppliers {
    
    BufferedReader reader;
  
    public boolean import_csv(String file_path){
          import_file(file_path);
       return true;
    }
    
     private void import_file(String file_path){
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine();
                while((csv_line = reader.readLine())!= null){
                    String[] line = csv_line.split(",");
                    if (line[0].equals("FALSE")){
                        Supplier spl = new Supplier();
                            spl.setFirstName(line[1]);
                            spl.setLastName(line[2]);
                            spl.setSex(line[3]);
                            spl.setAddress(line[4]);
                            spl.setZipcode(Integer.parseInt(line[5]));
                            spl.setCity(line[6]);
                            spl.setState(line[7]);
                            spl.setCountry(line[8]);
                            spl.setSupplier_Type(line[9]);
                            spl.setPhone(line[10]);
                            spl.setFax(line[11]);
                            spl.setMail(line[12]);
                            spl.setBank(line[13]);
                            spl.setIBAN(line[14]);
                        new SPL_Individual().insert_supplier(spl);
                    }else{
                        SupplierCompany spl = new SupplierCompany();
                            spl.setCompanyName(line[1]);
                            spl.setAddress(line[4]);
                            spl.setZipCode(Integer.parseInt(line[5]));
                            spl.setCity(line[6]);
                            spl.setState(line[7]);
                            spl.setCountry(line[8]);
                            spl.setSupplierType(line[9]);
                            spl.setPhone(line[10]);
                            spl.setFax(line[11]);
                            spl.setMail(line[12]);
                            spl.setBank(line[13]);
                            spl.setIBAN(line[14]);
                        new SPL_Companies().insert_supplier(spl);
                    }
                }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
     }
}
