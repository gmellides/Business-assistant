/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.sales;

import erpsystem.database.customers.CST_Sales;
import erpsystem.database.products.ProductsSales;
import erpsystem.database.sales.NewSaleDatabase;
import erpsystem.entities.actions.Sale;
import erpsystem.util.export.pdf.sales.ReceiptPDF;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewSale implements Initializable {

    @FXML
    private TextField txt_Quantity;
    @FXML
    private CheckBox chk_Company,chk_Receipt;
    @FXML
    private ComboBox<String> cmb_customer_select,cmb_product_select;
    @FXML
    private Label lbl_sellPrice,lbl_product_cost,lbl_finalPrice;
    @FXML
    private ToggleGroup payment_method;
    @FXML
    private TableColumn col_id,col_productName,col_quantity,col_price;
    @FXML    
    private TableView<Map> basket_table;
    @FXML
    private RadioButton rdb_credit,rdb_debit;
    @FXML
    private ImageView newSale_img;
    
    private ResourceBundle default_strings;
    // Product Sell Price 
    private float productPrice;
    private int productQuantity; 
    // List with IDs and available Quantity.(Data from Database)
    private HashMap<Integer,String> quantityList;
    // List with IDs and sell price for each product
    private HashMap<Integer,String> priceList;
    // Basket Table 
    private ObservableList<Map> basket;
    private Sale new_sale;
    private int prd_id;
    float final_price;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        init_window();
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
                cmb_customer_select.setItems(new CST_Sales().select_cst_companies());
            }else{
                cmb_customer_select.setItems(new CST_Sales().select_cst_individual());
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
            prd_id = Integer.parseInt(splited_string[0]);
            
            productPrice = Float.parseFloat(priceList.get(prd_id));
            productQuantity = Integer.valueOf(quantityList.get(prd_id));
           
            lbl_sellPrice.setText(priceList.get(prd_id)+"€");
       
            txt_Quantity.setText("");
            lbl_product_cost.setText("----"); 
        }
        /**
         * sets customer id to sale object 
         * @param event 
         */
         @FXML
        private void cmb_selectedCustomer_Action(ActionEvent event) {
            String val = cmb_customer_select.getSelectionModel().getSelectedItem();
            String[] splited_string = val.split(" ");
            new_sale.setCustomerID(Integer.parseInt(splited_string[0]));
        }
        /**
         * Add to basket Button. 
         *  Adds Row at basket table 
         *  Updates the final price on sale.
         * @param event 
         */
        @FXML
        private void btn_addToBasket_Action(ActionEvent event) {
            if ((!txt_Quantity.getText().isEmpty())&&(Integer.parseInt(txt_Quantity.getText())>0)){
                Map<String,String> basket_row = new HashMap();
                    basket_row.put("prd_id",String.valueOf(prd_id));
                    basket_row.put("prd_name",new ProductsSales().select_prdName(prd_id));
                    basket_row.put("quantity",txt_Quantity.getText());
                    float prd_price = Integer.parseInt(txt_Quantity.getText())*Float.parseFloat(priceList.get(prd_id));
                    basket_row.put("price", String.valueOf(prd_price));
                basket.add(basket_row);
                basket_table.setItems(basket);      
                final_price += prd_price;
            }
            new_sale.setFinal_Price(final_price);
            lbl_finalPrice.setText(String.valueOf(final_price));
        }
        @FXML
        private void btn_ConfirmSale_Action(ActionEvent event) {
            if (!basket.isEmpty()){   
                // Takes Product ID from Basket Table View
                    int[] prds = new int[basket.size()];
                    int index = 0;
                    for (Map i:basket){
                        prds[index] = Integer.parseInt((String) i.get("prd_id"));
                        index++;
                    }
                new_sale.setProducts(prds);
                // Takes the quantity from basket table 
                    int[] quantity = new int[basket.size()];
                    index = 0;
                    for (Map i:basket){
                        quantity[index] = Integer.parseInt((String) i.get("quantity"));
                        index++;
                    }
                new_sale.setQuantity(quantity);
                // takes the price
                    float[] price = new float[basket.size()];   
                    index = 0;
                    for (Map i:basket){
                        price[index] = Float.parseFloat((String) i.get("price"));
                        index++;
                    }
                    new_sale.setPrice(price);
                    new NewSaleDatabase().insert_sale(new_sale);
            if (chk_Receipt.isSelected()){
                new ReceiptPDF().save_receipt(default_strings);
            }
                close_window();
            }else{
                Alert_dialog(Alert.AlertType.ERROR,
                            "dlg_basketError_title",
                            "dlg_basketError_header",
                            "dlg_basketError_message");
            }
        }
        @FXML
        private void rbtn_Credit_Action(ActionEvent event) {
            new_sale.setPaymentMethod("credit");
        }

        @FXML
        private void rbtn_Debit_Action(ActionEvent event) {
            new_sale.setPaymentMethod("debit");
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
    /**
     * Init Window Method 
     */    
    private void init_window(){
        newSale_img.setImage(new Image(new File("resources/images/sales/sales.png").toURI().toString()));
        new_sale = new Sale();
        final_price = 0;
        basket = FXCollections.observableArrayList();
        // Price and Quantity Lists for check.
        cmb_customer_select.setItems(new CST_Sales().select_cst_individual());
        cmb_product_select.setItems(new ProductsSales().getproducts());
        // Price list with ID and SellPrice from the database
        priceList = new ProductsSales().select_sellPrice();
        // Quantity List with ID and Quantity from the database 
        quantityList = new ProductsSales().select_quantity();
        lbl_product_cost.setText("----");
        // Table Initialization
        TableColumn[] cols = {col_id,col_productName,col_quantity,col_price};
        String[] companies_id = {"prd_id","prd_name","quantity","price"};
        int index = 0;
        for (TableColumn column : cols){
            column.setCellValueFactory(new MapValueFactory(companies_id[index]));
            index++;
        }
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

    @FXML
    private void cmb_selectedProduct_Action(MouseEvent event) {
    }
}
