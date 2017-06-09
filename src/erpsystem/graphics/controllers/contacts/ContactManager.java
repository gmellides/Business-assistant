/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.Contacts_Operation;
import erpsystem.util.system.WindowsManager;
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
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ContactManager implements Initializable {

    @FXML
    private Button btnNewContact;
    @FXML
    private Pane contact_mgrPane;
    @FXML
    private Pane background_pane;
    @FXML
    private Button btnSearchView;
    @FXML
    private Label lbl_savedContacts;
    @FXML
    private Label lbl_lastInput;
    @FXML
    private Label Saved_contacts;

    
    private final WindowsManager window_check = new WindowsManager();  
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ShowContactInfos();
        set_style();
        Saved_contacts.setText(String.valueOf(new Contacts_Operation().count_contacts()));
    }    

    // ===== FXML Buttons Action =====
        @FXML
        private void btnNewContactsAction(ActionEvent event) throws IOException {
            if(!window_check.NewContact_isOpen()){
                window_check.NewContact_toogle(true);
                FXMLLoader fxml_loader = new FXMLLoader();
                    fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                    Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/contacts/NewContact.fxml").openStream());
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setHeight(420);
                    stage.setWidth(766);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent we) {
                            window_check.NewContact_toogle(false);
                            stage.close();
                        }
                    });
                    stage.setTitle("Νέα Επαφή");
                    stage.setScene(scene);
                    stage.setResizable(false);
                 // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                 // stage.getIcons().add(icon);
                    stage.show();
              }  
        }
        @FXML
        private void btnSearchView_Action(ActionEvent event) throws IOException{
            if (!window_check.ShowSearchContact_isOpen()){
                window_check.ShowSearchContact_toogle(true);
                FXMLLoader fxml_loader = new FXMLLoader();
                fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/contacts/SearchView.fxml").openStream());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(600);
                stage.setWidth(855);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        window_check.ShowSearchContact_toogle(false);
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
        }
        @FXML
        private void btn_BackUp_Action(ActionEvent event) throws IOException {
            if (!window_check.BackupContacts_isOpen()){
                window_check.BackupContacts_toogle(true);
            FXMLLoader f = new FXMLLoader();
                f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/contacts/BackUp.fxml").openStream());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(252);
                stage.setWidth(547);
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
        }
        @FXML
        private void btnExit_Action(ActionEvent event) {
            new WindowsManager().ContactManager_toogle(false);
            Stage this_window = (Stage) btnNewContact.getScene().getWindow();
            this_window.close();
        }
    // ===============================
    
    // ========== Methods ============
        public void set_style(){
            contact_mgrPane.setStyle("-fx-background-image: url('file://../resources/images/contacts/contact_manager.png\');");
        }
        public void ShowContactInfos(){
            // selevt count(*) from contacts
        }
        public void NewContactPrepare(){
            // this will be used for new contacts 
        }
    // ===============================
}
