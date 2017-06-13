/*
 * Title: NewContactSceen
 * Type: Java Class Controller
 * 
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.ContactsConnection;
import erpsystem.entities.people.Contact;
import erpsystem.util.system.WindowsManager;
import erpsystem.util.xml.read.ComboBox_Parser;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class NewContact implements Initializable {
    
        @FXML
        private ComboBox<String> sex_ComboBox;
        @FXML
        private ComboBox<String> phone1_type_ComboBox;
        @FXML
        private ComboBox<String> phone2_type_ComboBox;
        @FXML
        private ComboBox<String> countries_cmb;
        @FXML
        private ComboBox<String> state_cmb;
        @FXML
        private ComboBox<String> city_cmb;
        @FXML
        private Button btnClose;
        @FXML
        private Button btnSave;
        @FXML
        private TextField txt_firstname;
        @FXML
        private TextField txt_lastname;
        @FXML
        private TextField txt_address;
        @FXML
        private TextField txt_zipcode;
        @FXML
        private TextField txt_mail;
        @FXML
        private TextField txt_phone1;
        @FXML
        private TextField txt_phone2;
        @FXML
        private TextArea txt_comments;
        @FXML
        private TextField txt_website;
        @FXML
        private ImageView icon_img;
    // -- End of FXML Components Declaration
    
   private final WindowsManager window_check = new WindowsManager();
   private ComboBox_Parser combodata_xml;
   private ContactsConnection database;
   private ResourceBundle default_strings;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        init_comboboxes();
        set_style();
    }
    
    // -- FXML Componenents Action
        @FXML
        private void btnSave_Action(ActionEvent event) {  
            if (Check_fields()){
                Contact obj = new Contact();
                 data_to_obj(obj);                 
                 database = new ContactsConnection();
                 if(!database.insert_contact(obj)){
                     Alert succed_dialog = new Alert(Alert.AlertType.INFORMATION);
                     succed_dialog.setTitle(default_strings.getString("dialog_contactSaved_title"));
                     succed_dialog.setContentText(default_strings.getString("dialog_contactSaved_message"));
                     succed_dialog.showAndWait();
                     Stage window = (Stage) btnSave.getScene().getWindow();
                     window.close();
                     window_check.NewContact_toggle(false);
                 }else{
                     Alert succed_dialog = new Alert(Alert.AlertType.ERROR);
                     succed_dialog.setTitle(default_strings.getString("dialog_contactSaved_title"));
                     succed_dialog.setContentText(default_strings.getString("dialog_contactSaved_message"));
                     succed_dialog.showAndWait();
                     Stage window = (Stage) btnSave.getScene().getWindow();
                     window.close();
                     window_check.NewContact_toggle(false);
                 }
            }else{
               // error for fields 
            }
        }
        @FXML
        private void btnClose_Action(ActionEvent event) {
            window_check.NewContact_toggle(false);
            Stage current_stage = (Stage) btnClose.getScene().getWindow();
            current_stage.close();
        }
        @FXML
        private void cmbCountry_action(ActionEvent event) {
           String Selection =  countries_cmb.getSelectionModel().getSelectedItem();
           if (Selection.equals("Ελλάδα")){
                state_cmb.setDisable(false);
                city_cmb.setDisable(false);
           }else{
               state_cmb.getSelectionModel().clearSelection();
               state_cmb.setDisable(true);
               city_cmb.getSelectionModel().clearSelection();
               city_cmb.setDisable(true);
           }
        }
    // -- END of FXML Components Action
    
    // -- Methods   
        public void set_style(){
            icon_img.setImage(new Image(new File("resources/images/contacts/new_contact.png").toURI().toString()));
        }// set_style() 
        /**
         * Retrieve data from XML Files and place it into combo boxes
         */
        public void init_comboboxes(){
            combodata_xml = new ComboBox_Parser();
            try {
                countries_cmb.setItems(combodata_xml.get_countries());
                sex_ComboBox.setItems(combodata_xml.get_sex());
                ObservableList<String> temp_list = combodata_xml.get_phonetype();
                phone1_type_ComboBox.setItems(temp_list);
                phone2_type_ComboBox.setItems(temp_list);
                state_cmb.setItems(combodata_xml.get_states_greece());
                city_cmb.setItems(combodata_xml.get_big_cities_greece());
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }// init_comboboxes()
        public boolean Check_fields(){
            boolean flag = false;
                TextField[] fields = {txt_firstname,txt_lastname,txt_address,txt_zipcode,
                                      txt_mail,txt_phone1,txt_phone2,txt_website};
                for(TextField item: fields){
                    if (item.getText() != null){
                        flag = true;
                    }else{
                        flag = false;
                        break;
                    }
                }
            return flag;
        }
        private void data_to_obj(Contact obj){
            obj.setFirstName(txt_firstname.getText());
            obj.setLastName(txt_lastname.getText());
            obj.setAddress(txt_address.getText());
            obj.setSex(sex_ComboBox.getSelectionModel().getSelectedItem());
            obj.setComments(txt_comments.getText());
            obj.setWebsite(txt_website.getText());
            obj.setState(state_cmb.getSelectionModel().getSelectedItem());
            obj.setMail(txt_mail.getText());
            if(!city_cmb.isDisabled()){
                obj.setCity(city_cmb.getSelectionModel().getSelectedItem());
            }else{
                obj.setCity("----");
            }
            if (!countries_cmb.isDisabled()){
                obj.setCountry(countries_cmb.getSelectionModel().getSelectedItem());
            }else{
                obj.setCountry("----");
            }
            obj.setPhone_1(txt_phone1.getText());
            obj.setPhone_1_type(phone1_type_ComboBox.getSelectionModel().getSelectedItem());
            obj.setPhone_2(txt_phone2.getText());
            obj.setPhone_2_type(phone2_type_ComboBox.getSelectionModel().getSelectedItem());
            obj.setZipCode(Integer.parseInt(txt_zipcode.getText()));
        }
    // -- END of Methods
}
