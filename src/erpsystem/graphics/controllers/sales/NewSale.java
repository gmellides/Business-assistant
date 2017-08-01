/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.sales;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class NewSale implements Initializable {

    @FXML
    private ComboBox<?> cmb_customer,cmb_product;
    @FXML
    private TextField txt_Quantity;
    @FXML
    private CheckBox chk_Company,chk_Receipt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_ConfirmSale_Action(ActionEvent event) {
    }

    @FXML
    private void btn_Close_Action(ActionEvent event) {
    }

    @FXML
    private void btn_addToBasket_Action(ActionEvent event) {
    }
    
}
