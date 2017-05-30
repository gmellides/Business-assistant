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
import erpsystem.entities.business.Business;
import erpsystem.util.xml.read.BusinessXML_Parser;
import erpsystem.util.xml.write.BusinessData;
import erpsystem.util.system.FileManager;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

public class Edit_BusinessData implements Initializable {

    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;
    @FXML
    private DatePicker dtp_establishDate;
    @FXML
    private TextField txt_Name;
    @FXML
    private TextField txt_Address;
    @FXML
    private TextField txt_Phone;
    @FXML
    private TextField txt_City;
    @FXML
    private TextField txt_Fax;
    @FXML
    private Button btn_Search;
    @FXML
    private TextField txt_TaxReg;
    @FXML
    private TextArea txt_Description;
    @FXML
    private TextField txt_Mail;
    @FXML
    private ImageView business_logo_view;
    @FXML
    private Pane background_pane;
    @FXML
    private ImageView icon_imageview;

    /**
     * Controller class init. 
     * Search if Business Data exists if yes the window
     * opens with old data if no the fields are blank.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_background_and_icon();
        language_string = rb;
        BusinessXML = new BusinessData();
        if(BusinessXML.get_File().exists()){
            set_businesslogo();
            BusinessXML_Parser xmlParser = new BusinessXML_Parser(BusinessXML.get_File());
            set_data(xmlParser.getData());
        }
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
                                           txt_City.getText(),
                                           txt_Phone.getText(),
                                           txt_Fax.getText(),
                                           txt_TaxReg.getText(),
                                           txt_Mail.getText(),
                                           dtp_establishDate.getValue());
            BusinessData data_to_XML = new BusinessData();
            data_to_XML.save_data(b_data);

            Alert succed_dialog = new Alert(AlertType.INFORMATION);
            succed_dialog.setTitle("Επιτυχείς Αποθήκευση");
            succed_dialog.setContentText("Τα στοιχεία της επιχείρησης σας έχουν αποθηκεύει επιτυχώς. Μπορείτε να τα επεξεργαστείτε ανά πάσα στιγμή και πάλι από αυτό το παράθυρο. ");
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
        
    // ========== Methods ============
        /**
         * Search if user have already set his business logo 
         * and if yes it set it to the image view in the window.
         * if no this function sets a default photo in case to 
         * inform user about putting a logo.
         */
        public void set_businesslogo(){
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
        
        public void set_data(Business data){
            TextField[] textfields = {txt_Name,
                                      txt_Address,
                                      txt_Phone,
                                      txt_City,
                                      txt_Fax,
                                      txt_TaxReg,
                                      txt_Mail};
            String[] string_data = {data.getBusiness_Name(),
                                    data.getBusiness_Address(),
                                    data.getBusiness_Phone(),
                                    data.getBusiness_City(),
                                    data.getBusiness_Fax(),
                                    data.getBusiness_TaxReg(),
                                    data.getBusiness_Mail()};
            int index = 0;
            for (TextField item : textfields){
                item.setText(string_data[index]);
                index++;
            }
            txt_Description.setText(data.getBusiness_Description());
            dtp_establishDate.setValue(data.getBusiness_Date());
        }
        
        public void set_background_and_icon(){
           icon_imageview.setImage(new Image(new File("resources/images/menubar/edit_businessData.png").toURI().toString()));
        }
    // ===============================
        
    private BusinessData BusinessXML;
    private ResourceBundle language_string;
}
