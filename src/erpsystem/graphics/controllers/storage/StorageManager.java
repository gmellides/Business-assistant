/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.storage;

import erpsystem.database.customers.CST_Companies;
import erpsystem.database.customers.CST_Individual;
import erpsystem.database.storage.StorageDatabase;
import erpsystem.database.storage.StorageView;
import erpsystem.util.export.pdf.customers.CustomersTablePDF;
import erpsystem.util.export.pdf.storage.StorageTablePDF;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.FileManager;
import erpsystem.util.system.WindowsManager;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StorageManager implements Initializable {

    @FXML
    private PieChart StoragePie;
    @FXML
    private ImageView img_storageManager;
    @FXML
    private Label lbl_noData;
    
    private ResourceBundle default_strings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       default_strings = rb;
       init_window();
    }
        
        @FXML
        private void btn_NewProduct_Action(ActionEvent event) {
            try{
                OpenWindow("purchases/NewPurchase.fxml",
                           new Dimension().NewPurchase_window_width,
                           new Dimension().NewPurchase_window_height,
                           default_strings.getString("window_BackUp"));
            }catch(IOException e){
                e.printStackTrace();
            }
            close_window();
        }
        @FXML
        private void btn_BackUp_Action(ActionEvent event) {
            try{
                OpenWindow("storage/BackUp.fxml",
                           new Dimension().BackUp_window_width,
                           new Dimension().BackUp_window_height,
                           default_strings.getString("window_BackUp"));
            }catch(IOException e){
                e.printStackTrace();
            }
            close_window();
        }
        @FXML
        private void btn_ExportPDF_Action(ActionEvent event) {
            if(new StorageTablePDF().save_file(default_strings,
                                                new StorageView().select_products())){
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
        private void btn_ShowStorage_Action(ActionEvent event) {
            try{
                OpenWindow("storage/ViewStorage.fxml",
                           new Dimension().ViewStorage_window_width,
                           new Dimension().ViewStorage_window_height,
                           default_strings.getString("btn_viewStorage"));
            }catch(IOException e){
                e.printStackTrace();
            }
            close_window();
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
    
    private void close_window(){
        Stage win = (Stage) StoragePie.getScene().getWindow();
        win.close();
    }
    private void init_window(){
        img_storageManager.setImage(new Image(new File("resources/images/storage/storage_manager.png").toURI().toString()));
        for (Map prod : new StorageDatabase().select_storage_brief()){
            PieChart.Data data = new PieChart.Data((String) prod.get("prd_name"),Integer.parseInt((String) prod.get("prd_quantity")));
            StoragePie.getData().add(data);
        } 
        lbl_noData.setVisible(false);
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
                    new WindowsManager().toggle_window(WindowPath);
                    stage.close();
                    if (!WindowPath.equals("storage/StorageManager.fxml")){
                        try{
                            OpenWindow("storage/StorageManager.fxml",
                                       new Dimension().Manager_window_width,
                                       new Dimension().Manager_window_height,
                                       default_strings.getString("window_storage_manager"));
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
