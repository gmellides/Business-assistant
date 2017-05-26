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
import erpsystem.util.datetime.DateTimeProvider;
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
    
        // ======= Buttons ========
            @FXML
            private void btn_Employee_Action(ActionEvent event) {
            }

            @FXML
            private void btn_Customers_Action(ActionEvent event) throws IOException {
                FXMLLoader f = new FXMLLoader();
                f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/customers/CustomerManager.fxml").openStream());
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setHeight(420);
                stage.setWidth(600);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent we) {
                        stage.close();
                    }
                });
                stage.setTitle("Διαχείρηση Πελατών");
                stage.setScene(scene);
                stage.setResizable(false);
                    // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                     // stage.getIcons().add(icon);
                stage.show();
            }

            @FXML
            private void btn_Suppliers_Action(ActionEvent event) {
            }

            @FXML
            private void btn_Finance_Action(ActionEvent event) {
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
                        stage.close();
                    }
                });
                stage.setTitle("Επαφές");
                stage.setScene(scene);
                stage.setResizable(false);
                    // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                    // stage.getIcons().add(icon);
                stage.show();
            }
        // ========================

        // ====== Menu Items Action ======
            // File > View Business data
            @FXML
            private void mnu_view_BusinessData(ActionEvent event) throws IOException {
                FXMLLoader f = new FXMLLoader();
                    f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                    Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/menubar/View_BusinessData.fxml").openStream());
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setHeight(510);
                    stage.setWidth(747);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent we) {
                            stage.close();
                        }
                    });
                    stage.setTitle("Επεξεργασία Πληροφωριών επιχείρησης");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                    // stage.getIcons().add(icon);
                    stage.show();
            }
            // File > View Admin Data
            @FXML
            private void mnu_ViewAdminData_Action(ActionEvent event) throws IOException {
                FXMLLoader f = new FXMLLoader();
                    f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                    Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/menubar/View_BusinessAdmin.fxml").openStream());
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setHeight(515);
                    stage.setWidth(694);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent we) {
                            stage.close();
                        }
                    });
                    stage.setTitle("Επεξεργασία Πληροφωριών επιχείρησης");
                    stage.setScene(scene);
                    stage.setResizable(false);
                 // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                 // stage.getIcons().add(icon);
                    stage.show();
            }
            // File > Exit
            @FXML
            private void mnuExit_Action(ActionEvent event) {
                System.exit(0);
            }
            // Edit > Edit Business Data
             @FXML
            private void mnu_edit_BusinessInfo_Action(ActionEvent event) throws IOException {
                        FXMLLoader f = new FXMLLoader();
                        f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                        Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/menubar/Edit_BusinessData.fxml").openStream());
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setHeight(515);
                        stage.setWidth(694);
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent we) {
                                stage.close();
                            }
                        });
                        stage.setTitle("Επεξεργασία Πληροφωριών επιχείρησης");
                        stage.setScene(scene);
                        stage.setResizable(false);
                            // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                            // stage.getIcons().add(icon);
                        stage.show();
            }
            // Edit > Edit Business Admin
            @FXML
            private void mnu_edtBusinessAdmin_Action(ActionEvent event) throws IOException {
                 FXMLLoader f = new FXMLLoader();
                        f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                        Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/menubar/Edit_BusinessAdmin.fxml").openStream());
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setHeight(430);
                        stage.setWidth(800);
                        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                            @Override
                            public void handle(WindowEvent we) {
                                stage.close();
                            }
                        });
                        stage.setTitle("Επεξεργασία Πληροφωριών επιχείρησης");
                        stage.setScene(scene);
                        stage.setResizable(false);
                            // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                            // stage.getIcons().add(icon);
                        stage.show();
            }
        // ===============================     
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
                            lcn_lblDateTime.setText(new DateTimeProvider().GetDateTime());
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
