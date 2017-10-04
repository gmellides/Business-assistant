/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.storage;

import erpsystem.database.storage.StorageView;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewStorage implements Initializable {

    @FXML
    private ListView<String> ProductList;
    @FXML
    private Pane ProductInfo;
    @FXML
    private Label lbl_productName,lbl_description,lbl_quantity,lbl_category,
                  lbl_purchasePrice,lbl_sellPrice;
    @FXML
    private Label lbl_state;

    private ResourceBundle default_strings;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ProductList.setItems(new StorageView().select_product_list());
       default_strings = rb;
    }    

    @FXML
    private void SelectedProduct_Action(MouseEvent event) {
        String[] selectedProd =  ProductList.getSelectionModel().getSelectedItem().split(" ");
        Map item = new StorageView().select_product_info(selectedProd[0]);
        ProductInfo.setVisible(true);
        Label[] labels = {lbl_productName,lbl_description,lbl_quantity,lbl_category,
                         lbl_purchasePrice,lbl_sellPrice};
        String[] map_keys = {"prd_name","prd_description","prd_quantity","prd_Category",
                             "prd_purchasePrice","prd_sellPrice"};
        int i = 0;
        for(Label lbl : labels){
            lbl.setText((String) item.get(map_keys[i]));
            i++;
        }
        if(Integer.parseInt((String) item.get("prd_quantity"))>10){
            lbl_state.setText(default_strings.getString("lbl_prdState_available"));
        }else{
            lbl_state.setText(default_strings.getString("lbl_prdState_lowQuantity"));
        }
    }
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
    }
    private void close_window(){
        Stage win = (Stage) ProductInfo.getScene().getWindow();
        win.close();
    }
    
}
