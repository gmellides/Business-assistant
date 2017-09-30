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
    
    public boolean import_csv(boolean isCompany,String file_path){
        if(isCompany){
            import_company(file_path);
        }else{
            import_indevidual(file_path);
        }
        return true;
    }
    
    
    private void import_indevidual(String file_path){
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine();
                while((csv_line = reader.readLine())!= null){
                    String[] line = csv_line.split(",");
                    Customer cst = new Customer();
                       cst.setFirstName(line[0]);
                       cst.setLastName(line[1]);
                       cst.setSex(line[2]);
                       cst.setAddress(line[3]);
                       cst.setZipCode(Integer.parseInt(line[4]));
                       cst.setCity(line[5]);
                       cst.setState(line[6]);
                       cst.setCountry(line[7]);
                       cst.setCustomer_Type(line[8]);
                       cst.setPhone(line[9]);
                       cst.setFax(line[10]);
                       cst.setMail(line[11]);
                    new CST_Individual().insert_customer(cst);
                }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void import_company(String file_path){
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine();
                while((csv_line = reader.readLine())!= null){
                    String[] line = csv_line.split(",");
                    CustomerCompany cst = new CustomerCompany();
                        cst.setCompanyName(line[0]);
                        cst.setAddress(line[1]);
                        cst.setZipCode(Integer.parseInt(line[2]));
                        cst.setCity(line[3]);
                        cst.setState(line[4]);
                        cst.setCountry(line[5]);
                        cst.setCustomer_type(line[6]);
                        cst.setPhone(line[7]);
                        cst.setFax(line[8]);
                        cst.setMail(line[9]);
                    new CST_Companies().insert_company(cst);
                }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
