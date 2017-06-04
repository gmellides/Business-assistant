/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.Contacts_Operation;
import erpsystem.util.export.csv.contacts.ContactsExport;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class BackUp implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private Button btn_SelectFile;
    @FXML
    private TextField txt_Path;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
    }    

    @FXML
    private void btn_ExportCSV_Action(ActionEvent event) {
        contacts_database = new Contacts_Operation();
        export_csv = new ContactsExport();
        export_csv.export_file(default_strings, contacts_database.select_data_csv());
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage window = (Stage) btnClose.getScene().getWindow();
        window.close();
    }
    
    private ResourceBundle default_strings;
    private Contacts_Operation contacts_database;
    private ContactsExport export_csv;

    @FXML
    private void btn_SelectFile_Action(ActionEvent event) {
    }
}
