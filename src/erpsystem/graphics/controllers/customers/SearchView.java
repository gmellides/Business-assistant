/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.customers;

import erpsystem.database.customers.CST_Companies;
import erpsystem.database.customers.CST_Individual;
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
    @FXML
    private TableColumn col_comp_companyID,col_comp_BusinessName,col_comp_address,
                        col_comp_zipcode,col_comp_city,col_comp_state,col_comp_country,
                        col_comp_customerType,col_comp_phone,col_comp_fax,col_comp_mail,
                        col_comp_importDate;
    @FXML
    private ImageView search_view_img;
    
    private static boolean isCompany;
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
        isCompany = false;
        if (tbl_companies.isVisible())
            tbl_companies.setVisible(false);
        if (!tbl_customers.isVisible())
            tbl_customers.setVisible(true);
    }

    @FXML
    private void btn_toggleBusiness_Action(ActionEvent event) {
        isCompany = true;
        if (tbl_customers.isVisible())
            tbl_customers.setVisible(false);
        if (!tbl_companies.isVisible())
            tbl_companies.setVisible(true);
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
        OpenManager();
    }
        
    private void init_window(){
        search_view_img.setImage(new Image(new File("resources/images/customers/search_view.png").toURI().toString()));
        if(isTableFiled())
            tbl_companies.setVisible(false);
        isCompany = false;
    }
    
    private void close_window(){
        // window toogle 
        Stage this_window = (Stage) btn_ToggleData.getScene().getWindow();
        this_window.close();
    }
    private boolean isTableFiled(){
        boolean flag = false;
            tbl_customers.setRowFactory(tableview_evt ->{
                TableRow<Map> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                        && event.getClickCount() == 2) {
                            Map clickedRow = row.getItem();
                            OpenViewCustomerWindow(new Dimension().ViewEntry_window_widht,
                                                   new Dimension().ViewEntry_window_height,
                                                   clickedRow);
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
                            OpenViewCustomerWindow(new Dimension().ViewEntry_window_widht,
                                                   new Dimension().ViewEntry_window_height,
                                                   clickedRow);
                        }
                    });
                return row 
            ;});
            
        tbl_customers.setItems(new CST_Individual().select_customers());
        tbl_companies.setItems(new CST_Companies().select_company());
         
            TableColumn[] customer_columns = {Col_cust_CustomerID,Col_cust_Firstname,
            Col_cust_Lastname,Col_cust_sex,Col_cust_address,Col_cust_zipcode,Col_cust_city,
            Col_cust_state,Col_cust_country,Col_cust_custType,Col_cust_phone,Col_cust_fax,
            Col_cust_mail,Col_cust_importDate};
            String[]  customer_id = {"cst_id","cst_name","cst_lastname","cst_sex",
            "cst_address","cst_zipcode","cst_city","cst_state","cst_country","cst_customerType","cst_phone",
            "cst_fax","cst_mail","cst_date"};
            int index = 0;
            for (TableColumn column : customer_columns){
                column.setCellValueFactory(new MapValueFactory(customer_id[index]));
                index++;
            }
            TableColumn[] company_columns = {col_comp_companyID,col_comp_BusinessName,
            col_comp_address,col_comp_zipcode,col_comp_city,col_comp_state,col_comp_country,
            col_comp_customerType,col_comp_phone,col_comp_fax,col_comp_mail,col_comp_importDate};
            String[] companies_id = {"cst_id","cst_name","cst_address","cst_zipcode","cst_city",
            "cst_state","cst_country","cst_customerType","cst_phone","cst_fax","cst_mail","cst_date"};
            index = 0;
            for (TableColumn column : company_columns){
                column.setCellValueFactory(new MapValueFactory(companies_id[index]));
                index++;
            }
            
        return flag;
    }
   
    // View Window
    private void OpenViewCustomerWindow(int Width,
                                        int Height,
                                        Map input){
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/customers/ViewCustomer.fxml").openStream());
            ViewCustomer d = fxml_loader.getController();
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
            stage.setTitle(default_strings.getString("window_customer_manager"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    // Manager
    private void OpenManager(){
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
            // stage.getIcons().add(new Image(getClass().getResource("icon.png").toExternalForm()));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
