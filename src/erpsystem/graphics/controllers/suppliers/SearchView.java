/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.suppliers;

import erpsystem.database.suppliers.SupplierCompanies;
import erpsystem.database.suppliers.SupplierIndividual;
import erpsystem.database.suppliers.SuppliersDatabase;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.WindowsManager;
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
import javafx.scene.control.cell.MapValueFactory;
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
        
    private static boolean isCompany;
    private ResourceBundle default_strings;
    @FXML
    private ToggleButton Person_toggle;
    @FXML
    private ToggleButton Companies_toggle;
    
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
            Suppliers_Person.setItems(new SupplierIndividual().select_suppliers());
            Suppliers_Companies.setItems(new SupplierCompanies().select_suppliers());
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
                                OpenWindow(724,490,clickedRow);
                            }
                        });
                    return row 
                ;});
            TableColumn[] supl_p_col = {Col_supl_person_id,Col_firstname,Col_lastname,
            Col_sex,Col_address,Col_zipcode,Col_country,Col_state,Col_supplier_type,
            Col_city,Col_phone,Col_mail,Col_fax,Col_bank,Col_IBAN,Col_ImportDate};
            
            String[] supl_p_map = {"supl_person_id","firstname","lastname","sex",
            "address","zipcode","country","state","supplier_type","city","phone",
            "mail","fax","bank","iban","import_date"};
            int index = 0;
            for (TableColumn item:supl_p_col){
                item.setCellValueFactory(new MapValueFactory(supl_p_map[index]));
                index++;
            }
           
            TableColumn[] supl_c_col = {Col_c_supl_company_id,Col_c_name,Col_c_address,
            Col_c_zipcode,Col_c_city,Col_c_state,Col_c_country,Col_c_supplier_type,
            Col_c_phone,Col_c_fax,Col_c_mail,Col_c_bank,Col_c_IBAN,Col_c_import_date};
           
            String[] supl_c_map = {"supl_company_id","name","address","zipcode","city","state",
            "country","supplier_type","phone","fax","mail","bank","iban","import_date"};
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
            stage.setTitle(default_strings.getString("customer_manager"));
            stage.setScene(scene);
            stage.setResizable(false);
         // Image icon = new Image(getClass().getResource("icon.png").toExternalForm());
         // stage.getIcons().add(icon);
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
            stage.setTitle(default_strings.getString("customer_manager"));
            stage.setScene(scene);
            stage.setResizable(false);
            // stage.getIcons().add(new Image(getClass().getResource("icon.png").toExternalForm()));
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
