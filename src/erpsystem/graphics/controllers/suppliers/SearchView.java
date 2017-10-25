/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.suppliers;

import erpsystem.database.suppliers.SPL_Companies;
import erpsystem.database.suppliers.SPL_Individual;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SearchView implements Initializable {

    @FXML
    private TableView<Map> Suppliers_Person,Suppliers_Companies;
    @FXML
    private TableColumn Col_supl_person_id,Col_firstname,Col_lastname,
    Col_sex,Col_address,Col_zipcode,Col_country,Col_state,Col_supplier_type,
    Col_city,Col_phone,Col_mail,Col_fax,Col_bank,Col_IBAN,Col_ImportDate;    
    @FXML
    private TableColumn Col_c_supl_company_id,Col_c_name,Col_c_address,
    Col_c_zipcode,Col_c_city,Col_c_state,Col_c_country,Col_c_supplier_type,
    Col_c_phone,Col_c_fax,Col_c_mail,Col_c_bank,Col_c_IBAN,Col_c_import_date;
    @FXML
    private ToggleButton Person_toggle,Companies_toggle;
    
    private static boolean isCompany;
    private ResourceBundle default_strings;
    @FXML
    private ImageView search_supplier_img;
    @FXML
    private ToggleGroup view_group;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isCompany = false;
        if (isTableFiled()){
            default_strings = rb;
            Suppliers_Companies.setVisible(false);
        }
        search_supplier_img.setImage(new Image(new File("resources/images/suppliers/search_suppliers.png").toURI().toString()));
    }    
    
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
            OpenManager();
        }
        @FXML
        private void btn_togglePerson_Action(ActionEvent event) {
            isCompany = false;
            if (Suppliers_Companies.isVisible())
                Suppliers_Companies.setVisible(false);
            if (!Suppliers_Person.isVisible())
                Suppliers_Person.setVisible(true);
        }
        @FXML
        private void btn_toogleCompanies_Action(ActionEvent event) {
            isCompany = true;
            if (Suppliers_Person.isVisible())
                Suppliers_Person.setVisible(false);
            if (!Suppliers_Companies.isVisible())
                Suppliers_Companies.setVisible(true);
        }
    
    private boolean isTableFiled(){
        boolean flag = false;
            Suppliers_Person.setItems(new SPL_Individual().select_suppliers());
            Suppliers_Companies.setItems(new SPL_Companies().select_suppliers());
            Suppliers_Person.setRowFactory(tableview_evt ->{
                    TableRow<Map> row = new TableRow<>();
                    row.setOnMouseClicked(event -> {
                        if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                            && event.getClickCount() == 2) {
                                Map clickedRow = row.getItem();
                                OpenWindow(724,490,clickedRow);
                            }
                        });
                    return row 
                ;});
            Suppliers_Companies.setRowFactory(tableview_evt ->{
                    TableRow<Map> row = new TableRow<>();
                    row.setOnMouseClicked(event -> {
                        if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                            && event.getClickCount() == 2) {
                                Map clickedRow = row.getItem();
                                OpenWindow(new Dimension().ViewEntry_window_widht,
                                           new Dimension().ViewEntry_window_height,
                                           clickedRow);
                            }
                        });
                    return row 
                ;});
            TableColumn[] supl_p_col = {Col_supl_person_id,Col_firstname,Col_lastname,
            Col_sex,Col_address,Col_zipcode,Col_country,Col_state,Col_supplier_type,
            Col_city,Col_phone,Col_mail,Col_fax,Col_bank,Col_IBAN,Col_ImportDate};
            
            String[] supl_p_map = {"spl_id","spl_name","spl_lastname","spl_sex",
            "spl_address","spl_zipcode","spl_country","spl_state","spl_supplierType","spl_city","spl_phone",
            "spl_mail","spl_fax","spl_bank","spl_iban","spl_date"};
            int index = 0;
            for (TableColumn item:supl_p_col){
                item.setCellValueFactory(new MapValueFactory(supl_p_map[index]));
                index++;
            }
           
            TableColumn[] supl_c_col = {Col_c_supl_company_id,Col_c_name,Col_c_address,
            Col_c_zipcode,Col_c_city,Col_c_state,Col_c_country,Col_c_supplier_type,
            Col_c_phone,Col_c_fax,Col_c_mail,Col_c_bank,Col_c_IBAN,Col_c_import_date};
           
            String[] supl_c_map = {"spl_id","spl_name","spl_address","spl_zipcode","spl_city","spl_state",
            "spl_country","spl_supplierType","spl_phone","spl_fax","spl_mail","spl_bank","spl_iban","spl_date"};
            index = 0;
            for (TableColumn item : supl_c_col){
                item.setCellValueFactory(new MapValueFactory(supl_c_map[index]));
                index++;
            }
        return true;
    }
    private void OpenWindow(int Width,
                            int Height,
                            Map input){
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/suppliers/ViewSupplier.fxml").openStream());
            ViewSupplier d = fxml_loader.getController();
            d.set_window(isCompany,input);                            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(Height);
            stage.setWidth(Width);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {                    
                    stage.close();
                }
            });
            stage.setTitle(default_strings.getString("window_viewSupplier"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void OpenManager(){
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
    private void close_window(){
        Stage window = (Stage) Person_toggle.getScene().getWindow();
        window.close();
    }
}
