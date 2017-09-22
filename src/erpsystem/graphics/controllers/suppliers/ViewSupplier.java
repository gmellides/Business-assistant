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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ViewSupplier implements Initializable {

    @FXML
    private Label lbl_name,lbl_bank,lbl_supplierType,lbl_lastname,lbl_sex,
    lbl_address,lbl_zipcode,lbl_country,lbl_state,lbl_city,lbl_phone,lbl_IBAN,
    lbl_ImportDate,lbl_fax,lbl_mail;

    private ResourceBundle default_strings;
    @FXML
    private Button btn_close;
    @FXML
    private Button btn_Save;

    private Map clicked_row;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
    }    
    
    public void set_window(boolean isCompany,Map input){
        clicked_row = input;
        if (isCompany){
           set_splCompany_View();
        }else{   
           set_splIndividual_View();
        }
    } 
    @FXML
    private void btn_SaveEdited_Action(ActionEvent event) {
    }
    private void set_splIndividual_View(){
        String Person_key = String.valueOf(clicked_row.get("supl_person_id"));
        Label[] labels = {lbl_name,lbl_lastname,lbl_sex,lbl_address,lbl_zipcode,
        lbl_country,lbl_state,lbl_supplierType,lbl_city,lbl_phone,lbl_mail,lbl_fax,
        lbl_bank,lbl_IBAN,lbl_ImportDate};
        String[] Lable_text = {"gnr_lbl_firstname","gnr_lbl_lastname","gnr_lbl_sex","gnr_lbl_address",
        "gnr_lbl_zipcode","gnr_lbl_country","gnr_lbl_state","lbl_supplierType","gnr_lbl_city","gnr_lbl_phone",
        "gnr_lbl_mail","gnr_lbl_fax","lbl_bankName","lbl_IBAN","gnr_lbl_date"};
        String[] Data = {"firstname","lastname","sex","address","zipcode","country",
        "state","supplier_type","city","phone","mail","fax","bank","iban","import_date"};
        int index = 0 ;
        for(Label item : labels){
            item.setText(default_strings.getString(Lable_text[index])+" "+String.valueOf(clicked_row.get(Data[index])));
            index++;
        }
        System.out.println(Person_key);
    }
    private void set_splIndividual_Edit(){
        
    }
    private void set_splCompany_View(){
        lbl_lastname.setVisible(false);
        lbl_sex.setVisible(false);
        Label[] labels = {lbl_name,lbl_address,lbl_zipcode,lbl_country,
        lbl_state,lbl_supplierType,lbl_city,lbl_phone,lbl_mail,lbl_fax,
        lbl_bank,lbl_IBAN,lbl_ImportDate};
        String[] Lable_text = {"company_businessName","gnr_lbl_address","gnr_lbl_zipcode",
        "gnr_lbl_country","gnr_lbl_state","lbl_supplierType","gnr_lbl_city","gnr_lbl_phone",
        "gnr_lbl_mail","gnr_lbl_fax","lbl_bankName","lbl_IBAN","gnr_lbl_date"};
        String[] Data = {"name","address","zipcode","country","state",
        "supplier_type","city","phone","mail","fax","bank","iban","import_date"};
        int index = 0 ;
        for(Label item : labels){
            item.setText(default_strings.getString(Lable_text[index])+" "+String.valueOf(clicked_row.get(Data[index])));
            index++;
        }
    }
    private void set_splCompany_Edit(){
        
    }
   @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
    }
    private void close_window(){
        Stage window = (Stage) lbl_name.getScene().getWindow();
        window.close();
    }
}
