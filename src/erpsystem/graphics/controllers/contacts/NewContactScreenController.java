/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.util.parsers.c_ContactsComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NewContactScreenController implements Initializable {
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
    // -- End of FXML Components Declaration
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_comboboxes();
        set_style();
    }
    
    // -- FXML Componenents Action
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
               state_cmb.getSelectionModel().clearSelection();
               state_cmb.setDisable(true);
           }
        }
    // -- END of FXML Components Action
    
    // -- Methods
        public void set_style(){
            newcontact_img.setStyle("-fx-background-image: url('file://../resources/images/contacts/new_contact.png\');");
            background_panel.setStyle("-fx-background-color: #FFFFFF;");
        }
        public void init_comboboxes(){
            combodata_xml = new c_ContactsComboBox();
            try {
                countries_cmb.setItems(combodata_xml.get_countries());
                sex_ComboBox.setItems(combodata_xml.get_sex());
                ObservableList<String> temp_list = combodata_xml.get_phonetype();
                phone1_type_ComboBox.setItems(temp_list);
                phone2_type_ComboBox.setItems(temp_list);
                state_cmb.setItems(combodata_xml.get_states_greece());
                //city_cmb.setItems(combodata_xml.get_big_cities_greece());
            } catch (Exception ex) {
               ex.printStackTrace();
            }
        }
    // -- END of Methods
    
    private c_ContactsComboBox combodata_xml;
}
