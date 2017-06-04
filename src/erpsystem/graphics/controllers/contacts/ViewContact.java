/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ViewContact implements Initializable {

    @FXML
    private Button btn_Close;
    @FXML
    private ImageView contact_icon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (init_window()){
            set_data();
        }
    }    

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage this_window = (Stage) btn_Close.getScene().getWindow();
        this_window.close();
    }
    
    private boolean init_window(){
        contact_icon.setImage(new Image(new File("").toURI().toString()));
        return true;
    }
    private void set_data(){
        Label[] labels = {};
        String[] default_strings = {};
        int index = 0;
        for (Label item:labels){
            index++;
        }
    }
    
}
