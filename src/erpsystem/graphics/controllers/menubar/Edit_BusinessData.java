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
import erpsystem.util.xml.write.BusinessXML;

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
    private TextField txt_Description;
    @FXML
    private TextField txt_Mail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // check if data is available if yes display data
        // if not remains null 
    }    

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
        BusinessXML data_to_XML = new BusinessXML();
        data_to_XML.create_xml_stracture(b_data);
    }

    @FXML
    private void btnClose_Action(ActionEvent event) {
        Stage this_Stage = (Stage) btnClose.getScene().getWindow();
        this_Stage.hide();
    }

    @FXML
    private void btnSearch_Action(ActionEvent event) {
    }

}
