/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar;

import erpsystem.util.xml.read.ComboBox_Parser;
import erpsystem.util.xml.write.BusinessAdminData;
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
      if (set_data_combobox()){
          set_background_and_icon();
      }    
    }   
    
    @FXML
    private void btn_Save_Action(ActionEvent event) {
        admin_xml_creator = new BusinessAdminData();
        
    }
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage this_stage = (Stage) background_pane.getScene().getWindow();
        this_stage.close();
    }
    
    public void set_background_and_icon(){
        Image icon = new Image(new File("resources/images/menubar/edit_AdminData.png").toURI().toString());
        icon_imageview.setImage(icon);
        background_pane.setStyle("-fx-background-color: #FFFFFF;");
    }
    public boolean set_data_combobox(){
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
    
    private ComboBox_Parser comboData;
    private BusinessAdminData admin_xml_creator;
}
