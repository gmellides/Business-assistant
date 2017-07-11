/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.util.system.Dimension;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class BackUp implements Initializable {

    @FXML
    private Button btnClose,btn_SelectFile;
    @FXML
    private TextField txt_Path;

    
    private ResourceBundle default_strings;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
    }    

    @FXML
    private void btn_ExportCSV_Action(ActionEvent event) {
        
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage window = (Stage) btnClose.getScene().getWindow();
        window.close();
        OpenManager();
    }

    @FXML
    private void btn_SelectFile_Action(ActionEvent event) {
    }
    private void OpenManager() {
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/customers/CustomerManager.fxml").openStream());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(new Dimension().Manager_window_height);
            stage.setWidth(new Dimension().Manager_window_width);
               stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                   @Override
                   public void handle(WindowEvent we) {
                       new WindowsManager().toggle_window("customers/CustomerManager.fxml");
                       stage.close();
                   }
               });
         stage.setTitle(default_strings.getString("customer_manager"));
         stage.setScene(scene);
         stage.setResizable(false);
         // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
         // stage.getIcons().add(icon);
         stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
