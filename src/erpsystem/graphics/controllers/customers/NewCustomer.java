/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.database.customers.CustomersDatabase;
import erpsystem.entities.corpotations.CustomerCompany;
import erpsystem.entities.people.Customer;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        default_strings = rb;
        init_window();
    }    

        @FXML
        private void isCompany_Action(ActionEvent event) {
            if(business_toggle.isSelected()){
                NewCustomer.isCompany = true;
                lbl_Name.setText(default_strings.getString("company_businessName"));
                txt_LastName.setDisable(true);
                cmb_sex.setDisable(true);
            }else{
                lbl_Name.setText(default_strings.getString("lbl_firstname"));
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
                    new CustomersDatabase().insert_company(input);
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
                    new CustomersDatabase().insert_customer(input);
                }   
        }
        
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        new WindowsManager().NewCustomer_toggle(false);
        Stage this_window = (Stage) btnClose.getScene().getWindow();
        this_window.close();
    }
    
    private void init_window(){
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

    private boolean check_null(){
        boolean flag = false;
        TextField[] window_fields = {txt_Name,txt_LastName,txt_address,txt_zipcode,
                                     txt_phone,txt_fax,txt_mail};
        ComboBox[] comboboxes = {cmb_sex,cmb_state,cmb_city,cmb_customerType,
                                 cmb_country};
        if (isCompany){
            for (TextField item : window_fields){
               if (item.getText() != null){
                   flag = true;
               }else if(item.isDisable()){
                   flag = true;
               }else{
                   flag = false;
                   break;
               }
            }   
            for (ComboBox item : comboboxes){
                if (item.getSelectionModel().getSelectedItem() != null){
                    flag = true;
                }else if (item.isDisable()){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
        }else{
            for (TextField item : window_fields){
               if (item.getText() != null){
                   flag = true;
               }else{
                   flag = false;
                   break;
               } 
            }
            for (ComboBox item : comboboxes){
                if (item.getSelectionModel().getSelectedItem() != null){
                    flag = true;
                }else{
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }
    
    private void OpenWindow(String WindowPath,
                            int Width,
                            int Height,
                            String WindowName) throws IOException{
         FXMLLoader fxml_loader = new FXMLLoader();
         fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
         Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/"+WindowPath).openStream());
         Stage stage = new Stage();
         Scene scene = new Scene(root);
         stage.setHeight(Height);
         stage.setWidth(Width);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                   new WindowsManager().toggle_window(WindowPath);
                    stage.close();
                }
            });
         stage.setTitle(default_strings.getString("lbl_windowtitle"));
         stage.setScene(scene);
         stage.setResizable(false);
                 // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                 // stage.getIcons().add(icon);
         stage.show();
    }
}
