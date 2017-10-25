/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.sales;

import erpsystem.database.sales.SAL_View;
import erpsystem.util.system.Dimension;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewSales implements Initializable {

    @FXML
    private TableView<Map> tbl_sales_ind,tbl_sales_cmp;
    @FXML
    private TableColumn col_ind_ID,col_ind_Name,col_ind_Lastname,col_ind_Payment,
                        col_ind_Phone,col_ind_Price,col_ind_Date;
    @FXML
    private TableColumn col_cmp_ID,col_cmp_Name,col_cmp_Phone,col_cmp_Payment,
                        col_cmp_Price,col_cmp_Date;
    @FXML
    private ToggleGroup ViewGroup;

    private ResourceBundle default_strings;
    @FXML
    private ImageView show_sales_img;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        table_fill();
        show_sales_img.setImage(new Image(new File("resources/images/sales/show_sale.png").toURI().toString()));
    }    
        @FXML
    private void btn_Sales_ind_Action(ActionEvent event) {
        tbl_sales_ind.setVisible(true);
        tbl_sales_cmp.setVisible(false);
    }

    @FXML
    private void btn_Sales_cmp_Action(ActionEvent event) {
        tbl_sales_ind.setVisible(false);
        tbl_sales_cmp.setVisible(true);
    }
    
     @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
    }

   
    private void table_fill(){
        tbl_sales_ind.setRowFactory(tableview_evt ->{
            TableRow<Map> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                    && event.getClickCount() == 2) {
                    Map clickedRow = row.getItem();
                        OpenViewSaleWindow(new Dimension().ViewEntry_window_widht,
                                           new Dimension().ViewEntry_window_height,
                                           Integer.parseInt((String)clickedRow.get("sale_id")));
                    }
                });
            return row 
        ;});
        tbl_sales_cmp.setRowFactory(tableview_evt ->{
            TableRow<Map> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                    && event.getClickCount() == 2) {
                    Map clickedRow = row.getItem();
                        OpenViewSaleWindow(new Dimension().ViewEntry_window_widht,
                                           new Dimension().ViewEntry_window_height,
                                           Integer.parseInt((String)clickedRow.get("sale_id")));
                    }
                });
            return row 
        ;});
        
        tbl_sales_ind.setItems(new SAL_View().select_sales());
        tbl_sales_cmp.setItems(new SAL_View().select_sales_c());
        
        TableColumn[] ind_cols = {col_ind_ID,col_ind_Name,col_ind_Lastname,col_ind_Payment,
                                  col_ind_Phone,col_ind_Price,col_ind_Date};
        String[] MapID_i = {"sale_id","cst_name","cst_lastname","sal_paymentMethod",
                            "cst_phone","sal_finalPrice","sal_date"};
        int index = 0;
        for (TableColumn column : ind_cols){
            column.setCellValueFactory(new MapValueFactory(MapID_i[index]));
            index++;
        }
        
        TableColumn[] cmp_cols = {col_cmp_ID,col_cmp_Name,col_cmp_Phone,col_cmp_Payment,
                                  col_cmp_Price,col_cmp_Date};
        String[] MapID_c = {"sale_id","cst_name","cst_phone","sal_paymentMethod",
                            "sal_finalPrice","sal_date"};
        index = 0;
        for (TableColumn column : cmp_cols){
            column.setCellValueFactory(new MapValueFactory(MapID_c[index]));
            index++;
        }
    }
    private void OpenViewSaleWindow(int Width,
                                    int Height,
                                    int input){
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/sales/SaleInfo.fxml").openStream());
            SaleInfo d = fxml_loader.getController();
            d.init_window(input);                            
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
    private void close_window(){
        Stage win = (Stage) tbl_sales_ind.getScene().getWindow();
        win.close();
    }
}
