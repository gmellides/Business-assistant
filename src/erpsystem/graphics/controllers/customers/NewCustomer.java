/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.database.customers.CustomersDatabase;
import erpsystem.entities.business.Company;
import erpsystem.entities.people.Customer;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NewCustomer implements Initializable {

    
    private static boolean isCompany;
    @FXML
    private Button btnClose;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_window();
    }    

    @FXML
    private void isCompany_Action(ActionEvent event) {
        NewCustomer.isCompany = true;
        
        /// disable components
        
    }

    @FXML
    private void btnSave_Action(ActionEvent event) {
        if (isCompany){
            Company input = new Company();
            new CustomersDatabase().insert_company(input);
        }else{
            Customer input = new Customer();
            new CustomersDatabase().insert_customer(input);
        }   
    }
    
    
    private void init_window(){
        NewCustomer.isCompany = false;
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage this_window = (Stage) btnClose.getScene().getWindow();
        this_window.close();
    }
}
