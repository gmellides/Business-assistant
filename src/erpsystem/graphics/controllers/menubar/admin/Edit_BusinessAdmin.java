/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar.admin;

import erpsystem.util.xml.read.AdminDataParser;
import erpsystem.util.system.FileManager;
import erpsystem.entities.corpotations.BusinessAdmin;
import erpsystem.util.xml.read.ComboBoxDataParser;
import erpsystem.util.xml.write.AdminData;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Edit_BusinessAdmin implements Initializable {

    @FXML
    private Pane background_pane;
    @FXML
    private ImageView icon_imageview;
    @FXML
    private ComboBox<String> cmb_Sex,cmb_City;
    @FXML
    private TextField txt_FirstName,txt_LastName,txt_Address,txt_Phone1,
                      txt_Phone2,txtMail,txt_TaxReg,txt_ZipCode;
    @FXML
    private DatePicker dtp_Birthdate;
    @FXML
    private TextArea txt_Description;

    private ResourceBundle default_strings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        workspace = new FileManager();
        default_strings = rb;
        if(init_window()){
            if(new File(workspace.getApp_data_admin()+"/admin_data.xml").exists()){
                admin_xml = new AdminDataParser(new File(workspace.getApp_data_admin()+"/admin_data.xml"));
                put_data(admin_xml.getData());
            }
        }
    }   
    
    // ===== FXML Buttons Action =====
        @FXML
        private void btn_Save_Action(ActionEvent event) {
            BusinessAdmin obj = new BusinessAdmin(txt_FirstName.getText(),
                                                  txt_LastName.getText(),
                                                  dtp_Birthdate.getValue(),
                                                  cmb_Sex.getSelectionModel().getSelectedItem(),
                                                  txt_Address.getText(),
                                                  Integer.parseInt(txt_ZipCode.getText()),
                                                  cmb_City.getSelectionModel().getSelectedItem(),
                                                  txt_Phone1.getText(),
                                                  txt_Phone2.getText(),
                                                  txt_Description.getText(),
                                                  txt_TaxReg.getText(),
                                                  txtMail.getText());
            admin_xml_creator = new AdminData();
            if(admin_xml_creator.save_data(obj)){
                Alert_dialog(AlertType.INFORMATION,
                             "",
                             "",
                             "");
                close_window();
            }  
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
        @FXML
        private void ZipCode_Validation(KeyEvent event) {
            String input = event.getText();
             if(input.matches("[0-9]")){
                txt_ZipCode.getStyleClass().remove("text_field_error");
             }else{
                txt_ZipCode.getStyleClass().add("text_field_error");
             }
        }
    // ===============================
    public void close_window(){
        Stage this_stage = (Stage) background_pane.getScene().getWindow();
        this_stage.close();
    }
    
    
    public boolean init_window(){
        if(init_combobox())
            icon_imageview.setImage(new Image(new File("resources/images/menubar/edit_AdminData.png").toURI().toString()));
        return true;
    }
    public boolean init_combobox(){
        boolean flag = false;
            comboData = new ComboBoxDataParser();
            try{
                cmb_Sex.setItems(comboData.get_sex());   
                cmb_City.setItems(comboData.get_big_cities_greece());
                flag = true;
            }catch(Exception e){
                e.printStackTrace();
            } 
        return flag;
    } 
    
    
    private boolean put_data(BusinessAdmin admin){
        boolean flag = false;
            TextField[] fields = {txt_FirstName,txt_LastName,txt_Address,
                                  txt_Phone1,txt_Phone2,txtMail,txt_TaxReg,
                                  txt_ZipCode};
            String[] data = {admin.getFirstName(),admin.getLastName(),admin.getAddress(),
                             admin.getPhone1(),admin.getPhone2(),admin.getMail(),admin.getTaxReg(),
                             String.valueOf(admin.getZipCode())};
            cmb_Sex.getSelectionModel().select(admin.getSex());
            cmb_City.getSelectionModel().select(admin.getCity());
            dtp_Birthdate.setValue(admin.getBirthdate());
            txt_Description.setText(admin.getDescription());
            int index = 0;
            for (TextField item:fields){
                item.setText(data[index]);
                index++;
            }
            
        return flag;
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
    private FileManager workspace;
    private ComboBoxDataParser comboData;
    private AdminData admin_xml_creator;   
    private AdminDataParser admin_xml;
}
