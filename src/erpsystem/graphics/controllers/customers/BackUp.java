/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.database.customers.CustomersDatabase;
import erpsystem.util.export.csv.customers.CustomersCSV;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class BackUp implements Initializable {

    @FXML
    private Button btnClose,btn_SelectFile,btn_importCSV;
    @FXML
    private TextField txt_Path;
    @FXML
    private RadioButton rdb_Indeviduals,rdb_Companies;
    @FXML
    private ToggleGroup backup_option;
    
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
        if (backup_option.getSelectedToggle() !=null){
            if(backup_option.getSelectedToggle().equals(rdb_Companies)){
                if(new CustomersCSV()
                        .export_csv(backup_option.getSelectedToggle().equals(rdb_Companies),
                        default_strings,
                        new CustomersDatabase().select_company())){
                        Alert_dialog(AlertType.INFORMATION,
                                      "dlg_CSV_title",
                                      "dlg_customersCSV_header",
                                      "dlg_customersCompaniesCSV_message");  
                }
            }else{
                    if(new CustomersCSV()
                            .export_csv(backup_option.getSelectedToggle().equals(rdb_Companies),
                            default_strings,
                            new CustomersDatabase().select_person())){
                        Alert_dialog(AlertType.INFORMATION,
                                      "dlg_CSV_title",
                                      "dlg_customersCSV_header",
                                      "dlg_customersIndevidualCSV_message");  
                    }
            }
        }else{
           Alert_dialog(AlertType.ERROR,
                        "dlg_selectionErrorCSV_title",
                        "dlg_selectionErrorCSV_header",
                        "dlg_selectionErrorCSV_message"); 
        }
    }
    
    @FXML
    private void btn_SelectFile_Action(ActionEvent event) {
        Stage this_stage = (Stage) btn_SelectFile.getScene().getWindow();
                FileChooser csv_chooser = new FileChooser();
                csv_chooser.setTitle("jnfiwe");
                csv_chooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Όλα τα αρχεία", "*.*"),
                    new FileChooser.ExtensionFilter("CSV", "*.csv")
                );
                File logo_file_chooser = csv_chooser.showOpenDialog(this_stage);
                btn_importCSV.setDisable(false);
                if (logo_file_chooser == null){
                    btn_importCSV.setDisable(true);
                }else{
                    txt_Path.setText(logo_file_chooser.getAbsolutePath().toString());
                }
    }
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage window = (Stage) btnClose.getScene().getWindow();
        window.close();
        OpenManager();
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
         stage.setTitle(default_strings.getString("window_customer_manager"));
         stage.setScene(scene);
         stage.setResizable(false);
         stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
         stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void Alert_dialog(AlertType type,
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
