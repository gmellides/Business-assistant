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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gabri
 */
public class PurchaseInfo implements Initializable {

    @FXML
    private ImageView prcInfo_img;
    @FXML
    private Label lbl_name_txt,lbl_name,lbl_lastname_txt,lbl_lastname,lbl_phone,
                  lbl_fax,lbl_mail,lbl_address,lbl_city,lbl_state,lbl_zipcode,lbl_bank,
                  lbl_IBAN,lbl_supplierType,lbl_quantity,lbl_prdName,lbl_category,
                  lbl_prdDescription,lbl_prcPrice,lbl_sellPrice,lbl_paymentMethod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prcInfo_img.setImage(new Image(new File("resources/images/purchases/show_purchase.png").toURI().toString()));
    } 
    @FXML
    private void btn_close_Action(ActionEvent event) {
        Stage win = (Stage) prcInfo_img.getScene().getWindow();
        win.close();
    }
    public void init_window(int purchaseID,boolean isCompany){
         Map<String,String> data = new PRC_View().select_spacific_purchase(purchaseID).get(0);
         if (isCompany){
             lbl_lastname_txt.setVisible(false);
         }
         Label[] lbls = {lbl_name,lbl_lastname,lbl_mail,lbl_address,lbl_city,lbl_state,lbl_zipcode,
                         lbl_bank,lbl_IBAN,lbl_supplierType,lbl_fax,lbl_phone,
                         lbl_quantity,lbl_prdName,lbl_category,lbl_prdDescription,lbl_prcPrice,
                         lbl_sellPrice,lbl_paymentMethod};
         String[] MapID = {"spl_name","spl_lastname","spl_mail","spl_address","spl_city",
                           "spl_state","spl_zipcode","spl_bank","spl_IBAN","spl_supplierType",
                           "spl_fax","spl_phone",
                           "prd_quantity","prd_name","prd_category","prd_description",
                           "prd_prcPrice","prd_sellPrice","prc_paymentMethod"};
         int index = 0;
         for(Label item : lbls){
             item.setText(data.get(MapID[index]));
             index++;
         }
    }
}
