/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.database.customers.CST_Companies;
import erpsystem.database.customers.CST_Individual;
import erpsystem.entities.corpotations.CustomerCompany;
import erpsystem.entities.people.Customer;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.WindowsManager;
import erpsystem.util.xml.read.ComboBoxDataParser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewCustomer implements Initializable {

    @FXML
    private ImageView img_newContact;
    @FXML
    private Button btnClose;
    @FXML
    private Label lbl_Name;
    @FXML
    private TextField txt_Name,txt_LastName,txt_address,txt_zipcode,txt_phone,
                      txt_fax,txt_mail;
    @FXML
    private CheckBox business_toggle;
    @FXML
    private ComboBox<String> cmb_sex,cmb_state,cmb_city,cmb_customerType,cmb_country;
 
    private ResourceBundle default_strings;
    private static boolean isCompany;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_window(rb);
    }    

    /**
     * Input Check for Fields
     * @param event 
     */
        @FXML
        private void txt_Phone_check(KeyEvent event) {
            if(txt_phone.getText().matches("\\d+")){
                // No Action
            }else{
                Alert_dialog(Alert.AlertType.ERROR,
                                "dlg_inputStringError_title",
                                "dlg_inputStringError_header",
                                "dlg_inputStringError_message");
                txt_phone.setText("");
            }
        }
        @FXML
        private void txt_fax_check(KeyEvent event) {
            if(txt_fax.getText().matches("\\d+")){
                // No Action
            }else{
                Alert_dialog(Alert.AlertType.ERROR,
                                "dlg_inputStringError_title",
                                "dlg_inputStringError_header",
                                "dlg_inputStringError_message");
                txt_fax.setText("");
            }
        }
        @FXML
        private void txt_ZipCode_check(KeyEvent event) {
            if(txt_zipcode.getText().matches("\\d+")){
                // No Action
            }else{
                Alert_dialog(Alert.AlertType.ERROR,
                                "dlg_inputStringError_title",
                                "dlg_inputStringError_header",
                                "dlg_inputStringError_message");
                txt_zipcode.setText("");
            }
        }
        @FXML
        private void txt_firstname_check(KeyEvent event) {
            if(txt_Name.getText().matches("\\D+")){
                // No Action
            }else{
                Alert_dialog(Alert.AlertType.ERROR,
                                "dlg_inputStringError_title",
                                "dlg_inputStringError_header",
                                "dlg_inputStringError_message");
                txt_Name.setText("");
            }
        }
        @FXML
        private void txt_lastname_check(KeyEvent event) {
            if(txt_LastName.getText().matches("\\D+")){
                // No Action
            }else{
                Alert_dialog(Alert.AlertType.ERROR,
                                "dlg_inputStringError_title",
                                "dlg_inputStringError_header",
                                "dlg_inputStringError_message");
                txt_LastName.setText("");
            }
        }
        @FXML
        private void txt_address_check(KeyEvent event) {
            if(txt_address.getText().matches("\\D+")){
                // No Action
            }else{
                Alert_dialog(Alert.AlertType.ERROR,
                                "dlg_inputStringError_title",
                                "dlg_inputStringError_header",
                                "dlg_inputStringError_message");
                txt_address.setText("");
            }
        }
    /**
     * Components Events
     * @param event 
     */
        @FXML
        private void isCompany_Action(ActionEvent event) {
            if(business_toggle.isSelected()){
                NewCustomer.isCompany = true;
                lbl_Name.setText(default_strings.getString("company_businessName"));
                txt_LastName.setDisable(true);
                cmb_sex.setDisable(true);
            }else{
                lbl_Name.setText(default_strings.getString("gnr_lbl_firstname"));
                NewCustomer.isCompany = false;
                txt_LastName.setDisable(false);
                cmb_sex.setDisable(false);
            }
        }
        @FXML
        private void btnSave_Action(ActionEvent event) {
                if (isCompany){
                    CustomerCompany input = new CustomerCompany();
                        input.setCompanyName(txt_Name.getText());
                        input.setAddress(txt_address.getText());
                        input.setCity(cmb_city.getSelectionModel().getSelectedItem());
                        input.setZipCode(Integer.parseInt(txt_zipcode.getText()));
                        input.setCustomer_type(cmb_customerType.getSelectionModel().getSelectedItem());
                        input.setPhone(txt_phone.getText());
                        input.setMail(txt_mail.getText());
                        input.setFax(txt_fax.getText());
                        input.setCountry(cmb_country.getSelectionModel().getSelectedItem());
                    new CST_Companies().insert_company(input);
                    Alert_dialog(Alert.AlertType.INFORMATION,
                                 "dlg_customerSaved_title",
                                 "dlg_customerSaved_header",
                                 "dlg_customerSaved_message"); 
                    close_window();
                    OpenManager();
                }else{
                    Customer input = new Customer();
                        input.setFirstName(txt_Name.getText());
                        input.setLastName(txt_LastName.getText());
                        input.setSex(cmb_sex.getSelectionModel().getSelectedItem());
                        input.setAddress(txt_address.getText());
                        input.setZipCode(Integer.parseInt(txt_zipcode.getText()));
                        input.setCity(cmb_city.getSelectionModel().getSelectedItem());
                        input.setState(cmb_state.getSelectionModel().getSelectedItem());
                        input.setCountry(cmb_country.getSelectionModel().getSelectedItem());
                        input.setCustomer_Type(cmb_customerType.getSelectionModel().getSelectedItem());
                        input.setPhone(txt_phone.getText());
                        input.setFax(txt_fax.getText());
                        input.setMail(txt_mail.getText());
                    new CST_Individual().insert_customer(input);
                    Alert_dialog(Alert.AlertType.INFORMATION,
                                 "dlg_customerSaved_title",
                                 "dlg_customerSaved_header",
                                 "dlg_customerSaved_message"); 
                    close_window();
                    OpenManager();
                }   
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
            OpenManager();
        }
        
    /**
     * Private Methods used from this class.
     * @param rb 
     */
        private void init_window(ResourceBundle rb){
            default_strings = rb;
            NewCustomer.isCompany = false;
            // Set data to combo boxes
                try{
                    cmb_sex.setItems(new ComboBoxDataParser().get_sex());
                    cmb_state.setItems(new ComboBoxDataParser().get_states_greece());
                    cmb_city.setItems(new ComboBoxDataParser().get_big_cities_greece());
                    cmb_country.setItems(new ComboBoxDataParser().get_countries());
                    cmb_customerType.setItems(new ComboBoxDataParser().get_CustomerType());
                }catch(Exception e){
                    e.printStackTrace();
                }
            img_newContact.setImage(new Image(new File("resources/images/contacts/new_contact.png").toURI().toString()));
        }
        private void close_window(){
            new WindowsManager().NewCustomer_toggle(false);
            Stage this_window = (Stage) btnClose.getScene().getWindow();
            this_window.close();
        }
        private void OpenManager(){
            try{
                FXMLLoader fxml_loader = new FXMLLoader();
                fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/customers/CustomerManager.fxml").openStream());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(new Dimension().Manager_window_height);
                stage.setWidth(new Dimension().Manager_window_width);
                   stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                       @Override
                       public void handle(WindowEvent we) {
                           new WindowsManager().toggle_window("customers/CustomerManager.fxml");
                           stage.close();
                       }
                   });
                stage.setTitle(default_strings.getString("window_customer_manager"));
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
                stage.show();
            }catch(IOException e){
                e.printStackTrace();
            }
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
