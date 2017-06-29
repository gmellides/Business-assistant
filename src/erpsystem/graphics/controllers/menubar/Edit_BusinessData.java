/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import erpsystem.entities.corpotations.Business;
import erpsystem.util.xml.read.BusinessDataParser;
import erpsystem.util.xml.write.BusinessData;
import erpsystem.util.system.FileManager;
import erpsystem.util.xml.read.ComboBoxDataParser;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class Edit_BusinessData implements Initializable {

    @FXML
    private Button btnSave,btnClose,btn_Search;
    @FXML
    private DatePicker dtp_establishDate;
    @FXML
    private TextField txt_Name,txt_Address,txt_Phone,txt_Fax,txt_TaxReg,
                      txt_Mail,txt_Phone2;
    @FXML
    private TextArea txt_Description;
    @FXML
    private ImageView business_logo_view,icon_imageview;
    @FXML
    private ComboBox<String> cmb_City;

    private BusinessData BusinessXML;
    private ResourceBundle default_strings; 
    /**
     * Controller class init. 
     * Search if Business Data exists if yes the window
     * opens with old data if no the fields are blank.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_window(rb);
        
    }    

    // ===== FXML Buttons Action =====
        /**
         * Creates an xml file that contains the business data from this 
         * window. The data will be decrypted and after decryption will be 
         * saved in the XML file.
         * @param event 
         */
        @FXML
        private void btnSave_Action(ActionEvent event) {
            Business b_data = new Business(txt_Name.getText(),
                                           txt_Description.getText(),
                                           txt_Address.getText(),
                                           cmb_City.getSelectionModel().getSelectedItem().toString(),
                                           txt_Phone.getText(),
                                           txt_Phone2.getText(),
                                           txt_Fax.getText(),
                                           txt_TaxReg.getText(),
                                           txt_Mail.getText(),
                                           dtp_establishDate.getValue());
            BusinessData data_to_XML = new BusinessData();
            data_to_XML.save_data(b_data);

            Alert succed_dialog = new Alert(AlertType.INFORMATION);
            succed_dialog.setTitle(default_strings.getString("dialog_businessData_Title"));
            succed_dialog.setContentText(default_strings.getString("dialog_businessData_Message"));
            succed_dialog.showAndWait();
        }
        /**
         * Displays a file chooser and saves an image to user data folder.
         * In this File Chooser the user must choose the logo that business
         * use and after he click on save the logo will be copied at user data 
         * folder.
         * @param event 
         */
        @FXML
        private void btnSearch_Action(ActionEvent event) {
            Stage this_stage = (Stage) btn_Search.getScene().getWindow();
            FileChooser b_logo_chooser = new FileChooser();
            b_logo_chooser.setTitle("Λογοτυπο επιχείρησης");
            b_logo_chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Όλα τα αρχεία", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
            );
            File logo_file_chooser = b_logo_chooser.showOpenDialog(this_stage);
            if (logo_file_chooser == null){
                // No Action required 
                // When Cancel option 
                // is selected.
            }else{
                String extension =  logo_file_chooser.getName().substring(logo_file_chooser.getName().indexOf(".")+1,logo_file_chooser.getName().length());
                new FileManager().save_logo_img(logo_file_chooser,extension);
                Image this_logo = new Image(new File(new FileManager().getApp_data_business()+"/logo."+extension).toURI().toString());
                business_logo_view.setImage(this_logo);
            }
        } 
        /**
         * Closes the form.
         * @param event 
         */
        @FXML
        private void btnClose_Action(ActionEvent event) {
            Stage this_Stage = (Stage) btnClose.getScene().getWindow();
            this_Stage.hide();
        }
    // ===============================
        
    // === Input Check with RegEx ===
        @FXML
        private void Phone_Validator(KeyEvent event) {
            if(event.getText().matches("[0-9]")){
                txt_Phone.getStyleClass().remove("text_field_error");
            }else{
                txt_Phone.getStyleClass().add("text_field_error");
            }
        }
        @FXML
        private void Fax_Validator(KeyEvent event) {
            if(event.getText().matches("[0-9]")){
                // ok continue
            }else{
                
            }
        }
        @FXML
        private void TaxReg_Validator(KeyEvent event) {
            if(event.getText().matches("[0-9]")){
                // ok continue
            }else{
                
            }
        }
    // ===============================

    // ========== Methods ============
        /**
         * Search if user have already set his business logo 
         * and if yes it set it to the image view in the window.
         * if no this function sets a default photo in case to 
         * inform user about putting a logo.
         */
        private void set_businesslogo(){
            FileManager path = new FileManager();
            String[] logo_name = new String[]{"/logo.png","/logo.bmp","/logo.jpg"};
            for (String name : logo_name){
                File logo = new File(path.getApp_data_business()+name);
                if (logo.exists()){
                    business_logo_view.setImage(new Image(new File(new FileManager().getApp_data_business()+name).toURI().toString()));
                    break;
                }
            }
            if (business_logo_view.getImage() == null){
                business_logo_view.setImage(new Image(new File("resources/default_img/default.png").toURI().toString()));
            }    
        }
        /**
         * Sets data from XML (Works only when XML file is available)
         * @param business 
         */
        private void set_data(Business business){
            TextField[] textfields = {txt_Name,
                                      txt_Address,
                                      txt_Phone,
                                      txt_Phone2,
                                      txt_Fax,
                                      txt_TaxReg,
                                      txt_Mail};
            String[] string_data = {business.getName(),
                                    business.getAddress(),
                                    business.getPhone1(),
                                    business.getPhone2(),
                                    business.getFax(),
                                    business.getTaxReg(),
                                    business.getMail()};
            cmb_City.getSelectionModel().select(business.getCity());
            txt_Description.setText(business.getDescription());
            dtp_establishDate.setValue(business.getDate());
            int index = 0;
            for (TextField item : textfields){
                item.setText(string_data[index]);
                index++;
            }
        }
        /**
         * Initialize the window sets the combobox 
         * icon data from xml if its available and business logo.
         * @param rb 
         */
        private void init_window(ResourceBundle rb){
            // set combobox
                try{
                    cmb_City.setItems(new ComboBoxDataParser().get_big_cities_greece());
                }catch(Exception e){
                    e.printStackTrace();
                }
            // set icon 
                icon_imageview.setImage(new Image(new File("resources/images/menubar/edit_businessData.png").toURI().toString()));
            // set language
                default_strings = rb;
            // checks for data
                BusinessXML = new BusinessData();
                if(BusinessXML.get_File().exists()){
                    set_businesslogo();
                    set_data(new BusinessDataParser(BusinessXML.get_File()).getData());
                }else{
                   set_businesslogo(); 
                }
            // 
        }
    // ===============================
        
 
}
