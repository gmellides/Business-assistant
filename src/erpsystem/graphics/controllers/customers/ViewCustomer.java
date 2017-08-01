/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.util.xml.read.ComboBoxDataParser;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewCustomer implements Initializable {

    @FXML
    private Label lbl_category_view,lbl_date_view,lbl_mail_view,lbl_phone_view,
    lbl_Fax_view,lbl_sex_view,lbl_CustomerType_view,lbl_ZipCode_view,lbl_City_view,
    lbl_State_view,lbl_Address_view,lbl_country_view,lbl_lastname_view,lbl_Name_view;
    @FXML
    private TextField txtName_edit,txtLastname_edit,txtAddress_edit,txtMail_edit,
    txtPhone_edit,txtFax_edit,txtZipcode_edit;
    @FXML
    private ComboBox<String> cmb_country,cmb_city,cmb_sex,cmb_customerType,cmb_state;
    @FXML
    private Button btnClose;     
    @FXML
    private Pane View_Panel,Edit_Panel;
    @FXML
    private ToggleButton btn_editToggle;
    
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
    @FXML
    private void btn_Edit_Action(ActionEvent event) {
        boolean flag = btn_editToggle.isSelected();
        if (flag){
            View_Panel.setVisible(false);
            Edit_Panel.setVisible(true);
            btn_editToggle.setText("Provoli");
        }else{
            View_Panel.setVisible(true);
            Edit_Panel.setVisible(false);
            btn_editToggle.setText("Epeksergasia");
        }
    }
    /*
       Public Method used as an interface between double click Event 
       at TableView. Fills the View and Edit Panel with Data and puts
       items in comboboxes.
    */
    public void set_window(boolean isCompany,Map input){
        if (isCompany){
            view_customer_company(input);
            edit_customer_company(input);
        }else{   
            view_customer_person(input);
            edit_customer_person(input);
        }
        try{
            cmb_country.setItems(new ComboBoxDataParser().get_countries());
            cmb_city.setItems(new ComboBoxDataParser().get_big_cities_greece());
            cmb_state.setItems(new ComboBoxDataParser().get_states_greece());
            cmb_sex.setItems(new ComboBoxDataParser().get_sex());  
            //cmb_customerType
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void view_customer_person(Map input){
        Label[] window_labels = {lbl_Name_view,lbl_lastname_view,lbl_sex_view,
            lbl_country_view,lbl_Address_view,lbl_State_view,lbl_City_view,
            lbl_ZipCode_view,lbl_CustomerType_view,lbl_phone_view,lbl_Fax_view,
            lbl_mail_view,lbl_date_view};
        String[] map_id = {"firstname","lastname","sex","country",
            "address","state","city","zipcode","customer_type","phone",
            "fax","mail","import_date"};
        String[] label_strings = {"lbl_firstname","lbl_lastname","lbl_sex","lbl_country",
            "lbl_address","lbl_state","lbl_city","lbl_zipcode","customer_type","lbl_phone",
            "lbl_fax","lbl_mail","lbl_date"};
        int index = 0;
        for (Label item : window_labels){
            item.setText(default_strings.getString(label_strings[index])+" "+String.valueOf(input.get(map_id[index])));
            index++;
        }
        lbl_category_view.setText(default_strings.getString("lbl_category_text")+" "+default_strings.getString("customer_cat"));
    }
    private void edit_customer_person(Map input){
        TextField[] fields = {txtName_edit,txtLastname_edit,txtAddress_edit,txtMail_edit,
                    txtPhone_edit,txtFax_edit,txtZipcode_edit};
        String[] map_id = {"firstname","lastname","address",
                 "mail","phone","fax","zipcode"};
        int index = 0;
        for (TextField item:fields){
            item.setText(String.valueOf(input.get(map_id[index])));
            index++;
        }
        cmb_country.getSelectionModel().select(String.valueOf(input.get("country")));
        cmb_city.getSelectionModel().select(String.valueOf(input.get("city")));
        cmb_sex.getSelectionModel().select(String.valueOf(input.get("sex")));
        cmb_customerType.getSelectionModel().select(String.valueOf(input.get("customer_type")));
        cmb_state.getSelectionModel().select(String.valueOf(input.get("state")));
    }
    // Fills the labels at View panel with data.
    private void view_customer_company(Map input){
        lbl_sex_view.setVisible(false);      
        lbl_lastname_view.setVisible(false);
        Label[] window_labels = {lbl_Name_view,lbl_country_view,lbl_Address_view,
                lbl_State_view,lbl_City_view,lbl_ZipCode_view,lbl_CustomerType_view,
                lbl_phone_view,lbl_Fax_view,lbl_mail_view,lbl_date_view};
        String[] companies_data_id = {"name","country","address","state","city",
                 "zipcode","customer_type","phone","fax","mail","import_date"};
        String[] label_strings = {"company_businessName","lbl_country","lbl_address",
                 "lbl_state","lbl_city","lbl_zipcode","customer_type","lbl_phone","lbl_fax",
                 "lbl_mail","lbl_date"};
        int index = 0;
        for (Label item : window_labels){
            item.setText(default_strings.getString(label_strings[index])+" "+String.valueOf(input.get(companies_data_id[index])));
            index++;
        }
        lbl_category_view.setText(default_strings.getString("lbl_category_text")+" "+default_strings.getString("company_cat"));
    }
    private void edit_customer_company(Map input){
        // Disable unsed components for Edit Panel
        lbl_sex_view.setVisible(false);      
        lbl_lastname_view.setVisible(false);
        txtLastname_edit.setVisible(false);
        cmb_sex.setVisible(false);
        TextField[] fields =  {txtName_edit,txtAddress_edit,txtMail_edit,
                    txtPhone_edit,txtFax_edit,txtZipcode_edit};
        String[] companies_data_id = {"name","address","mail",
                 "phone","fax","zipcode","customer_type"};
        int index = 0;
        for (TextField item : fields){
            item.setText(String.valueOf(input.get(companies_data_id[index])));
            index++;
        }
        cmb_country.getSelectionModel().select(String.valueOf(input.get("country")));
        cmb_city.getSelectionModel().select(String.valueOf(input.get("city")));
        cmb_customerType.getSelectionModel().select(String.valueOf(input.get("customer_type")));
        cmb_state.getSelectionModel().select(String.valueOf(input.get("state")));
    } 
}
