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
import erpsystem.util.system.WindowsManager;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Launcher implements Initializable {
   
    // ====== FXML Components =======
        @FXML
        private Label lcn_lblDateTime;
    // ==============================
        
    private Timer DateTimeUpdater; 
    private ResourceBundle default_strings;     
    private final WindowsManager window_check = new WindowsManager();    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
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
                    window_check.CustomerManager_toggle(true);
                    try{
                        OpenWindow("customers/CustomerManager.fxml",600,390,default_strings.getString("customer_manager"));
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }else{
                    
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
                    try{
                        OpenWindow("storage/StorageManager.fxml",
                                   600,
                                   420,
                                   default_strings.getString("customer_manager"));
                    }catch(IOException e){
                        e.printStackTrace();
                    }
             }
            
            @FXML
            private void btnContactManagerAction(ActionEvent event){
                if (!window_check.ContactManager_isOpen()){
                    window_check.ContactManager_toggle(true);
                    try{
                        OpenWindow("contacts/ContactManager.fxml",
                                   600,
                                   420,
                                   default_strings.getString("customer_manager"));
                    }catch(IOException e){
                        e.printStackTrace();
                    }
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
                    window_check.ViewBusiness_toggle(true);
                    try{
                        OpenWindow("menubar/View_BusinessData.fxml",
                                    747,
                                    510,
                                    default_strings.getString("view_business_data"));
                    }catch(IOException e){
                        e.printStackTrace();
                    } 
                } 
            }
            // File > View Admin Data
            @FXML
            private void mnu_ViewAdminData_Action(ActionEvent event) throws IOException {
                if (!window_check.ViewAdmin_isOpen()){
                    window_check.ViewAdmin_toggle(true);
                    try{
                        OpenWindow("menubar/View_BusinessAdmin.fxml",
                                   694,
                                   470,
                                   default_strings.getString("view_admin_data"));
                    }catch(IOException e){
                        e.printStackTrace();
                    } 
                    
                }    
            }
            // Edit > Edit Business Data
            @FXML
            private void mnu_edit_BusinessInfo_Action(ActionEvent event) throws IOException {
                try{
                   OpenWindow("menubar/Edit_BusinessData.fxml",
                              694,
                              515,
                              default_strings.getString("edit_business_data"));
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            // Edit > Edit Business Admin
            @FXML
            private void mnu_edtBusinessAdmin_Action(ActionEvent event) throws IOException {
                try{
                   OpenWindow("menubar/Edit_BusinessAdmin.fxml",
                              800,
                              430,
                              default_strings.getString("edit_admin_data"));
                }catch(IOException e){
                    e.printStackTrace();
               }
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
                    stage.setTitle(WindowName);
                    stage.setScene(scene);
                    stage.setResizable(false);
                 // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
                 // stage.getIcons().add(icon);
                    stage.show();
                    
        }
    // ==== END OF MY METHODS ===
}
