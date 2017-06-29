/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.suppliers;

import erpsystem.util.xml.read.ComboBoxDataParser;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewSupplier implements Initializable {

    @FXML
    private Label lbl_name;
    @FXML
    private TextField txtName,txtLastName,txtAddress,txtZipCode,txtPhone,txtFax,
                     txtMail,txtBankName,txtIBAN;
    @FXML
    private ComboBox<String> cmbSex,cmbCountry,cmbState,cmbCity,cmbSupplierType;
    @FXML
    private CheckBox business_toggle;
    
    private ResourceBundle default_strings;
    private boolean isBusiness;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_window(rb);
    }    
    
    @FXML
    private void btn_Save_Action(ActionEvent event) {
        if (isBusiness){
            
            // AlertDialog
            close_window();
        }else{
            // AlertDialog
            close_window();
        }
    }
    
    @FXML
    private void isBusiness_Action(ActionEvent event) {
        if(business_toggle.isSelected()){
            isBusiness = true;
            lbl_name.setText(default_strings.getString("company_businessName"));
            txtLastName.setDisable(true);
            cmbSex.setDisable(true);
        }else{
            isBusiness = false;
        }
    }
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
    }
    
    private void init_window(ResourceBundle rb){
        isBusiness = false;
        default_strings = rb;
        try{
            cmbSex.setItems(new ComboBoxDataParser().get_sex());
            cmbCountry.setItems(new ComboBoxDataParser().get_countries());
            cmbState.setItems(new ComboBoxDataParser().get_states_greece());
            cmbCity.setItems(new ComboBoxDataParser().get_big_cities_greece());
            // cmbSupplierType
        }catch(Exception e){
            e.printStackTrace();
        }
        // image 
    }
    private void close_window(){
        ///   window manager toggle
        Stage window = (Stage) lbl_name.getScene().getWindow();
        window.close();
    }
}
