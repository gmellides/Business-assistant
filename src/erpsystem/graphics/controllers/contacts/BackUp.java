/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.ContactsDatabase;
import erpsystem.util.export.csv.contacts.ContactsExport;
import erpsystem.util.system.WindowsManager;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class BackUp implements Initializable {

    @FXML
    private Button btnClose,btn_SelectFile,btn_Import;
    @FXML
    private TextField txt_Path;
 
    private ResourceBundle default_strings;
    private ContactsDatabase contacts_database;
    private ContactsExport export_csv;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
    }    
    
    // ===== FXML Buttons Action =====
        @FXML
        private void btn_ExportCSV_Action(ActionEvent event) {
            contacts_database = new ContactsDatabase();
            export_csv = new ContactsExport();
            if (export_csv.export_file(default_strings, contacts_database.select_contacts())){
                Alert_dialog(AlertType.INFORMATION,
                            "dlg_CSV_title",
                            "dlg_contactsCSV_header",
                            "dlg_contactsCSV_message");
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
                btn_Import.setDisable(false);
                if (logo_file_chooser == null){
                    btn_Import.setDisable(true);
                }else{
                    txt_Path.setText(logo_file_chooser.getAbsolutePath().toString());
                }
        }
        @FXML
        private void btn_Import_Action(ActionEvent event) {
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            new WindowsManager().BackupContacts_toggle(false);
            Stage window = (Stage) btnClose.getScene().getWindow();
            window.close();
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
    // ===============================
}
