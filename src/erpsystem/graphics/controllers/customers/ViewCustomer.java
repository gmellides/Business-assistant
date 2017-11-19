/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.database.customers.CST_View;
import erpsystem.entities.corpotations.CustomerCompany;
import erpsystem.entities.people.Customer;
import erpsystem.util.export.pdf.customers.CustomerPDF;
import erpsystem.util.xml.read.ComboBoxDataParser;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewCustomer implements Initializable {

    @FXML
    private Label lbl_category_view,lbl_date_view,lbl_mail_view,lbl_phone_view,
    lbl_Fax_view,lbl_sex_view,lbl_CustomerType_view,lbl_ZipCode_view,lbl_City_view,
    lbl_State_view,lbl_Address_view,lbl_country_view,lbl_lastname_view,lbl_Name_view,
    lbl_sex_edit,lbl_Lastname_edit,lbl_category_edt;
    @FXML
    private TextField txtName_edit,txtLastname_edit,txtAddress_edit,txtMail_edit,
    txtPhone_edit,txtFax_edit,txtZipcode_edit;
    @FXML
    private ComboBox<String> cmb_country,cmb_city,cmb_sex,cmb_customerType,cmb_state;
    @FXML
    private Button btnClose,btn_Delete,btn_SaveEdited,btn_ExportPDF;     
    @FXML
    private Pane View_Panel,Edit_Panel;
    @FXML
    private ToggleButton btn_editToggle;
    @FXML
    private ImageView viewCustomer_icon;
    
    private Map clicked_row;
    private ResourceBundle default_strings;
    private int customerID;
    private boolean isCompany;
 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        // Puts items to comboboxes at Edit Panel
        try{
            cmb_country.setItems(new ComboBoxDataParser().get_countries());
            cmb_city.setItems(new ComboBoxDataParser().get_big_cities_greece());
            cmb_state.setItems(new ComboBoxDataParser().get_states_greece());
            cmb_sex.setItems(new ComboBoxDataParser().get_sex());  
            cmb_customerType.setItems(new ComboBoxDataParser().get_CustomerType());
        }catch(Exception e){
            e.printStackTrace();
        }
        viewCustomer_icon.setImage(new Image(new File("resources/images/customers/customer_info.png").toURI().toString()));
    }
    /**
     * Component event Methods
     * @param event 
     */
        @FXML
        private void btn_Delete_Action(ActionEvent event) {
            if (new CST_View().delete_customer(customerID)){
                Alert_dialog(AlertType.INFORMATION,
                        "dlg_customerDelete_title",
                        "dlg_customerDelete_header",
                        "dlg_customerDelete_message");
            }
        }
        @FXML
        private void btn_SaveEdited_Action(ActionEvent event) {
           if (!isCompany){
               if (new CST_View().update_customer_individual(customerID, get_cst_obj())){
                   Alert_dialog(AlertType.INFORMATION,
                        "dlg_UpdateEntry_title",
                        "dlg_UpdateEntry_header",
                        "dlg_UpdateEntry_message");
                   close_window();
               }
           }else{
               if (new CST_View().update_customer_company(customerID, get_cmp_obj())){
                   Alert_dialog(AlertType.INFORMATION,
                        "dlg_UpdateEntry_title",
                        "dlg_UpdateEntry_header",
                        "dlg_UpdateEntry_message");
                   close_window();
               }
           }
        }
        @FXML
        private void btn_ExportPDF_Action(ActionEvent event) {
            if (new CustomerPDF().save_pdf(default_strings, clicked_row)){
                Alert_dialog(AlertType.INFORMATION,
                        "dlg_customerPDF_title",
                        "dlg_customerPDF_header",
                        "dlg_customerPDF_message");
            }
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
        /**
        * Toggle Button about edit and view panels.
        * @param event 
        */
        @FXML
        private void btn_Edit_Action(ActionEvent event) {
            if (btn_editToggle.isSelected()){
                btn_Delete.setVisible(true);
                btn_SaveEdited.setVisible(true);
                btnClose.setVisible(false);
                btn_ExportPDF.setVisible(false);
                View_Panel.setVisible(false);
                Edit_Panel.setVisible(true);
                btn_editToggle.setText(default_strings.getString("gnr_btn_view"));
            }else{
                btn_Delete.setVisible(false);
                btn_SaveEdited.setVisible(false);
                btnClose.setVisible(true);
                btn_ExportPDF.setVisible(true);
                View_Panel.setVisible(true);
                Edit_Panel.setVisible(false);
                btn_editToggle.setText(default_strings.getString("gnr_btn_edit"));
            }
        }
    /**
     * Public Method used as an interface between double click Event 
     * at TableView. Fills the View and Edit Panel with Data and puts
     * items in comboboxes.
     */
        public void set_window(boolean CompanyToogle,Map input){
            clicked_row = input;
            isCompany = CompanyToogle;
            if (CompanyToogle){
                view_customer_company();
                edit_customer_company();
            }else{   
                view_customer_individual();
                edit_customer_individual();
            }   
        }
    /*
        Loads data to View Panel for a customer 
    */
        private void view_customer_individual(){
            Label[] window_labels = {lbl_Name_view,lbl_lastname_view,lbl_sex_view,
                lbl_country_view,lbl_Address_view,lbl_State_view,lbl_City_view,
                lbl_ZipCode_view,lbl_CustomerType_view,lbl_phone_view,lbl_Fax_view,
                lbl_mail_view,lbl_date_view};
            String[] map_id = {"cst_name","cst_lastname","cst_sex","cst_country",
                "cst_address","cst_state","cst_city","cst_zipcode","cst_customerType","cst_phone",
                "cst_fax","cst_mail","cst_date"};
            String[] label_strings = {"gnr_lbl_firstname","gnr_lbl_lastname",
                "gnr_lbl_sex","gnr_lbl_country","gnr_lbl_address","gnr_lbl_state",
                "gnr_lbl_city","gnr_lbl_zipcode","customer_type","gnr_lbl_phone",
                "gnr_lbl_fax","gnr_lbl_mail","gnr_lbl_date"};
            int index = 0;
            for (Label item : window_labels){
                item.setText(default_strings.getString(label_strings[index])+" "+String.valueOf(clicked_row.get(map_id[index])));
                index++;
            }
            lbl_category_view.setText(default_strings.getString("lbl_category_text")+" "+default_strings.getString("customer_cat"));
        }
        private void edit_customer_individual(){
            customerID = Integer.parseInt(String.valueOf(clicked_row.get("cst_id")));
            TextField[] fields = {txtName_edit,txtLastname_edit,txtAddress_edit,txtMail_edit,
                        txtPhone_edit,txtFax_edit,txtZipcode_edit};
            String[] map_id = {"cst_name","cst_lastname","cst_address",
                     "cst_mail","cst_phone","cst_fax","cst_zipcode"};
            int index = 0;
            for (TextField item:fields){
                item.setText(String.valueOf(clicked_row.get(map_id[index])));
                index++;
            }
            lbl_category_edt.setText(default_strings.getString("lbl_category_text")+" "+default_strings.getString("customer_cat"));
            cmb_country.getSelectionModel().select(String.valueOf(clicked_row.get("cst_country")));
            cmb_city.getSelectionModel().select(String.valueOf(clicked_row.get("cst_city")));
            cmb_sex.getSelectionModel().select(String.valueOf(clicked_row.get("cst_sex")));
            cmb_customerType.getSelectionModel().select(String.valueOf(clicked_row.get("cst_customerType")));
            cmb_state.getSelectionModel().select(String.valueOf(clicked_row.get("cst_state")));
        }
        // Fills the labels at View panel with data.
        private void view_customer_company(){       
            lbl_sex_view.setVisible(false);      
            lbl_lastname_view.setVisible(false);
            Label[] window_labels = {lbl_Name_view,lbl_country_view,lbl_Address_view,
                    lbl_State_view,lbl_City_view,lbl_ZipCode_view,lbl_CustomerType_view,
                    lbl_phone_view,lbl_Fax_view,lbl_mail_view,lbl_date_view};
            String[] mapID = {"cst_name","cst_country","cst_address","cst_state","ccst_ity",
                    "cst_zipcode","cst_customerType","cst_phone","cst_fax","cst_mail","cst_date"};
            String[] label_strings = {"company_businessName","gnr_lbl_country","gnr_lbl_address",
                    "gnr_lbl_state","gnr_lbl_city","gnr_lbl_zipcode","customer_type","gnr_lbl_phone","gnr_lbl_fax",
                    "gnr_lbl_mail","gnr_lbl_date"};
            int index = 0;
            for (Label item : window_labels){
                item.setText(default_strings.getString(label_strings[index])+" "+String.valueOf(clicked_row.get(mapID[index])));
                index++;
            }
            lbl_category_view.setText(default_strings.getString("lbl_category_text")+" "+default_strings.getString("company_cat"));
        }
        private void edit_customer_company(){
            customerID = Integer.parseInt(String.valueOf(clicked_row.get("cst_id")));
            // Disable unsed components for Edit Panel
            lbl_sex_view.setVisible(false);      
            lbl_lastname_view.setVisible(false);
            txtLastname_edit.setVisible(false);
            cmb_sex.setVisible(false);
            lbl_sex_edit.setVisible(false);
            lbl_Lastname_edit.setVisible(false);
            TextField[] fields =  {txtName_edit,txtAddress_edit,txtMail_edit,
                        txtPhone_edit,txtFax_edit,txtZipcode_edit};
            String[] companies_data_id = {"cst_name","cst_address","cst_mail",
                     "cst_phone","cst_fax","cst_zipcode","cst_customerType"};
            int index = 0;
            for (TextField item : fields){
                item.setText(String.valueOf(clicked_row.get(companies_data_id[index])));
                index++;
            }
            lbl_category_edt.setText(default_strings.getString("lbl_category_text")+" "+default_strings.getString("company_cat"));
            cmb_country.getSelectionModel().select(String.valueOf(clicked_row.get("cst_country")));
            cmb_city.getSelectionModel().select(String.valueOf(clicked_row.get("cst_city")));
            cmb_customerType.getSelectionModel().select(String.valueOf(clicked_row.get("cst_customerType")));
            cmb_state.getSelectionModel().select(String.valueOf(clicked_row.get("cst_state")));
        } 
        private Customer get_cst_obj(){
            Customer obj = new Customer();
                obj.setFirstName(txtName_edit.getText());
                obj.setLastName(txtLastname_edit.getText());
                obj.setAddress(txtAddress_edit.getText());
                obj.setState(cmb_state.getSelectionModel().getSelectedItem());
                obj.setCity(cmb_city.getSelectionModel().getSelectedItem());
                obj.setCustomer_Type(cmb_customerType.getSelectionModel().getSelectedItem());
                obj.setCountry(cmb_country.getSelectionModel().getSelectedItem());
                obj.setFax(txtFax_edit.getText());
                obj.setPhone(txtPhone_edit.getText());
                obj.setMail(txtMail_edit.getText());
                obj.setSex(cmb_sex.getSelectionModel().getSelectedItem());
                obj.setZipCode(Integer.parseInt(txtZipcode_edit.getText()));
            return obj;
        }
        private CustomerCompany get_cmp_obj(){
            CustomerCompany obj = new CustomerCompany();
                obj.setAddress(txtAddress_edit.getText());
                obj.setCompanyName(txtName_edit.getText());
                obj.setState(cmb_state.getSelectionModel().getSelectedItem());
                obj.setCity(cmb_city.getSelectionModel().getSelectedItem());
                obj.setCustomer_type(cmb_customerType.getSelectionModel().getSelectedItem());
                obj.setCountry(cmb_country.getSelectionModel().getSelectedItem());
                obj.setFax(txtFax_edit.getText());
                obj.setPhone(txtPhone_edit.getText());
                obj.setMail(txtMail_edit.getText());
                obj.setZipCode(Integer.parseInt(txtZipcode_edit.getText()));
            return obj;
        }
        private void close_window(){
            Stage this_window = (Stage) btnClose.getScene().getWindow();
            this_window.close();
        }
        private void Alert_dialog(Alert.AlertType type,
                              String Title,
                              String Header,
                              String Message){
            Alert succed_dialog = new Alert(type);
            succed_dialog.setTitle(default_strings.getString(Title));
            succed_dialog.setHeaderText(default_strings.getString(Header));
            succed_dialog.setContentText(default_strings.getString(Message));
            succed_dialog.showAndWait();   
        }
}
