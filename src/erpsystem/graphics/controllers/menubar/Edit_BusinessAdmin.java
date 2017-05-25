/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Edit_BusinessAdmin implements Initializable {

    @FXML
    private Pane background_pane;
    @FXML
    private ImageView icon_imageview;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_background_and_icon();
    }    

    public void set_background_and_icon(){
        Image icon = new Image(new File("resources/images/menubar/edit_AdminData.png").toURI().toString());
        icon_imageview.setImage(icon);
        background_pane.setStyle("-fx-background-color: #FFFFFF;");
    }
}
