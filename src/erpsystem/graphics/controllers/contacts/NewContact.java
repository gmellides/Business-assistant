/*
 * Title: NewContactSceen
 * Type: Java Class Controller
 * 
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.ContactsDatabase;
import erpsystem.entities.people.Contact;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.WindowsManager;
import erpsystem.util.xml.read.ComboBoxDataParser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewContact implements Initializable {
    
        @FXML
        private ComboBox<String> sex_ComboBox,phone1_type_ComboBox,phone2_type_ComboBox,
                                 countries_cmb,state_cmb,city_cmb;
        @FXML
        private Button btnClose,btnSave;
        @FXML
        private TextField txt_firstname,txt_lastname,txt_address,txt_zipcode,
                          txt_mail,txt_phone1,txt_phone2,txt_website;
        @FXML
        private TextArea txt_comments;
        @FXML
        private ImageView icon_img;
    // -- End of FXML Components Declaration
    
   private final WindowsManager window_check = new WindowsManager();
   private ComboBoxDataParser combodata_xml;
   private ContactsDatabase database;
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
            Contact obj = new Contact();
             data_to_obj(obj);                 
             database = new ContactsDatabase();
            if(!database.insert_contact(obj)){
                Alert succed_dialog = new Alert(Alert.AlertType.INFORMATION);
                succed_dialog.setTitle(default_strings.getString("dlg_contactSaved_title"));
                succed_dialog.setContentText(default_strings.getString("dlg_contactSaved_message"));
                succed_dialog.showAndWait();
                Stage window = (Stage) btnSave.getScene().getWindow();
                window.close();
                window_check.NewContact_toggle(false);
                OpenManager();
            }else{
                Alert succed_dialog = new Alert(Alert.AlertType.ERROR);
                succed_dialog.setTitle(default_strings.getString("dlg_contactSaved_title"));
                succed_dialog.setContentText(default_strings.getString("dlg_contactSaved_message"));
                succed_dialog.showAndWait();
                Stage window = (Stage) btnSave.getScene().getWindow();
                window.close();
                window_check.NewContact_toggle(false);
                OpenManager();
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
            combodata_xml = new ComboBoxDataParser();
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
        private void OpenManager(){
            try{
                FXMLLoader fxml_loader = new FXMLLoader();
                fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/contacts/ContactManager.fxml").openStream());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(new Dimension().Manager_window_height);
                stage.setWidth(new Dimension().Manager_window_width);
                   stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                       @Override
                       public void handle(WindowEvent we) {
                           new WindowsManager().toggle_window("contacts/ContactManager.fxml");
                           stage.close();
                       }
                   });
                stage.setTitle(default_strings.getString("window_contact_manager"));
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
                stage.show();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    // -- END of Methods
}
