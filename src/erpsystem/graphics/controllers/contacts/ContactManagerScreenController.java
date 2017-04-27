/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ContactManagerScreenController implements Initializable {

    @FXML
    private Button btnNewContact;
    @FXML
    private Pane contact_mgrPane;
    @FXML
    private Pane background_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowContactInfos();
        contact_mgrPane.setStyle("-fx-background-image: url('file://../resources/images/contacts/contact_manager.png\');");
        background_pane.setStyle("-fx-background-color: #FFFFFF;");
    }    

    @FXML
    private void btnNewContactsAction(ActionEvent event) throws IOException {
        FXMLLoader f = new FXMLLoader();
            f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/contacts/NewContactScreen.fxml").openStream());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(420);
            stage.setWidth(766);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                    stage.hide();
                }
            });
            stage.setTitle("Νέα Επαφή");
            stage.setScene(scene);
            stage.setResizable(false);
                // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                // stage.getIcons().add(icon);
            stage.show();
    }
    
    public void ShowContactInfos(){
        // selevt count(*) from contacts
    }
    public void NewContactPrepare(){
        // this will be used for new contacts 
    }
}
