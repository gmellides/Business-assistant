/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import erpsystem.database.contacts.CNT_Database;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.WindowsManager;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SearchView implements Initializable {

    @FXML
    private Button btnClose;
    @FXML
    private TableView<Map> contacts_table;
    @FXML
    private TableColumn Col_id,col_firstname,col_lastname,col_sex,
                        col_address,col_zipcode,col_country,
                        col_greek_state,col_city,col_mail,
                        col_phone1,Col_Phone1Type,col_phone2,
                        Col_Phone2Type,Col_Comments,Col_website,
                        Col_ImportDate;
    @FXML
    private TextField txt_Instant_Search;
    @FXML
    private ImageView icon_img;
    
    public static String search_value;
    private final WindowsManager window_check = new WindowsManager();
    private ResourceBundle language_strings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        language_strings = rb;
        init_window();
    }  
    
        @FXML
        private void btnClose_Action(ActionEvent event) {
            window_check.ShowSearchContact_toggle(false);
            Stage this_window = (Stage)  btnClose.getScene().getWindow();
            this_window.close();
        }
        

    private void init_window(){
        icon_img.setImage(new Image(new File("resources/images/contacts/contact_manager.png").toURI().toString()));
        // Double Click Event for Table
        contacts_table.setRowFactory(tableview_evt ->{
            TableRow<Map> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                    && event.getClickCount() == 2) {
                        Map clickedRow = row.getItem();
                        OpenViewContactWindow(new Dimension().ViewEntry_window_widht,
                                              new Dimension().ViewEntry_window_height,
                                              clickedRow);
                    }
                });
            return row 
        ;});
        contacts_table.setItems(new CNT_Database().select_contacts());
        TableColumn[] columns = {Col_id,col_firstname,col_lastname,col_sex,
                                 col_address,col_zipcode,col_country,
                                 col_greek_state,col_city,col_mail,
                                 col_phone1,Col_Phone1Type,col_phone2,
                                 Col_Phone2Type,Col_Comments,Col_website,
                                 Col_ImportDate};
        String[] id = {"contact_id","firstname","lastname","sex",
                       "address","zipcode","country",
                       "greek_state","city","mail",
                       "phone1","phone1_type","phone2",
                       "phone2_type","comments","website",
                       "import_date"};
        int index = 0;
        for (TableColumn column : columns){
            column.setCellValueFactory(new MapValueFactory(id[index]));
            index++;
        }
    }
    private void OpenViewContactWindow(int width,int height,Map clickedRow){
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/contacts/ViewContact.fxml").openStream());
            ViewContact ctrl = fxml_loader.getController();
            ctrl.set_window(clickedRow);                            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(height);
            stage.setWidth(width);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {      
                    stage.close();
                }
            });
            stage.setTitle(language_strings.getString("window_viewContact"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
