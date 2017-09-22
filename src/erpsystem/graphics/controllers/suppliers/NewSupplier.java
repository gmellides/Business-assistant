/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.suppliers;

import erpsystem.database.suppliers.SupplierCompanies;
import erpsystem.database.suppliers.SupplierIndividual;
import erpsystem.entities.corpotations.SupplierCompany;
import erpsystem.entities.people.Supplier;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.WindowsManager;
import erpsystem.util.xml.read.ComboBoxDataParser;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewSupplier implements Initializable {

    @FXML
    private Label lbl_name;
    @FXML
    private TextField txtName,txtLastName,txtAddress,txtZipCode,txtPhone,txtFax,
                      txtMail,txtBankName,txtIBAN;
    @FXML
    private ComboBox<String> cmbSex,cmbCountry,cmbState,cmbCity,cmbSupplierType;
    @FXML
    private CheckBox business_toggle;
    
    private ResourceBundle default_strings;
    private boolean isBusiness;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_window(rb);
    }    
        @FXML
        private void btn_Save_Action(ActionEvent event) {
            if (isBusiness){    
                new SupplierCompanies().insert_supplier(get_cmp_obj());
                Alert_dialog(AlertType.INFORMATION,
                             "dlg_supplierSaved_title",
                             "dlg_supplierSaved_header",
                             "dlg_supplierSaved_message");
                close_window();
            }else{  
                new SupplierIndividual().insert_supplier(get_spl_obj());
                Alert_dialog(AlertType.INFORMATION,
                             "dlg_supplierSaved_title",
                             "dlg_supplierSaved_header",
                             "dlg_supplierSaved_message");
                close_window();
            }
        }
        @FXML
        private void isBusiness_Action(ActionEvent event) {
            if(business_toggle.isSelected()){
                isBusiness = true;
                lbl_name.setText(default_strings.getString("company_businessName"));
                txtLastName.setDisable(true);
                cmbSex.setDisable(true);
            }else{
                isBusiness = false;
                lbl_name.setText(default_strings.getString("gnr_lbl_firstname"));
                txtLastName.setDisable(false);
                cmbSex.setDisable(false);
            }
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
            OpenManager();
        }
    private void init_window(ResourceBundle rb){
        isBusiness = false;
        default_strings = rb;
        try{
            cmbSex.setItems(new ComboBoxDataParser().get_sex());
            cmbCountry.setItems(new ComboBoxDataParser().get_countries());
            cmbState.setItems(new ComboBoxDataParser().get_states_greece());
            cmbCity.setItems(new ComboBoxDataParser().get_big_cities_greece());
            cmbSupplierType.setItems(new ComboBoxDataParser().get_CustomerType());
        }catch(Exception e){
            e.printStackTrace();
        }
        // image 
    }
    private void close_window(){
        // window manager toggle
        Stage window = (Stage) lbl_name.getScene().getWindow();
        window.close();
        OpenManager();
    }
    private SupplierCompany get_cmp_obj(){
        SupplierCompany new_spl = new SupplierCompany();
            new_spl.setCompanyName(txtName.getText());
            new_spl.setAddress(txtAddress.getText());
            new_spl.setZipCode(Integer.parseInt(txtZipCode.getText()));
            new_spl.setState(cmbState.getSelectionModel().getSelectedItem());
            new_spl.setCity(cmbCity.getSelectionModel().getSelectedItem());
            new_spl.setSupplierType(cmbSupplierType.getSelectionModel().getSelectedItem());
            new_spl.setCountry(cmbCountry.getSelectionModel().getSelectedItem());
            new_spl.setIBAN(txtIBAN.getText());
            new_spl.setPhone(txtPhone.getText());
            new_spl.setMail(txtMail.getText());
            new_spl.setBank(txtBankName.getText());
            new_spl.setFax(txtFax.getText());
        return new_spl;
    }
    private Supplier get_spl_obj(){
        Supplier new_spl = new Supplier();
            new_spl.setFirstName(txtName.getText());
            new_spl.setLastName(txtLastName.getText());
            new_spl.setSex(cmbSex.getSelectionModel().getSelectedItem());
            new_spl.setAddress(txtAddress.getText());
            new_spl.setCity(cmbCity.getSelectionModel().getSelectedItem());
            new_spl.setZipcode(Integer.parseInt(txtZipCode.getText()));
            new_spl.setState(cmbState.getSelectionModel().getSelectedItem());
            new_spl.setSupplier_Type(cmbSupplierType.getSelectionModel().getSelectedItem());
            new_spl.setCountry(cmbCountry.getSelectionModel().getSelectedItem());
            new_spl.setIBAN(txtIBAN.getText());
            new_spl.setPhone(txtPhone.getText());
            new_spl.setMail(txtMail.getText());
            new_spl.setBank(txtBankName.getText());
            new_spl.setFax(txtFax.getText());
        return new_spl;
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
    private void OpenManager(){
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/suppliers/SupplierManager.fxml").openStream());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(new Dimension().Manager_window_height);
            stage.setWidth(new Dimension().Manager_window_width);
               stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                   @Override
                   public void handle(WindowEvent we) {
                       new WindowsManager().toggle_window("suppliers/SupplierManager.fxml");
                       stage.close();
                   }
               });
            stage.setTitle(default_strings.getString("window_supplier_manager"));
            stage.setScene(scene);
            stage.setResizable(false);
            // stage.getIcons().add(new Image(getClass().getResource("icon.png").toExternalForm()));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
