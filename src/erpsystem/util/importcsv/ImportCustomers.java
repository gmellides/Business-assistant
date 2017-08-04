/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.importcsv;

import erpsystem.database.customers.CustomersDatabase;
import erpsystem.entities.corpotations.CustomerCompany;
import erpsystem.entities.people.Customer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class ImportCustomers {
    
    private BufferedReader reader;
    private CustomersDatabase cust_db;
    
    public boolean import_csv(boolean isCompany,String file_path){
        if(isCompany){
            import_company(file_path);
        }else{
            import_person(file_path);
        }
        
        return true;
    }
    
    
    private void import_person(String file_path){
        cust_db = new CustomersDatabase();
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine();
                while((csv_line = reader.readLine())!= null){
                    String[] line = csv_line.split(",");
                    Customer cst = new Customer();
                    cust_db.insert_customer(cst);
                }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void import_company(String file_path){
        cust_db = new CustomersDatabase();
        String csv_line = null;
        try{
            reader = new BufferedReader(new FileReader(file_path));
            reader.readLine();
                while((csv_line = reader.readLine())!= null){
                    String[] line = csv_line.split(",");
                    CustomerCompany cst = new CustomerCompany();
                    cust_db.insert_company(cst);
                }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
