/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.suppliers;

import erpsystem.database.suppliers.SPL_Database;
import erpsystem.util.export.csv.suppliers.SuppliersCSV;
import erpsystem.util.importcsv.ImportSuppliers;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BackUp implements Initializable {

    @FXML
    private Button btnClose,btn_SelectFile,btn_Import;
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
        if(new SuppliersCSV().export_file(default_strings, 
                new SPL_Database().select_suppliersBackUp())){
            Alert_dialog(Alert.AlertType.INFORMATION,
                            "dlg_CSV_title",
                            "dlg_contactsCSV_header",
                            "dlg_contactsCSV_message");
        }
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage win = (Stage) btnClose.getScene().getWindow();
        win.close();
    }

    @FXML
    private void btn_SelectFile_Action(ActionEvent event) {
         Stage this_stage = (Stage) btn_SelectFile.getScene().getWindow();
            FileChooser csv_chooser = new FileChooser();
            csv_chooser.setTitle(default_strings.getString("window_fileChooser_CSV"));
            csv_chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV", "*.csv")
            );
            File csv_file_chooser = csv_chooser.showOpenDialog(this_stage);
            btn_Import.setDisable(false);
            if (csv_file_chooser == null){
                btn_Import.setDisable(true);
            }else{
                txt_Path.setText(csv_file_chooser.getAbsolutePath().toString());
            }
    }

    @FXML
    private void btn_Import_Action(ActionEvent event) {
        if(new ImportSuppliers().import_csv(txt_Path.getText())){
            Alert_dialog(Alert.AlertType.INFORMATION,
                            "dlg_CSV_title",
                            "dlg_contactsCSV_header",
                            "dlg_contactsCSV_message");
        }
    }

    private void OpenManager() {
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/suppliers/SupplierManager.fxml").openStream());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(new Dimension().Manager_window_height);
            stage.setWidth(new Dimension().Manager_window_width);
               stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                   @Override
                   public void handle(WindowEvent we) {
                       new WindowsManager().toggle_window("suppliers/SupplierManager.fxml");
                       stage.close();
                   }
               });
         stage.setTitle(default_strings.getString("window_supplier_manager"));
         stage.setScene(scene);
         stage.setResizable(false);
         stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
         stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
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
