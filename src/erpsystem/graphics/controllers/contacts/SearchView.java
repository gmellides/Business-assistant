/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.Contacts_Operation;
import erpsystem.util.system.WindowsManager;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SearchView implements Initializable {

    @FXML
    private Pane background_panel;
    @FXML
    private Pane image_panel;
    @FXML
    private Button btnClose;
    @FXML
    private TableView<Map> contacts_table;
    @FXML
    private TableColumn col_firstname;
    @FXML
    private TableColumn col_lastname;
    @FXML
    private TableColumn col_sex;
    @FXML
    private TableColumn col_address;
    @FXML
    private TableColumn col_zipcode;
    @FXML
    private TableColumn col_country;
    @FXML
    private TableColumn col_greek_state;
    @FXML
    private TableColumn col_city;
    @FXML
    private TableColumn col_mail;
    @FXML
    private TableColumn col_phone1;
    @FXML
    private TableColumn col_phone2;
    @FXML
    private TextField txt_Instant_Search;

    public static String search_value;
    private final WindowsManager window_check = new WindowsManager();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_style();
        set_data();
    }  
    
    @FXML
    private void btnClose_Action(ActionEvent event) {
        window_check.ShowSearchContact_toogle(false);
        Stage this_window = (Stage)  btnClose.getScene().getWindow();
        this_window.close();
    }
    
    
    @FXML
    private void Instant_Search(KeyEvent event) {
        search_value = event.getText();
       System.out.print(search_value);
    }

    private void set_data(){
        contacts_table.setItems(new Contacts_Operation().select_table_data());
        TableColumn[] columns = {col_firstname,col_lastname,col_sex,
                                 col_address,col_zipcode,col_country,
                                 col_greek_state,col_city,col_mail,
                                 col_phone1,col_phone2};
        String[] id = {"firstname","lastname","sex",
                       "address","zipcode","country",
                       "greek_state","city","mail",
                       "phone1","phone2"};
        int index = 0;
        for (TableColumn column : columns){
            column.setCellValueFactory(new MapValueFactory(id[index]));
            index++;
        }
        
    }
    public void set_style(){
        image_panel.setStyle("-fx-background-image: url('file://../resources/images/contacts/contact_manager.png\');");
    }
}
