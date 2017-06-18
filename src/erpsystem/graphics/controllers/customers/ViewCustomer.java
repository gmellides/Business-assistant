/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewCustomer implements Initializable {

    @FXML
    private Label lbl_name_Text,lbl_lastname_text,lbl_sex,lbl_name,lbl_lastname,
                  sex_label,lbl_Country,lbl_Address,lbl_state,lbl_City,lbl_zipcode,
                  lbl_customerType,lbl_Phone,lbl_fax,lbl_mail,lbl_date,lbl_Category;

    private ResourceBundle default_strings;
    @FXML
    private Button btnClose;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
    }  

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage this_window = (Stage) btnClose.getScene().getWindow();
        this_window.close();
    }
    
    public void set_window(boolean isBusiness,Map input){
        if (isBusiness){
            lbl_name_Text.setText(default_strings.getString("company_businessName"));
            lbl_lastname_text.setVisible(false);
            lbl_sex.setVisible(false);
            sex_label.setVisible(false);
            lbl_lastname.setVisible(false);
            lbl_Category.setText(default_strings.getString("company_cat"));
            Label[] window_labels = {lbl_name,lbl_Country,lbl_Address,
            lbl_state,lbl_City,lbl_zipcode,lbl_customerType,lbl_Phone,lbl_fax,
            lbl_mail,lbl_date};
            String[] companies_id = {"name","country","address","state","city",
            "zipcode","customer_type","phone","fax","mail","import_date"};
           
            int index = 0;
            for (Label item : window_labels){
                item.setText(String.valueOf(input.get(companies_id[index])));
                index++;
            }
            
        }else{  
            lbl_Category.setText(default_strings.getString("customer_cat"));
            Label[] window_labels = {lbl_name,lbl_lastname,sex_label,lbl_Address,lbl_zipcode,lbl_City,lbl_state,
                  lbl_Country,lbl_customerType,lbl_Phone,lbl_fax,lbl_mail,lbl_date};
            String[]  customer_id = {"firstname","lastname","sex",
            "address","zipcode","city","state","country","customer_type","phone",
            "fax","mail","import_date"};
            int index = 0;
            for (Label item : window_labels){
                item.setText(String.valueOf(input.get(customer_id[index])));
                index++;
            }
            
        }
    }
}
