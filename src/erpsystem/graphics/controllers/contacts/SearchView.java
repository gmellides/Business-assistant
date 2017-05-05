/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class SearchView implements Initializable {

    @FXML
    private Pane background_panel;
    @FXML
    private Pane image_panel;
    @FXML
    private Button btnClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_style();
        
    }  
    
    @FXML
    private void btnClose_Action(ActionEvent event) {
        
    }
    
    public void set_style(){
        image_panel.setStyle("-fx-background-image: url('file://../resources/images/contacts/contact_manager.png\');");
        background_panel.setStyle("-fx-background-color: #FFFFFF;");
    }
}