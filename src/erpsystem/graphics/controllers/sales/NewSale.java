/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.sales;

import erpsystem.database.customers.CustomerSales;
import erpsystem.database.products.ProductsSales;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class NewSale implements Initializable {

    @FXML
    private TextField txt_Quantity;
    @FXML
    private CheckBox chk_Company,chk_Receipt;
    @FXML
    private ComboBox<String> cmb_customer_select,cmb_product_select;
    @FXML
    private Label lbl_sellPrice,lbl_product_cost;

    private ResourceBundle default_strings;
    private float productPrice;
    private int productQuantity;
    private String productName;
    private HashMap<Integer,String> quantityList;
    private HashMap<Integer,String> priceList;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        init_window();
    }    

        @FXML
        private void btn_ConfirmSale_Action(ActionEvent event) {
        }
       
        @FXML
        private void btn_addToBasket_Action(ActionEvent event) {
            if ((!txt_Quantity.getText().isEmpty())&&(Integer.parseInt(txt_Quantity.getText())>0)){
                
            }
        }
        /**
         * Quantity TextField event.
         * Calculates the cost for selected product with quantity
         * it only allows values lower than quantity.
         * @param event 
         */
        @FXML
        private void txt_Quantity_Action(KeyEvent event) {
            // Check for numeric Input
            if (txt_Quantity.getText().matches("\\d+")){
                // Check for 
                if (!txt_Quantity.getText().equals("")){
                    int quantity = Integer.parseInt(txt_Quantity.getText());
                    if (quantity <= productQuantity){
                        lbl_product_cost.setText(String.valueOf(quantity*productPrice));
                    }else{
                        Alert_dialog(Alert.AlertType.ERROR,
                            "dlg_inputDigitError_title",
                            "dlg_inputDigitError_header",
                            "dlg_inputDigitError_message");
                        txt_Quantity.setText("");
                    }
                }else{
                    lbl_product_cost.setText("----");
                }
            }else{
                Alert_dialog(Alert.AlertType.ERROR,
                            "dlg_inputDigitError_title",
                            "dlg_inputDigitError_header",
                            "dlg_inputDigitError_message");
                txt_Quantity.setText("");
            }
        }
        /**
         * CheckBox event for Customer.
         * if is checked combobox data will change the data to companies
         * if isn't selected combobox data will be for customers 
         * @param event 
         */
        @FXML
        private void chk_Company_Toggle(ActionEvent event) {
            if (chk_Company.isSelected()){
                cmb_customer_select.setItems(new CustomerSales().select_cst_companies());
            }else{
                cmb_customer_select.setItems(new CustomerSales().select_cst_individual());
            }
        }
        /**
         * ComboBox Products Action Event.
         * It takes selected product with the ID keeps the price 
         * and quantity. Also gives the price of the product in a 
         * label.
         * @param event 
         */
        @FXML
        private void cmb_selectedProduct_Action(ActionEvent event) {
            String val = cmb_product_select.getSelectionModel().getSelectedItem();
            String[] splited_string = val.split(" ");
            productPrice = Float.parseFloat(priceList.get(Integer.parseInt(splited_string[0])));
            productQuantity = Integer.valueOf(quantityList.get(Integer.parseInt(splited_string[0])));
            productName = splited_string[1];
            lbl_sellPrice.setText(priceList.get(Integer.parseInt(splited_string[0]))+"€");
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
    /**
     * Init Window Method 
     */    
    private void init_window(){
        // Price list with ID and SellPrice from the database
        priceList = new ProductsSales().select_sellPrice();
        // Quantity List with ID and Quantity from the database 
        quantityList = new ProductsSales().select_quantity();
        cmb_customer_select.setItems(new CustomerSales().select_cst_individual());
        cmb_product_select.setItems(new ProductsSales().getproducts());
        lbl_product_cost.setText("----");
    }
    private void Alert_dialog(Alert.AlertType type,
                               String Title,
                               String Header,
                               String Message){
            Alert succed_dialog = new Alert(type);
            succed_dialog.setTitle(default_strings.getString(Title));
            succed_dialog.setHeaderText(default_strings.getString(Header));
            succed_dialog.setContentText(default_strings.getString(Message));
            succed_dialog.showAndWait();
        }
    private void close_window(){
        Stage win = (Stage) cmb_customer_select.getScene().getWindow();
        win.close();
    }
}
