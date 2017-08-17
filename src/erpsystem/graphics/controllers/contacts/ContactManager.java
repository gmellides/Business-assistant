/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.ContactsDatabase;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.WindowsManager;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ContactManager implements Initializable {

    @FXML
    private Button btnNewContact;
    @FXML
    private Label Saved_contacts;
    @FXML
    private ImageView icn_contactManager;
    
    private ResourceBundle default_strings;
    private final WindowsManager window_check = new WindowsManager();  
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        init_window();
    }

    // ===== FXML Buttons Action =====
        @FXML
        private void btnNewContactsAction(ActionEvent event) throws IOException {
            if(!window_check.NewContact_isOpen()){
                window_check.NewContact_toggle(true);     
                try{
                    OpenWindow("contacts/NewContact.fxml",
                               766,
                               420,
                               default_strings.getString("window_newContact"));
                }catch(IOException e){
                    e.printStackTrace();
                }
              }  
            close_window();
        }
        @FXML
        private void btnSearchView_Action(ActionEvent event) throws IOException{
            if (!window_check.ShowSearchContact_isOpen()){
                window_check.ShowSearchContact_toggle(true);
                try{
                    OpenWindow("contacts/SearchView.fxml",
                               new Dimension().SearchView_window_width,
                               new Dimension().SearchView_window_height,
                               default_strings.getString("window_showContacts"));
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            close_window();
        }
        @FXML
        private void btn_BackUp_Action(ActionEvent event) throws IOException {
            if (!window_check.BackupContacts_isOpen()){
                window_check.BackupContacts_toggle(true);    
                try{
                    OpenWindow("contacts/BackUp.fxml",
                               new Dimension().BackUp_window_width,
                               new Dimension().BackUp_window_height,
                               default_strings.getString("window_BackUp"));
                }catch(IOException e){
                    e.printStackTrace();
                }         
            }
            close_window();
        }
        @FXML
        private void btnExit_Action(ActionEvent event) {
            close_window();
        }
    // ===============================
    
    // ========== Methods ============
        public void init_window(){
           icn_contactManager.setImage(new Image(new File("resources/images/contacts/contact_manager.png").toURI().toString()));
           Saved_contacts.setText(String.valueOf(new ContactsDatabase().count_contacts()));
        }       
        
        private void OpenWindow(String WindowPath,
                                int Width,
                                int Height,
                                String WindowName) throws IOException{
            FXMLLoader f = new FXMLLoader();
                f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/"+WindowPath).openStream());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(Height);
                stage.setWidth(Width);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        window_check.toggle_window(WindowPath);
                        stage.close();      
                    }
                });
                stage.setTitle(WindowName);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
                stage.show(); 
        }
        private void close_window(){
            new WindowsManager().ContactManager_toggle(false);
            Stage this_window = (Stage) btnNewContact.getScene().getWindow();
            this_window.close();
        }
    // ===============================
}
