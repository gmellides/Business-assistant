/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class About implements Initializable {

    @FXML
    private ImageView icon_img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       icon_img.setImage(new Image(new File("resources/logo/new_icon.png").toURI().toString()));
    }    

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage win = (Stage) icon_img.getScene().getWindow();
        win.close();
    }

    @FXML
    private void lbl_Mail_Action(ActionEvent event) throws URISyntaxException, IOException {if (Desktop.isDesktopSupported()) {
    Desktop desktop = Desktop.getDesktop();
    if (desktop.isSupported(Desktop.Action.MAIL)) {
        URI mailto = new URI("mailto:gabriel_mellides@outlook.com?subject=Business%20Assistant%20Επικοινωνία");
        desktop.mail(mailto);
    }
}
        
    }
    
}
