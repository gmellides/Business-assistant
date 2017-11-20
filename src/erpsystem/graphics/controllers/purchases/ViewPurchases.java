/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.purchases;

import erpsystem.database.purchases.PRC_View;
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

public class ViewPurchases implements Initializable {

    @FXML
    private TableView<Map> tbl_purchases_ind,tbl_purchases_cmp;
    @FXML
    private TableColumn col_ind_id,col_ind_name,col_ind_lastname,col_ind_address,
                        col_ind_zipcode,col_ind_city,col_ind_phone,col_ind_paymentMethod,
                        col_ind_finalPrice;
    @FXML
    private ToggleGroup view_group;
    @FXML
    private ImageView viewPrc_img;


    private ResourceBundle default_strings;
    @FXML
    private TableColumn col_cmp_id,col_cmp_name,col_cmp_address,col_cmp_zipcode,
                        col_cmp_city,col_cmp_phone,col_cmp_paymentType,
                        col_cmp_finalPrice;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        viewPrc_img.setImage(new Image(new File("resources/images/purchases/purchase_manager.png").toURI().toString()));
        fill_table();
    }    
    
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
    }
    
    public void fill_table(){
        TableColumn[] cols = {col_ind_id,col_ind_name,col_ind_lastname,col_ind_address,
                      col_ind_zipcode,col_ind_city,col_ind_phone,col_ind_paymentMethod,
                      col_ind_finalPrice};
        tbl_purchases_ind.setItems(new PRC_View().select_purchases());
        String[] prcMap_id = {"prc_id","spl_name","spl_lastname","spl_address",
            "spl_zipcode","spl_city","spl_phone","prc_paymentMethod","prc_finalPrice"};
            int index = 0;
            for (TableColumn column : cols){
                column.setCellValueFactory(new MapValueFactory(prcMap_id[index]));
                index++;
            }
        tbl_purchases_ind.setRowFactory(tableview_evt ->{
            TableRow<Map> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                    && event.getClickCount() == 2) {
                    Map clickedRow = row.getItem();
                        OpenPurchaseSaleWindow(new Dimension().ViewEntry_window_widht,
                                           new Dimension().ViewEntry_window_height,
                                           Integer.parseInt((String)clickedRow.get("prc_id")),false);
                    }
                });
            return row 
        ;});
        tbl_purchases_cmp.setItems(new PRC_View().select_purchases_c());
        TableColumn[] cols_c = {col_cmp_id,col_cmp_name,col_cmp_address,col_cmp_zipcode,
                        col_cmp_city,col_cmp_phone,col_cmp_paymentType,
                        col_cmp_finalPrice};
        String[] prcMap_id_c = {"prc_id","spl_name","spl_address",
            "spl_zipcode","spl_city","spl_phone","prc_paymentMethod","prc_finalPrice"};
            index = 0;
            for (TableColumn column : cols_c){
                column.setCellValueFactory(new MapValueFactory(prcMap_id_c[index]));
                index++;
            }
        tbl_purchases_cmp.setRowFactory(tableview_evt ->{
            TableRow<Map> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY 
                                    && event.getClickCount() == 2) {
                    Map clickedRow = row.getItem();
                        OpenPurchaseSaleWindow(new Dimension().ViewEntry_window_widht,
                                           new Dimension().ViewEntry_window_height,
                                           Integer.parseInt((String)clickedRow.get("prc_id")),true);
                    }
                });
            return row 
        ;});
    }

    
    private void close_window(){
        Stage win = (Stage) viewPrc_img.getScene().getWindow();
        win.close();
    }
    private void OpenPurchaseSaleWindow(int Width,
                                    int Height,
                                    int input,boolean isCompany){
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/purchases/PurchaseInfo.fxml").openStream());
            PurchaseInfo controller = fxml_loader.getController();
            controller.init_window(input,isCompany);                            
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

    @FXML
    private void btn_individual_Action(ActionEvent event) {
        tbl_purchases_ind.setVisible(true);
        tbl_purchases_cmp.setVisible(false);
    }

    @FXML
    private void btn_companies_Action(ActionEvent event) {
        tbl_purchases_ind.setVisible(false);
        tbl_purchases_cmp.setVisible(true);
    }

}
