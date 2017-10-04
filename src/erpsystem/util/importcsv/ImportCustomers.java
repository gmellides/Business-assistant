/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.importcsv;

import erpsystem.database.customers.CST_Companies;
import erpsystem.database.customers.CST_Individual;
import erpsystem.entities.corpotations.CustomerCompany;
import erpsystem.entities.people.Customer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ImportCustomers {
    
    private BufferedReader reader;
    
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
                    if (line[1].equals("FALSE")){
                        Customer cst = new Customer();
                            cst.setFirstName(line[0]);
                            cst.setLastName(line[2]);
                            cst.setSex(line[3]);
                            cst.setAddress(line[4]);
                            cst.setZipCode(Integer.parseInt(line[5]));
                            cst.setCity(line[6]);
                            cst.setState(line[7]);
                            cst.setCountry(line[8]);
                            cst.setCustomer_Type(line[9]);
                            cst.setPhone(line[10]);
                            cst.setFax(line[11]);
                            cst.setMail(line[12]);
                        new CST_Individual().insert_customer(cst);
                    }else{
                        CustomerCompany cst = new CustomerCompany();
                            cst.setCompanyName(line[0]);
                            cst.setAddress(line[4]);
                            cst.setZipCode(Integer.parseInt(line[5]));
                            cst.setCity(line[6]);
                            cst.setState(line[7]);
                            cst.setCountry(line[8]);
                            cst.setCustomer_type(line[9]);
                            cst.setPhone(line[10]);
                            cst.setFax(line[11]);
                            cst.setMail(line[12]);
                        new CST_Companies().insert_company(cst);
                    }
                }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
}
