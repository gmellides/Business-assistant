/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.sales;

import erpsystem.database.finance.FinanceSale;
import erpsystem.database.sales.ReceiptDatabase;
import erpsystem.database.sales.SAL_View;
import erpsystem.util.export.pdf.sales.ReceiptPDF;
import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SaleInfo implements Initializable {

    @FXML
    private TableView<Map> tbl_products;
    @FXML
    private Label lbl_saleID,lbl_cstName,lbl_cstLastname,lbl_cstSex,lbl_category,
            lbl_cstCustomerType,lbl_cstAddress,lbl_cstZipcode,lbl_cstCity,lbl_cstState,
            lbl_cstPhone,lbl_cstFax,lbl_paymentType,lbl_cstMail,lbl_salDate,lbl_finalPrice;
    @FXML
    private TableColumn col_id,col_prdName,col_category,col_quantity,col_price;
    
    private ResourceBundle bundle;
    private int Id;
    @FXML
    private Button btn_Paid;
    @FXML
    private ImageView saleInfo_img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bundle = rb;
        saleInfo_img.setImage(new Image(new File("resources/images/sales/show_sale.png").toURI().toString()));
    }    
    
    public void init_window(int saleID){
        this.Id = saleID;
        Map<String,String> data = new SAL_View().select_specific_sale(saleID).get(0);
        Label[] component = {lbl_saleID,lbl_cstName,lbl_cstLastname,lbl_cstSex,
            lbl_cstCustomerType,lbl_cstAddress,lbl_cstZipcode,lbl_cstCity,lbl_cstState,
            lbl_cstPhone,lbl_cstFax,lbl_cstMail,lbl_salDate,lbl_finalPrice};
        String[] mapIDs = {"sale_id","cst_name","cst_lastname","cst_sex","cst_customerType",
            "cst_address","cst_zipcode","cst_city","cst_state","cst_phone","cst_fax",
            "cst_mail","sal_finalPrice","sal_date"};
        int index = 0;
        for (Label lbl : component){
            lbl.setText(data.get(mapIDs[index]));
            index++;
        }
        if (data.get("sal_paymentMethod").equals("credit")){
            lbl_paymentType.setText(bundle.getString("rbtn_credit"));
            btn_Paid.setVisible(true);
        }else{
            lbl_paymentType.setText(bundle.getString("rbtn_Debit"));
        }
      
        // set product table

        tbl_products.setItems(new ReceiptDatabase().get_prdInfo(saleID));
        TableColumn[] ind_cols = {col_id,col_prdName,col_category,col_quantity,col_price};
        String[] MapID_i = {"prd_id","prd_name","prd_category","sal_quantity",
                            "prd_sellPrice","sal_price"};
        index = 0;
        for (TableColumn column : ind_cols){
            column.setCellValueFactory(new MapValueFactory(MapID_i[index]));
            index++;
        }
    }
    @FXML
    private void btn_Paid_Action(ActionEvent event) {
        new FinanceSale().change_status(Id);
    }
    @FXML
    private void btn_close_Action(ActionEvent event) {
        close_window();
    }
    
    private void close_window(){
        Stage win = (Stage) tbl_products.getScene().getWindow();
        win.close();
    }
    @FXML
    private void btn_receipt_Action(ActionEvent event) {
        new ReceiptPDF().save_receipt(bundle, Id);
        // alert 
    }

   
}
