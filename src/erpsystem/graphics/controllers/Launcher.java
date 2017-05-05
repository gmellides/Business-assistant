/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;
import javafx.fxml.Initializable;
import erpsystem.util.datetime.c_DateTimeProvider;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Launcher implements Initializable {
   
    // ====== FXML Components =======
        @FXML
        private Label lcn_lblDateTime;
        @FXML
        private Button btnExit;
        @FXML
        private Button btnContactManager;
    // === END OF FXML Components ===
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Start_DateTimeInfo();
    }

    // ====== FXML Components Action ======
        @FXML
        private void mnu_edit_BusinessInfo_Action(ActionEvent event) throws IOException {
                FXMLLoader f = new FXMLLoader();
                f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/menubar/Edit_BusinessData.fxml").openStream());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(495);
                stage.setWidth(694);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        stage.hide();
                    }
                });
                stage.setTitle("Επεξεργασία Πληροφωριών επιχείρησης");
                stage.setScene(scene);
                stage.setResizable(false);
                    // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                    // stage.getIcons().add(icon);
                stage.show();
        }
        @FXML
        private void btnExitAction(ActionEvent event) {
            System.exit(0);
        }
        @FXML
        private void btnContactManagerAction(ActionEvent event) throws IOException {
            FXMLLoader f = new FXMLLoader();
            f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/contacts/ContactManager.fxml").openStream());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(420);
            stage.setWidth(600);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
                    stage.hide();
                }
            });
            stage.setTitle("Επαφές");
            stage.setScene(scene);
            stage.setResizable(false);
                // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                // stage.getIcons().add(icon);
            stage.show();
        }
        @FXML
        private void mnuExit_Action(ActionEvent event) {
            System.exit(0);
        }
    // ==== ENF OF COMPONENTS ACTION ======
    
    
    // ====== My Methods ========
        public void Start_DateTimeInfo(){
            DateTimeUpdater = new Timer();
            DateTimeUpdater.schedule(new TimerTask(){
                @Override
                public void run(){
                    Platform.runLater(new Runnable(){
                        @Override
                        public void run(){
                            lcn_lblDateTime.setText(new c_DateTimeProvider().GetDateTime());
                        }
                    });
                }
            },0,1000);
        }
        public void SetStyle(){
            
        }
    // ==== END OF MY METHODS ===
    
    private Timer DateTimeUpdater; 
}
