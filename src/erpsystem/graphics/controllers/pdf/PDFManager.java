/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.pdf;

import erpsystem.database.contacts.CNT_Database;
import erpsystem.database.customers.CST_Companies;
import erpsystem.database.customers.CST_Individual;
import erpsystem.database.storage.StorageView;
import erpsystem.database.suppliers.SPL_Companies;
import erpsystem.database.suppliers.SPL_Individual;
import erpsystem.entities.corpotations.Business;
import erpsystem.util.export.pdf.admin_data.AdminCard;
import erpsystem.util.export.pdf.business_data.BusinessCard;
import erpsystem.util.export.pdf.contacts.ContactsTablePDF;
import erpsystem.util.export.pdf.customers.CustomersTablePDF;
import erpsystem.util.export.pdf.storage.StorageTablePDF;
import erpsystem.util.export.pdf.suppliers.SuppliersTablePDF;
import erpsystem.util.system.FileManager;
import erpsystem.util.xml.read.AdminDataParser;
import erpsystem.util.xml.read.BusinessDataParser;
import erpsystem.util.xml.write.BusinessData;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PDFManager implements Initializable {

    @FXML
    private Button btn_close;

    private ResourceBundle default_strings;
    @FXML
    private ImageView pdf_icon_imgView;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        pdf_icon_imgView.setImage(new Image(new File("resources/images/pdf/pdf_manager.png").toURI().toString()));
    }    

    @FXML
    private void btn_close_Action(ActionEvent event) {
        Stage win = (Stage) btn_close.getScene().getWindow();
        win.close();
    }

        @FXML
        private void btn_Customer_Action(ActionEvent event) {
            if(new CustomersTablePDF().save_file(default_strings,
                                                     new CST_Individual().select_customers(),
                                                     new CST_Companies().select_company())){
                    Alert_dialog(Alert.AlertType.INFORMATION,
                            "dlg_customerTableSaved_title",
                            "dlg_customerTableSaved_header",
                            "dlg_customerTableSaved_message");
                    try{
                        File pdf_file = new File(new FileManager().getDocuments_root());
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(pdf_file);
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
        }
        @FXML
        private void btn_Contacts_Action(ActionEvent event) {
            if(new ContactsTablePDF().save_file(default_strings, 
                  new CNT_Database().select_contacts())){
                   try{
                        File pdf_file = new File(new FileManager().getDocuments_root());
                        Desktop desktop = Desktop.getDesktop();
                        desktop.open(pdf_file);
                   }catch(IOException e){
                       e.printStackTrace();
                   }
               }
        }
        @FXML
        private void btn_storage_Action(ActionEvent event) {
            if(new StorageTablePDF().save_file(default_strings,
                                                new StorageView().select_products())){
                Alert_dialog(Alert.AlertType.INFORMATION,
                        "dlg_customerTableSaved_title",
                        "dlg_customerTableSaved_header",
                        "dlg_customerTableSaved_message");
                try{
                    File pdf_file = new File(new FileManager().getDocuments_root());
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(pdf_file);
                }catch(IOException e){
                    e.printStackTrace();
                }
            }

        }
        @FXML
        private void btn_suppliers_Action(ActionEvent event) {
            if (new SuppliersTablePDF().save_file(default_strings, 
                                                      new SPL_Individual().select_suppliers(),
                                                      new SPL_Companies().select_suppliers())){
                Alert_dialog(Alert.AlertType.INFORMATION,
                           "dlg_supplierTableSaved_title",
                            "dlg_supplierTableSaved_header",
                            "dlg_supplierTableSaved_message");
                try{
                    File pdf_file = new File(new FileManager().getDocuments_root());
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(pdf_file);
                }catch(IOException e){
                    e.printStackTrace();
                }
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

    @FXML
    private void btn_AdminCard_Action(ActionEvent event) {
        if (new AdminCard().save_card(default_strings,new AdminDataParser(new File(new FileManager().getApp_data_admin()+"/admin_data.xml")).getData())){
            Alert_dialog(Alert.AlertType.INFORMATION,
                         "dlg_businessData_exportpdf_title",
                         "dlg_businessData_exportpdf_header",
                         "dlg_businessData_exportpdf_message");
            try{
                File pdf_file = new File(new FileManager().getDocuments_business_data());
                Desktop desktop = Desktop.getDesktop();
                desktop.open(pdf_file);
            }catch(IOException e){
                e.printStackTrace();
            }
           
        }
    }

    @FXML
    private void btn_BCard_Action(ActionEvent event) {
        Business business = new BusinessDataParser(new BusinessData().get_File()).getData();
        if(new BusinessCard().save_card(default_strings, business)){
                Alert_dialog(Alert.AlertType.CONFIRMATION,
                            "dlg_businessData_exportpdf_title",
                            "dlg_businessData_exportpdf_header",
                            "dlg_businessData_exportpdf_message");
            try{
                File pdf_file = new File(new FileManager().getDocuments_business_data());
                Desktop desktop = Desktop.getDesktop();
                desktop.open(pdf_file);
            }catch(IOException e){
                e.printStackTrace();
            }
            }
    }

    
}
