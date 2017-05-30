/*
 * Title: NewContactSceen
 * Type: Java Class Controller
 * 
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.Contacts_Operation;
import erpsystem.entities.people.Contact;
import erpsystem.util.datetime.DateTimeProvider;
import erpsystem.util.xml.read.ComboBox_Parser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewContact implements Initializable {
    // -- FXML Components Declaration
        @FXML
        private Pane newcontact_img;
        @FXML
        private Pane background_panel;
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
    // -- End of FXML Components Declaration
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_comboboxes();
        set_style();
    }
    
    // -- FXML Componenents Action
        @FXML
        private void btnSave_Action(ActionEvent event) {
            
            if (Check_fields()){
                Contact obj = new Contact();
                 obj.setFirstName(txt_firstname.getText());
                 obj.setLastName(txt_lastname.getText());
                 obj.setAddress(txt_address.getText());
                 obj.setSex(sex_ComboBox.getSelectionModel().getSelectedItem());
                 obj.setComments(txt_comments.getText());
                 obj.setWebsite(txt_website.getText());
                 obj.setState(state_cmb.getSelectionModel().getSelectedItem());
                 obj.setMail(txt_mail.getText());
                 obj.setCity(city_cmb.getSelectionModel().getSelectedItem());
                 obj.setCountry(city_cmb.getSelectionModel().getSelectedItem());
                 obj.setPhone_1(txt_phone1.getText());
                 obj.setPhone_1_type(phone1_type_ComboBox.getSelectionModel().getSelectedItem());
                 obj.setPhone_2(txt_phone2.getText());
                 obj.setPhone_2_type(phone2_type_ComboBox.getSelectionModel().getSelectedItem());
                 obj.setZipCode(Integer.parseInt(txt_zipcode.getText()));
                 obj.setImport_date(new DateTimeProvider().GetDateTime());
                 
                 database = new Contacts_Operation();
                 if(database.insert_contact(obj)){
                     // success
                 }else{
                     // error
                 }
            }else{
               // error for fields 
            }
        }
        @FXML
        private void btnClose_Action(ActionEvent event) {
            Stage current_stage = (Stage) btnClose.getScene().getWindow();
            current_stage.close();
        }
        @FXML
        private void cmbCountry_action(ActionEvent event) {
           String Selection =  countries_cmb.getSelectionModel().getSelectedItem();
           if (Selection.equals("Ελλάδα")){
                state_cmb.setDisable(false);
           }else{
              // state_cmb.getSelectionModel().clearSelection();
               state_cmb.setDisable(false);
           }
        }
    // -- END of FXML Components Action
    
    // -- Methods   
        public void set_style(){
            newcontact_img.setStyle("-fx-background-image: url('file://../resources/images/contacts/new_contact.png\');");
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
    // -- END of Methods
    
    private ComboBox_Parser combodata_xml;
    private Contacts_Operation database;
}
