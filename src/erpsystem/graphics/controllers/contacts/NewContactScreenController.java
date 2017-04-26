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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class NewContactScreenController implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_comboboxes();
        SetImages();    
    }
    
    public void SetImages(){
        newcontact_img.setStyle("-fx-background-image: url('file://../resources/images/contacts/new_contact.png\');");
        background_panel.setStyle("-fx-background-color: #FFFFFF;");
    }
    public void init_comboboxes(){
        d = new c_ContactsComboBox();
        try {
            countries_cmb.setItems(d.get_countries());
            sex_ComboBox.setItems(d.get_sex());
            ObservableList<String> temp_list = d.get_phonetype();
            phone1_type_ComboBox.setItems(temp_list);
            phone2_type_ComboBox.setItems(temp_list);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
    }
    
    private c_ContactsComboBox d;
}
