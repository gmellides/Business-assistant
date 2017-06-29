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
    private Label lbl_Name,lbl_lastname,lbl_sex,lbl_country,lbl_Address,lbl_State,
                  lbl_City,lbl_ZipCode,lbl_CustomerType,lbl_category,lbl_phone,
                  lbl_Fax,lbl_mail,lbl_date;
    @FXML
    private Button btnClose; 
    
    private ResourceBundle default_strings;

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
            lbl_sex.setVisible(false);      
            lbl_lastname.setVisible(false);
            Label[] window_labels = {lbl_Name,lbl_country,lbl_Address,lbl_State,
               lbl_City,lbl_ZipCode,lbl_CustomerType,lbl_phone,lbl_Fax,lbl_mail,
               lbl_date};
            String[] companies_data_id = {"name","country","address","state","city",
            "zipcode","customer_type","phone","fax","mail","import_date"};
            String [] label_strings = {"company_businessName","lbl_country","lbl_address",
            "lbl_state","lbl_city","lbl_zipcode","customer_type","lbl_phone","lbl_fax",
            "lbl_mail","lbl_date"};
            int index = 0;
            for (Label item : window_labels){
                item.setText(default_strings.getString(label_strings[index])+" "+String.valueOf(input.get(companies_data_id[index])));
                index++;
            }
            lbl_category.setText(default_strings.getString("lbl_category_text")+" "+default_strings.getString("company_cat"));
        }else{   
            Label[] window_labels = {lbl_Name,lbl_lastname,lbl_sex,lbl_country,
            lbl_Address,lbl_State,lbl_City,lbl_ZipCode,lbl_CustomerType,
            lbl_phone,lbl_Fax,lbl_mail,lbl_date};
            String[] customers_data_id = {"firstname","lastname","sex","country",
            "address","state","city","zipcode","customer_type","phone",
            "fax","mail","import_date"};
            String[] label_strings = {"lbl_firstname","lbl_lastname","lbl_sex","lbl_country",
            "lbl_address","lbl_state","lbl_city","lbl_zipcode","customer_type","lbl_phone",
            "lbl_fax","lbl_mail","lbl_date"};
            int index = 0;
            for (Label item : window_labels){
                item.setText(default_strings.getString(label_strings[index])+" "+String.valueOf(input.get(customers_data_id[index])));
                index++;
            }
            lbl_category.setText(default_strings.getString("lbl_category_text")+" "+default_strings.getString("customer_cat"));
        }
    }
}
