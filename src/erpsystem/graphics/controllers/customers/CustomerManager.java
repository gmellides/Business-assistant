/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.database.customers.CST_Companies;
import erpsystem.database.customers.CST_Individual;
import erpsystem.database.customers.CST_Database;
import erpsystem.util.export.pdf.customers.CustomersTablePDF;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.FileManager;
import erpsystem.util.system.WindowsManager;
import java.awt.Desktop;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class CustomerManager implements Initializable {

    @FXML
    private Label lbl_Companies,lbl_Summary,lbl_Customers;
    @FXML
    private Button btn_newCustomer,btn_ExportPDF,btnClose,btn_backUp,btn_viewCustomer;
    @FXML
    private ImageView img_customerManager;
    
    private ResourceBundle default_strings;
    private WindowsManager window_check;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        window_check = new WindowsManager();
        init_window();
    }
    
        @FXML
        private void btnNewCustomer_Action(ActionEvent event) throws IOException {
            if(!window_check.NewCustomer_isOpen()){
                window_check.NewCustomer_toggle(true);                  
                try{
                    OpenWindow("customers/NewCustomer.fxml",
                               781,
                               450,
                               default_strings.getString("window_newCustomer"));
                }catch(IOException e){
                    e.printStackTrace();
                }    
                close_window();
            }  
        }
        @FXML
        private void btn_SearchView_Action(ActionEvent event) {
                try{
                    OpenWindow("customers/SearchView.fxml",
                               new Dimension().SearchView_window_width,
                               new Dimension().SearchView_window_height,
                               default_strings.getString("window_showCustomers"));
                }catch(IOException e){
                    e.printStackTrace();
                }
                close_window();
        } 
        @FXML
        private void btn_ExportCustomers_Action(ActionEvent event) {
            if(new CustomersTablePDF().save_file(default_strings,
                                                 new CST_Individual().select_customers(),
                                                 new CST_Companies().select_company())){
                Alert_dialog(Alert.AlertType.INFORMATION,
                        "dlg_customerTableSaved_title",
                        "dlg_customerTableSaved_header",
                        "dlg_customerTableSaved_message");
                try{
                    File pdf_file = new File(new FileManager().getDocuments_root());
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(pdf_file);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        @FXML
        private void btn_BackUp_Action(ActionEvent event) {
                try{
                    OpenWindow("customers/BackUp.fxml",
                               new Dimension().BackUp_window_width,
                               new Dimension().BackUp_window_height,
                               default_strings.getString("window_BackUp"));
                }catch(IOException e){
                    e.printStackTrace();
                }
                close_window();
        }
        @FXML
        private void btnClose_Action(ActionEvent event) {
            close_window();
        }
    
    private void init_window(){
        btn_backUp.setDisable(true);
        btn_viewCustomer.setDisable(true);
        btn_ExportPDF.setDisable(true);
        int[] number_of_records = new CST_Database().count_customers();
            lbl_Customers.setText(String.valueOf(number_of_records[0]));
            lbl_Companies.setText(String.valueOf(number_of_records[1]));
            lbl_Summary.setText(String.valueOf(number_of_records[2]));      
            if (number_of_records[2] > 0){
                btn_backUp.setDisable(false);
                btn_viewCustomer.setDisable(false);
                btn_ExportPDF.setDisable(false);
            }
        img_customerManager.setImage(new Image(new File("resources/images/customers/customer_manager.png").toURI().toString()));
    }
    private void close_window(){
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
                    if (!WindowPath.equals("customers/CustomerManager.fxml")){
                        try{
                            OpenWindow("customers/CustomerManager.fxml",
                                       new Dimension().Manager_window_width,
                                       new Dimension().Manager_window_height,
                                       default_strings.getString("window_customer_manager"));
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }   
                }
            });
         stage.setTitle(WindowName);
         stage.setScene(scene);
         stage.setResizable(false);
         stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
         stage.show();
    } 
    private void Alert_dialog(Alert.AlertType type,
                                  String Title,
                                  String Header,
                                  String Message){
        Alert succed_dialog = new Alert(type);
        succed_dialog.setTitle(default_strings.getString(Title));
        succed_dialog.setHeaderText(default_strings.getString(Header));
        succed_dialog.setContentText(default_strings.getString(Message));
        succed_dialog.showAndWait();   
    }
}
