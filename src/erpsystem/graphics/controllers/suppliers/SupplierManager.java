/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.suppliers;

import erpsystem.database.suppliers.SupplierCompanies;
import erpsystem.database.suppliers.SupplierIndividual;
import erpsystem.database.suppliers.SuppliersDatabase;
import erpsystem.util.export.pdf.suppliers.SuppliersTablePDF;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SupplierManager implements Initializable {

    @FXML
    private Label lbl_suppl_people,lbl_suppl_comp,lbl_suppl_sum;
 
    private ResourceBundle default_strings;
    @FXML
    private Button btn_ExportPDF;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       init_window(rb);
    }    

        @FXML
        private void btn_NewSupplier_Action(ActionEvent event) {
            try{
                OpenWindow("suppliers/NewSupplier.fxml",
                           750,
                           448,
                           default_strings.getString("window_newSupplier"));
            }catch(IOException e){
                e.printStackTrace();
            }
            close_window();
        }

        @FXML
        private void btn_ShowRecords_Action(ActionEvent event) {
            try{
                OpenWindow("suppliers/SearchView.fxml",
                           new Dimension().SearchView_window_width,
                           new Dimension().SearchView_window_height,
                           default_strings.getString("window_showSuppliers"));
            }catch(IOException e){
                e.printStackTrace();
            }
            close_window();
        }

        @FXML
        private void btn_BackUp_Action(ActionEvent event) {
            try{
                OpenWindow("suppliers/BackUp.fxml",
                           new Dimension().BackUp_window_width,
                           new Dimension().BackUp_window_height,
                           default_strings.getString("window_BackUp"));
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
        new WindowsManager().SupplierManager_toggle(false);
        Stage window = (Stage) lbl_suppl_sum.getScene().getWindow();
        window.close();  
    }
    private void init_window(ResourceBundle rb){
       // Strings File
        default_strings = rb;
       // Set Data to labels
        int[] data = new SuppliersDatabase().count_suppliers();
        lbl_suppl_people.setText(String.valueOf(data[0]));
        lbl_suppl_comp.setText(String.valueOf(data[1]));
        lbl_suppl_sum.setText(String.valueOf(data[2]));
       //Set Icon on Imageview
        // CODE TODO 
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
                    if(!WindowPath.equals("suppliers/SupplierManager.fxml")){
                        try{
                        OpenWindow("suppliers/SupplierManager.fxml",
                                   new Dimension().Manager_window_width,
                                   new Dimension().Manager_window_height,
                                   default_strings.getString("window_supplier_manager"));
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

    @FXML
    private void btn_ExportPDF_Action(ActionEvent event) {
        if (new SuppliersTablePDF().save_file(default_strings, 
                                              new SupplierIndividual().select_suppliers(),
                                              new SupplierCompanies().select_suppliers())){
            
        }
    }
}
