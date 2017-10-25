/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.storage;

import erpsystem.database.storage.StorageView;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
                  lbl_purchasePrice,lbl_sellPrice,lbl_Profit,lbl_SumProfit,
                  lbl_state,lbl_VAT,lbl_noProductSelected;
    @FXML
    private ImageView storage_img;
   
    private ResourceBundle default_strings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (new StorageView().select_product_list().isEmpty()){
            ProductList.setDisable(true);
            lbl_noProductSelected.setText(rb.getString("lbl_noProducts"));
        }else{
            ProductList.setItems(new StorageView().select_product_list());
        }
       default_strings = rb;
       storage_img.setImage(new Image(new File("resources/images/storage/storage_info.png").toURI().toString()));
    }    

    @FXML
    private void SelectedProduct_Action(MouseEvent event) {
        lbl_noProductSelected.setVisible(false);
        String[] selectedProd =  ProductList.getSelectionModel().getSelectedItem().split(" ");
        Map item = new StorageView().select_product_info(selectedProd[0]);
        ProductInfo.setVisible(true);
        Label[] labels = {lbl_productName,lbl_description,lbl_quantity,lbl_category,
                         lbl_purchasePrice,lbl_sellPrice};
        String[] map_keys = {"prd_name","prd_description","prd_quantity","prd_Category",
                             "prd_purchasePrice","prd_sellPrice"};
        int i = 0;
        for(Label lbl : labels){
            if(map_keys[i].equals("prd_purchasePrice")||
               map_keys[i].equals("prd_sellPrice")){
                lbl.setText((String) item.get(map_keys[i])+"€");
            }else{
                lbl.setText((String) item.get(map_keys[i]));
            }
            i++;
        }
        if(Integer.parseInt((String) item.get("prd_quantity"))>10){
            lbl_state.setText(default_strings.getString("lbl_prdState_available"));
        }else{
            lbl_state.setText(default_strings.getString("lbl_prdState_lowQuantity"));
        }
        float prof = Float.parseFloat((String)item.get("prd_sellPrice")) - Float.parseFloat((String) item.get("prd_purchasePrice"));
        lbl_Profit.setText(String.valueOf(prof)+"€");
        float quantity = Integer.parseInt((String) item.get("prd_quantity"));
        lbl_SumProfit.setText(String.valueOf(prof*quantity)+"€");
        lbl_VAT.setText(String.valueOf(item.get("prd_VAT"))+"%");
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
