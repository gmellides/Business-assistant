/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View_BusinessAdmin implements Initializable {

    @FXML
    private Button btn_ExportPDF;
    @FXML
    private Button btn_ExportCard;
    @FXML
    private Button btn_Close;
    @FXML
    private ImageView icon_imageview;
    @FXML
    private Pane background_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_background_and_icon();
        data = rb;
    }    

    @FXML
    private void btnExportPDF_Action(ActionEvent event) {
        
    }

    @FXML
    private void btnExportCard_Action(ActionEvent event) {
    }

    @FXML
    private void btnClose_Action(ActionEvent event) {
        Stage this_stage = (Stage) btn_Close.getScene().getWindow();
        this_stage.close();
    }
    
    public void set_background_and_icon(){
        Image icon = new Image(new File("resources/images/menubar/view_adminData.png").toURI().toString());
        icon_imageview.setImage(icon);
        background_pane.setStyle("-fx-background-color: #FFFFFF;"); 
    }
    
    private ResourceBundle data;
}
