/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.storage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class BackUp implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btn_SelectFile;
    @FXML
    private TextField txt_Path;
    @FXML
    private Button btn_importCSV;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

        @FXML
        private void btn_ExportCSV_Action(ActionEvent event) {
        }

        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }

        @FXML
        private void btn_SelectFile_Action(ActionEvent event) {
        }

        @FXML
        private void btn_importCSV_Action(ActionEvent event) {
            close_window();
        }
    
    private void close_window(){
        Stage window = (Stage) btnClose.getScene().getWindow();
        window.close();
    }
}
