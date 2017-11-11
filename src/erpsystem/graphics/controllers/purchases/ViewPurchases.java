/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.purchases;

import erpsystem.database.purchases.PRC_View;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ViewPurchases implements Initializable {

    @FXML
    private TableView<Map> tbl_purchases_ind;
    @FXML
    private TableColumn col_ind_id,col_ind_name,col_ind_lastname,col_ind_address,
                        col_ind_zipcode,col_ind_city,col_ind_phone,col_ind_paymentMethod,
                        col_ind_finalPrice;
    @FXML
    private ToggleGroup view_group;
    @FXML
    private ImageView viewPrc_img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewPrc_img.setImage(new Image(new File("resources/images/purchases/show_purchase.png").toURI().toString()));
        fill_table();
    }    
    public void fill_table(){
        TableColumn[] cols = {col_ind_id,col_ind_name,col_ind_lastname,col_ind_address,
                      col_ind_zipcode,col_ind_city,col_ind_phone,col_ind_paymentMethod,
                      col_ind_finalPrice};
        tbl_purchases_ind.setItems(new PRC_View().select_purchases());
        String[] customer_id = {"prc_id","spl_name","spl_lastname","spl_address",
            "spl_zipcode","spl_city","spl_phone","prc_paymentMethod","prc_finalPrice"};
            int index = 0;
            for (TableColumn column : cols){
                column.setCellValueFactory(new MapValueFactory(customer_id[index]));
                index++;
            }
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
    }
    private void close_window(){
        Stage win = (Stage) viewPrc_img.getScene().getWindow();
        win.close();
    }
}
