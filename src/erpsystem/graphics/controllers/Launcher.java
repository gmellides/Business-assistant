/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers;

import erpsystem.database.contacts.Contacts_Operation;
import erpsystem.database.customers.Customers_Operation;
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
import erpsystem.util.system.WindowsManager;
import erpsystem.util.xml.write.AppData;
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
    
    private AppData temp_data;
    private Timer DateTimeUpdater; 
    private ResourceBundle language_strings;     
    private final WindowsManager window_check = new WindowsManager();    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        language_strings = rb;
        Start_DateTimeInfo();
    }

    // ====== FXML Components Action ======
    
        // ======= Buttons ========
            @FXML
            private void btnSales_Action(ActionEvent event) {
            }
            @FXML
            private void btnPurchases_Action(ActionEvent event) {
            }
            @FXML
            private void btn_Employee_Action(ActionEvent event) {
            }
            @FXML
            private void btn_Customers_Action(ActionEvent event) throws IOException {
                if (!window_check.CustomerManager_isOpen()){
                    window_check.CustomerManager_toogle(true);
                    FXMLLoader fxml_loader = new FXMLLoader();
                    fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                    Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/customers/CustomerManager.fxml").openStream());
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setHeight(390);
                    stage.setWidth(600);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent we) {
                            window_check.CustomerManager_toogle(false);
                            stage.close();
                        }
                    });
                    stage.setTitle(language_strings.getString("customer_manager"));
                    stage.setScene(scene);
                    stage.setResizable(false);
                 // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                 // stage.getIcons().add(icon);
                    stage.show();
                }
            }
            @FXML
            private void btn_Suppliers_Action(ActionEvent event) {
            }
            @FXML
            private void btn_Finance_Action(ActionEvent event) {
            }
            @FXML
            private void btnStorage_Action(ActionEvent event) throws IOException {
                FXMLLoader f = new FXMLLoader();
                f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/storage/StorageManager.fxml").openStream());
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
                stage.setTitle("contact_manager");
                stage.setScene(scene);
                stage.setResizable(false);
                    // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                    // stage.getIcons().add(icon);
                stage.show();
             }
            @FXML
            private void btnContactManagerAction(ActionEvent event) throws IOException {
                if (!window_check.ContactManager_isOpen()){
                    window_check.ContactManager_toogle(true);
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
                            window_check.ContactManager_toogle(false);
                            stage.close();
                        }
                    });
                    stage.setTitle("contact_manager");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                    // stage.getIcons().add(icon);
                    stage.show();
                } 
            }
            @FXML
            private void btnExitAction(ActionEvent event) {
                System.exit(0);
            }
        // ========================

        // ====== Menu Items Action ======
            // File > View Business data
            @FXML
            private void mnu_view_BusinessData(ActionEvent event) throws IOException {
                if(!window_check.ViewBusiness_isOpen()){
                    window_check.ViewBusiness_toogle(true);
                    FXMLLoader fxml_loader = new FXMLLoader();
                    fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                    Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/menubar/View_BusinessData.fxml").openStream());
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setHeight(510);
                    stage.setWidth(747);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent we) {
                            window_check.ViewBusiness_toogle(false);
                            stage.close();
                        }
                    });
                    stage.setTitle(language_strings.getString("view_business_data"));
                    stage.setScene(scene);
                    stage.setResizable(false);
                    // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                    // stage.getIcons().add(icon);
                    stage.show();
                } 
            }
            // File > View Admin Data
            @FXML
            private void mnu_ViewAdminData_Action(ActionEvent event) throws IOException {
                if (!window_check.ViewAdmin_isOpen()){
                    window_check.ViewAdmin_toogle(true);
                    FXMLLoader f = new FXMLLoader();
                    f.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
                    Parent root = f.load(getClass().getResource("/erpsystem/graphics/windows/menubar/View_BusinessAdmin.fxml").openStream());
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.setHeight(470);
                    stage.setWidth(694);
                    stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                        @Override
                        public void handle(WindowEvent we) {
                            window_check.ViewAdmin_toogle(false);
                            stage.close();
                        }
                    });
                    stage.setTitle(language_strings.getString("view_admin_data"));
                    stage.setScene(scene);
                    stage.setResizable(false);
                    //Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                    //stage.getIcons().add(icon);
                    stage.show();
                }    
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
                        stage.setTitle(language_strings.getString("edit_business_data"));
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
                        stage.setTitle(language_strings.getString("edit_admin_data"));
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
        // ===============================     
    // ==== ENF OF COMPONENTS ACTION ======  
            
    // ====== My Methods ========
        private void Start_DateTimeInfo(){
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
        private boolean create_app_data(){
            boolean flag = false;
                String[] values = {String.valueOf(new Contacts_Operation().count_contacts()),
                                   String.valueOf(new Customers_Operation().count_customers())};
            return flag;
        }
    // ==== END OF MY METHODS ===
}
