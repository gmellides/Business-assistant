/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.purchases;

import erpsystem.database.products.PRD_Purchase;
import erpsystem.database.purchases.PRC_NewPurchase;
import erpsystem.database.suppliers.SPL_Purchases;
import erpsystem.entities.actions.Purchase;
import erpsystem.entities.product.Product;
import erpsystem.financial.BasicCalculations;
import erpsystem.util.system.Dimension;
import erpsystem.util.xml.read.PurchaseCategoryParser;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewPurchase implements Initializable {

    @FXML
    private ComboBox<String> cmb_Supplier,cmb_category;
    
    private ResourceBundle default_strings;
    @FXML
    private CheckBox chk_companyToggle;
    @FXML
    private ToggleGroup payment_method;
    @FXML
    private TextField txt_Quantity,txt_PurchasePrice,txt_SellPrice,txt_VAT,
                      txt_PreferedProfit,txt_ProductName;
    @FXML
    private TextArea txt_ProductDesc;
    @FXML
    private Label lbl_PurchaseCost;
     
    private int Quantity;
    private float PurchasePrice;
    private float PreferedProfit;
    private int VAT;
    @FXML
    private RadioButton rbtn_debit,rbtn_credit;
   

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        init_window();
    }    

    @FXML
    private void btn_NewCategory_Action(ActionEvent event) {
        close_window();
        try{
            OpenWindow("purchases/NewCategory.fxml",
                        new Dimension().NewCategory_window_width,
                        new Dimension().NewCategory_window_height,
                        default_strings.getString("edit_admin_data"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
    }
    @FXML
    private void chk_Company_Toggle(ActionEvent event) {
        if (chk_companyToggle.isSelected()){
            cmb_Supplier.setItems(new SPL_Purchases().select_Companies_combobox());
        }else{
            cmb_Supplier.setItems(new SPL_Purchases().select_Indevidual_combobox());
        }
    }
    @FXML
    private void txt_Quantity_KeyRelease(KeyEvent event) {
        Quantity = Integer.parseInt(txt_Quantity.getText());
    }

    @FXML
    private void txt_PurchasePrice_KeyRelease(KeyEvent event) {
        PurchasePrice = Float.parseFloat(txt_PurchasePrice.getText());
        lbl_PurchaseCost.setText(String.valueOf(new BasicCalculations().calc_purchase_cost(Quantity,PurchasePrice))+"€");
    }
    @FXML
    private void txt_VAT_KeyReleased(KeyEvent event) {
        VAT = Integer.parseInt(txt_VAT.getText());
        txt_SellPrice.setText(String.valueOf(new BasicCalculations().calc_sell_price(VAT, PurchasePrice, PreferedProfit)));
    }

    @FXML
    private void txt_PreferedProfit_KeyReleased(KeyEvent event) {
        VAT = Integer.parseInt(txt_VAT.getText());
        PreferedProfit = Float.parseFloat(txt_PreferedProfit.getText());
        txt_SellPrice.setText(String.valueOf(new BasicCalculations().calc_sell_price(VAT, PurchasePrice, PreferedProfit)));
    }

    @FXML
    private void btn_confirmSale_Action(ActionEvent event) {
        Purchase prc = new Purchase();
        String[] spl_str = cmb_Supplier.getSelectionModel().getSelectedItem().split(" ");
        prc.setSupplierID(Integer.parseInt(spl_str[0]));
        prc.setPurchasePrice(PurchasePrice);
        if (rbtn_debit.isSelected()){
            prc.setPaymentMethod("debit");
        }else if (rbtn_credit.isSelected()){
            prc.setPaymentMethod("credit");
        }
        Product prd = new Product(txt_ProductName.getText(),
                                  txt_ProductDesc.getText(),
                                  cmb_category.getSelectionModel().getSelectedItem(),
                                  Integer.parseInt(txt_Quantity.getText()),
                                  Float.parseFloat(txt_PurchasePrice.getText()),
                                  Integer.parseInt(txt_VAT.getText()),
                                  Float.parseFloat(txt_PreferedProfit.getText()),
                                  Float.parseFloat(txt_SellPrice.getText()));
        // add product to database 
        new PRD_Purchase().insert_product(prd);
        prc.setProductID(new PRD_Purchase().get_productID());
        //
        new PRC_NewPurchase().insert_purchase(prc);
        // Alert Dialog
    }
    private void close_window(){
        Stage win = (Stage) cmb_Supplier.getScene().getWindow();
        win.close();
    }
    private void OpenWindow(String WindowPath,
                                int Width,
                                int Height,
                                String WindowName) throws IOException{
        FXMLLoader fxml_loader = new FXMLLoader();
        fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
        Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/"+WindowPath).openStream());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setHeight(Height);
        stage.setWidth(Width);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
            //  window_check.toggle_window(WindowPath);
                stage.close();
            }
        });
        stage.setTitle(WindowName);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
        stage.show();
    }
    private void init_window(){
        try {
            cmb_category.setItems(new PurchaseCategoryParser().get_categories());
            cmb_Supplier.setItems(new SPL_Purchases().select_Indevidual_combobox());
        } catch (Exception ex) {
           ex.printStackTrace();
        }       
    }
}
