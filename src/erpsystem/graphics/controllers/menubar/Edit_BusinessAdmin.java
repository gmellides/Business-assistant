/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar;

import erpsystem.util.xml.read.AdminXML_Parser;
import erpsystem.util.system.FileManager;
import erpsystem.entities.business.BusinessAdmin;
import erpsystem.util.xml.read.ComboBox_Parser;
import erpsystem.util.xml.write.AdminData;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private ComboBox<String> cmb_Sex;
    @FXML
    private ComboBox<String> cmb_City;
    @FXML
    private TextField txt_FirstName;
    @FXML
    private TextField txt_LastName;
    @FXML
    private DatePicker dtp_Birthdate;
    @FXML
    private TextField txt_Address;
    @FXML
    private TextField txt_Phone1;
    @FXML
    private TextField txt_Phone2;
    @FXML
    private TextField txtMail;
    @FXML
    private TextArea txt_Description;
    @FXML
    private TextField txt_TaxReg;
    @FXML
    private TextField txt_ZipCode;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        workspace = new FileManager();
        if(init_window()){
            if(new File(workspace.getApp_data_admin()+"/admin_data.xml").exists()){
                admin_xml = new AdminXML_Parser(new File(workspace.getApp_data_admin()+"/admin_data.xml"));
                put_data(admin_xml.getData());
            }
        }
    }   
    
    // ===== FXML Buttons Action =====
        @FXML
        private void btn_Save_Action(ActionEvent event) {
            if (check_fields()){
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
                    // Success Dialog
                }
            }
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            Stage this_stage = (Stage) background_pane.getScene().getWindow();
            this_stage.close();
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
    
    
    
    public boolean init_window(){
        if(init_combobox())
            icon_imageview.setImage(new Image(new File("resources/images/menubar/edit_AdminData.png").toURI().toString()));
        return true;
    }
    public boolean init_combobox(){
        boolean flag = false;
            comboData = new ComboBox_Parser();
            try{
                cmb_Sex.setItems(comboData.get_sex());   
                cmb_City.setItems(comboData.get_big_cities_greece());
                flag = true;
            }catch(Exception e){
                e.printStackTrace();
            } 
        return flag;
    } 
    private boolean check_fields(){
        boolean flag = false;
            TextField[] fields = {txt_FirstName,txt_LastName,txt_Address,
                                  txt_ZipCode,txt_TaxReg,txt_Phone1,
                                  txt_Phone2,txtMail};
            for (TextField item : fields){
                if (item.getText() == null){
                    flag = false;
                    break;
                }
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
    
    private FileManager workspace;
    private ComboBox_Parser comboData;
    private AdminData admin_xml_creator;   
    private AdminXML_Parser admin_xml;
}
