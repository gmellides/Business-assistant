/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

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
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CustomerManager implements Initializable {

    @FXML
    private Button btnClose;

    private ResourceBundle default_strings;
    private WindowsManager window_check;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        window_check = new WindowsManager();
    }
    
    @FXML
    private void btnNewCustomer_Action(ActionEvent event) throws IOException {
        if(!window_check.NewCustomer_isOpen()){
            window_check.NewCustomer_toggle(true);                  
            try{
                OpenWindow("customers/NewCustomer.fxml",
                           766,
                           420,
                           default_strings.getString("lbl_windowtitle"));
            }catch(IOException e){
                e.printStackTrace();
            }    
        }  
    }
    
    @FXML
    private void btnClose_Action(ActionEvent event) {
        new WindowsManager().CustomerManager_toggle(false);
        Stage window = (Stage) btnClose.getScene().getWindow();
        window.close();
    }
    private void OpenWindow(String WindowPath,
                            int Width,
                            int Height,
                            String WindowName) throws IOException{
         FXMLLoader fxml_loader = new FXMLLoader();
         fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
         Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/"+WindowPath).openStream());
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
         stage.setTitle(default_strings.getString("lbl_windowtitle"));
         stage.setScene(scene);
         stage.setResizable(false);
                 // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                 // stage.getIcons().add(icon);
         stage.show();
    }
    
}
