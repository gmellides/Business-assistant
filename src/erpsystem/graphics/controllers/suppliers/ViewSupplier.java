/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.suppliers;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewSupplier implements Initializable {

    @FXML
    private Label lbl_name,lbl_bank,lbl_supplierType,lbl_lastname,lbl_sex,
    lbl_address,lbl_zipcode,lbl_country,lbl_state,lbl_city,lbl_phone,lbl_IBAN,
    lbl_ImportDate,lbl_fax,lbl_mail;

    private ResourceBundle default_strings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
    }    
    
    public void set_window(boolean isCompany,Map input){
        if (isCompany){
           Fill_Company(input);
        }else{   
           Fill_Person(input);
        }
    }
    public void Fill_Person(Map input){
        String Person_key = String.valueOf(input.get("supl_person_id"));
        Label[] labels = {lbl_name,lbl_lastname,lbl_sex,lbl_address,lbl_zipcode,
        lbl_country,lbl_state,lbl_supplierType,lbl_city,lbl_phone,lbl_mail,lbl_fax,
        lbl_bank,lbl_IBAN,lbl_ImportDate};
        String[] Lable_text = {"lbl_firstname","lbl_lastname","lbl_sex","lbl_address",
        "lbl_zipcode","lbl_country","lbl_state","lbl_supplierType","lbl_city","lbl_phone",
        "lbl_mail","lbl_fax","lbl_bankName","lbl_IBAN","lbl_date"};
        String[] Data = {"firstname","lastname","sex","address","zipcode","country",
        "state","supplier_type","city","phone","mail","fax","bank","iban","import_date"};
        int index = 0 ;
        for(Label item : labels){
            item.setText(default_strings.getString(Lable_text[index])+String.valueOf(input.get(Data[index])));
            index++;
        }
        System.out.println(Person_key);
    }
    public void Fill_Company(Map input){
        lbl_lastname.setVisible(false);
        lbl_sex.setVisible(false);
        Label[] labels = {lbl_name,lbl_address,lbl_zipcode,lbl_country,
        lbl_state,lbl_supplierType,lbl_city,lbl_phone,lbl_mail,lbl_fax,
        lbl_bank,lbl_IBAN,lbl_ImportDate};
        String[] Lable_text = {"company_businessName","lbl_address","lbl_zipcode",
        "lbl_country","lbl_state","lbl_supplierType","lbl_city","lbl_phone",
        "lbl_mail","lbl_fax","lbl_bankName","lbl_IBAN","lbl_date"};
        String[] Data = {"name","address","zipcode","country","state",
        "supplier_type","city","phone","mail","fax","bank","iban","import_date"};
        int index = 0 ;
        for(Label item : labels){
            item.setText(default_strings.getString(Lable_text[index])+String.valueOf(input.get(Data[index])));
            index++;
        }
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage window = (Stage) lbl_name.getScene().getWindow();
        window.close();
    }
}
