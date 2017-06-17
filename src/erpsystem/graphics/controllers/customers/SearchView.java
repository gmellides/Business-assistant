/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.database.customers.CustomersDatabase;
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
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SearchView implements Initializable {

    @FXML
    private TableView<Map> tbl_customers,tbl_companies;
    @FXML
    private ToggleButton btn_ToggleData;
    @FXML
    private ToggleGroup view_group;
    @FXML
    private TableColumn Col_cust_CustomerID,Col_cust_Firstname,Col_cust_Lastname,
                        Col_cust_sex,Col_cust_address,Col_cust_zipcode,Col_cust_city,
                        Col_cust_state,Col_cust_country,Col_cust_custType,Col_cust_phone,
                        Col_cust_fax,Col_cust_mail,Col_cust_importDate;
    
    private static boolean isBusiness;
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
    private void btn_toggleCustomers_Action(ActionEvent event) {
        isBusiness = false;
        if (tbl_companies.isVisible())
            tbl_companies.setVisible(false);
        if (!tbl_customers.isVisible())
            tbl_customers.setVisible(true);
    }

    @FXML
    private void btn_toggleBusiness_Action(ActionEvent event) {
        isBusiness = true;
        if (tbl_customers.isVisible())
            tbl_customers.setVisible(false);
        if (!tbl_companies.isVisible())
            tbl_companies.setVisible(true);
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage this_window = (Stage) btn_ToggleData.getScene().getWindow();
        this_window.close();
    }
    
    // hnadcuiovg
    
    private void init_window(){
        if(isTableFiled())
            tbl_companies.setVisible(false);
        isBusiness = false;
        
    }
    private boolean isTableFiled(){
        boolean flag = false;
            tbl_customers.setRowFactory(tableview_evt ->{
                TableRow<Map> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                        && event.getClickCount() == 2) {
                            Map clickedRow = row.getItem();
                            OpenWindow(clickedRow);
                        }
                    });
                return row 
            ;});    
            tbl_companies.setRowFactory(tableview_evt ->{
                TableRow<Map> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                        && event.getClickCount() == 2) {
                            Map clickedRow = row.getItem();
                            OpenWindow(clickedRow);
                        }
                    });
                return row 
            ;});
            
        tbl_customers.setItems(new CustomersDatabase().select_customer());
        tbl_companies.setItems(new CustomersDatabase().select_company());
         
            TableColumn[] customer_columns = {Col_cust_CustomerID,Col_cust_Firstname,
            Col_cust_Lastname,Col_cust_sex,Col_cust_address,Col_cust_zipcode,Col_cust_city,
            Col_cust_state,Col_cust_country,Col_cust_custType,Col_cust_phone,Col_cust_fax,
            Col_cust_mail,Col_cust_importDate};
            String[] id = {"customer_id","firstname","lastname","sex",
                           "address","zipcode","city","state","country",
                           "customer_type","phone","fax","mail","import_date"};
            int index = 0;
            for (TableColumn column : customer_columns){
                column.setCellValueFactory(new MapValueFactory(id[index]));
                index++;
            }
            
        return flag;
    }
    private void OpenWindow(Map input){
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/contacts/ViewContact.fxml").openStream());
            ViewCustomer d = fxml_loader.getController();
            d.set_window(isBusiness,input);                            
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(444);
            stage.setWidth(680);
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
}
