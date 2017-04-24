/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import java.net.URL;
import java.util.ResourceBundle;
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
    private ComboBox<?> phone1_type_ComboBox;
    @FXML
    private ComboBox<?> phone2_type_ComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        newcontact_img.setStyle("-fx-background-image: url('file://../resources/images/contacts/new_contact.png\');");
        background_panel.setStyle("-fx-background-color: #FFFFFF;");
        
    }
    
}
