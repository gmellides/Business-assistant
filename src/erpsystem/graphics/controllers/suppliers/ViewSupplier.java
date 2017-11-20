/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.suppliers;

import erpsystem.database.suppliers.SPL_View;
import erpsystem.entities.corpotations.SupplierCompany;
import erpsystem.entities.people.Supplier;
import erpsystem.util.system.Dimension;
import erpsystem.util.system.WindowsManager;
import erpsystem.util.xml.read.ComboBoxDataParser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ViewSupplier implements Initializable {

    @FXML
    private Label lbl_name,lbl_bank,lbl_supplierType,lbl_lastname,lbl_sex,
    lbl_address,lbl_zipcode,lbl_country,lbl_state,lbl_city,lbl_phone,lbl_IBAN,
    lbl_ImportDate,lbl_fax,lbl_mail,lbl_edt_sex,lbl_edt_lastname;
    @FXML
    private Button btn_Save,btn_close,btn_Delete,btn_Export;
    @FXML
    private ImageView view_spl_img;
    @FXML
    private TextField txt_edt_name,txt_edt_lastname,txt_edt_address,txt_edt_zipcode,
                      txt_edt_phone,txt_edt_fax,txt_edt_mail,txt_edt_bank,txt_edt_IBAN;
    @FXML
    private ComboBox<String> cmb_edt_sex,cmb_edt_state,cmb_edt_city,cmb_edt_splType,
                             cmb_edt_country;
    @FXML
    private ToggleButton btn_toggle;
    @FXML
    private Pane ViewPanel,EditPanel;
    
    private ResourceBundle default_strings;
    private Map clicked_row;
    private boolean isCompany;
    private int ID;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        view_spl_img.setImage(new Image(new File("resources/images/suppliers/view_supplier.png").toURI().toString()));
    }
    public void set_window(boolean isCompany,Map input){
        clicked_row = input;
        ID = Integer.parseInt((String)clicked_row.get("spl_id"));
        this.isCompany = isCompany;
        if (isCompany){
           set_splCompany_View();
           set_splCompany_Edit();
        }else{   
           set_splIndividual_View();
           set_splIndividual_Edit();
        }
    } 
    @FXML
    private void btn_SaveEdited_Action(ActionEvent event) {
        if (isCompany){
            if(new SPL_View().update_supplier_cmp(ID, get_UpdatedSPL_cmp())){
            Alert_dialog(Alert.AlertType.INFORMATION,
                        "dlg_UpdateEntry_title",
                        "dlg_UpdateEntry_header",
                        "dlg_UpdateEntry_message");
            }
            close_window();
            OpenSearchView();
        }else{
            if(new SPL_View().update_supplier_ind(ID, get_UpdatedSPL_ind())){
                Alert_dialog(Alert.AlertType.INFORMATION,
                        "dlg_UpdateEntry_title",
                        "dlg_UpdateEntry_header",
                        "dlg_UpdateEntry_message");
            }
            close_window();
            OpenSearchView();
        }
    }
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        close_window();
          OpenSearchView();
    }
    private Supplier get_UpdatedSPL_ind(){
        Supplier spl = new Supplier();
            spl.setFirstName(txt_edt_name.getText());
            spl.setLastName(txt_edt_lastname.getText());
            spl.setAddress(txt_edt_address.getText());
            spl.setZipcode(Integer.parseInt(txt_edt_zipcode.getText()));
            spl.setCity(cmb_edt_city.getSelectionModel().getSelectedItem());
            spl.setSex(cmb_edt_sex.getSelectionModel().getSelectedItem());
            spl.setState(cmb_edt_state.getSelectionModel().getSelectedItem());
            spl.setSupplier_Type(cmb_edt_splType.getSelectionModel().getSelectedItem());
            spl.setCountry(cmb_edt_country.getSelectionModel().getSelectedItem());
            spl.setPhone(txt_edt_phone.getText());
            spl.setFax(txt_edt_fax.getText());
            spl.setMail(txt_edt_mail.getText());
            spl.setBank(txt_edt_bank.getText());
            spl.setIBAN(txt_edt_IBAN.getText());
        return spl;
    }
    public SupplierCompany get_UpdatedSPL_cmp(){
        SupplierCompany spl = new SupplierCompany();
            spl.setAddress(txt_edt_address.getText());
            spl.setCompanyName(txt_edt_name.getText());
            spl.setBank(txt_edt_bank.getText());
            spl.setCity(cmb_edt_city.getSelectionModel().getSelectedItem());
            spl.setCountry(cmb_edt_country.getSelectionModel().getSelectedItem());
            spl.setFax(txt_edt_fax.getText());
            spl.setIBAN(txt_edt_IBAN.getText());
            spl.setMail(txt_edt_mail.getText());
            spl.setPhone(txt_edt_phone.getText());
            spl.setSupplierType(cmb_edt_splType.getSelectionModel().getSelectedItem());
            spl.setState(cmb_edt_state.getSelectionModel().getSelectedItem());
            spl.setZipCode(Integer.parseInt(txt_edt_zipcode.getText()));
        return spl;
    }
    @FXML
    private void btn_EditView_Toogle(ActionEvent event) {
        if (!btn_toggle.isSelected()){
            btn_Delete.setVisible(false);
                btn_Save.setVisible(false);
                btn_close.setVisible(true);
                btn_Export.setVisible(true);
                ViewPanel.setVisible(false);
                EditPanel.setVisible(true);
            btn_toggle.setText(default_strings.getString("gnr_btn_view"));
        }else{
            btn_Delete.setVisible(true);
                btn_Save.setVisible(true);
                btn_close.setVisible(false);
                btn_Export.setVisible(false);
                ViewPanel.setVisible(true);
                EditPanel.setVisible(false);
            btn_toggle.setText(default_strings.getString("gnr_btn_edit")); 
        }
    }
        private void set_splIndividual_View(){
            String Person_key = String.valueOf(clicked_row.get("spl_id"));
            Label[] labels = {lbl_name,lbl_lastname,lbl_sex,lbl_address,lbl_zipcode,
            lbl_country,lbl_state,lbl_supplierType,lbl_city,lbl_phone,lbl_mail,lbl_fax,
            lbl_bank,lbl_IBAN,lbl_ImportDate};
            String[] Lable_text = {"gnr_lbl_firstname","gnr_lbl_lastname","gnr_lbl_sex","gnr_lbl_address",
            "gnr_lbl_zipcode","gnr_lbl_country","gnr_lbl_state","lbl_supplierType","gnr_lbl_city","gnr_lbl_phone",
            "gnr_lbl_mail","gnr_lbl_fax","lbl_bankName","lbl_IBAN","gnr_lbl_date"};
            String[] Data = {"spl_name","spl_lastname","spl_sex","spl_address","spl_zipcode","spl_country",
            "spl_state","spl_supplierType","spl_city","spl_phone","spl_mail","spl_fax","spl_bank","spl_iban","spl_date"};
            int index = 0 ;
            for(Label item : labels){
                item.setText(default_strings.getString(Lable_text[index])+" "+String.valueOf(clicked_row.get(Data[index])));
                index++;
            }

        }
        private void set_splIndividual_Edit(){
            try{
                cmb_edt_sex.setItems(new ComboBoxDataParser().get_sex());
                cmb_edt_state.setItems(new ComboBoxDataParser().get_states_greece());
                cmb_edt_city.setItems(new ComboBoxDataParser().get_big_cities_greece());
                cmb_edt_splType.setItems(new ComboBoxDataParser().get_CustomerType());
                cmb_edt_country.setItems(new ComboBoxDataParser().get_countries());
            }catch(Exception e){
                e.printStackTrace();
            }
            String[] row = {"spl_name","spl_lastname","spl_address","spl_zipcode",
                            "spl_phone","spl_mail","spl_fax","spl_bank","spl_iban"};
            TextField[] edit_fields = {txt_edt_name,txt_edt_lastname,txt_edt_address,txt_edt_zipcode,
                       txt_edt_phone,txt_edt_mail,txt_edt_fax,txt_edt_bank,txt_edt_IBAN};
            int index = 0;
            for (TextField field :edit_fields){
                field.setText((String) clicked_row.get(row[index]));
                index++;
            }
            cmb_edt_sex.getSelectionModel().select((String) clicked_row.get("spl_sex"));
            cmb_edt_state.getSelectionModel().select((String) clicked_row.get("spl_state"));
            cmb_edt_city.getSelectionModel().select((String) clicked_row.get("spl_city"));
            cmb_edt_splType.getSelectionModel().select((String) clicked_row.get("spl_supplierType"));
            cmb_edt_country.getSelectionModel().select((String) clicked_row.get("spl_country"));
        }
        private void set_splCompany_View(){
            lbl_lastname.setVisible(false);
            lbl_sex.setVisible(false);
            Label[] labels = {lbl_name,lbl_address,lbl_zipcode,lbl_country,
            lbl_state,lbl_supplierType,lbl_city,lbl_phone,lbl_mail,lbl_fax,
            lbl_bank,lbl_IBAN,lbl_ImportDate};
            String[] Lable_text = {"company_businessName","gnr_lbl_address","gnr_lbl_zipcode",
            "gnr_lbl_country","gnr_lbl_state","lbl_supplierType","gnr_lbl_city","gnr_lbl_phone",
            "gnr_lbl_mail","gnr_lbl_fax","lbl_bankName","lbl_IBAN","gnr_lbl_date"};
            String[] Data = {"spl_name","spl_address","spl_zipcode","spl_country",
            "spl_state","spl_supplierType","spl_city","spl_phone","spl_mail","spl_fax","spl_bank","spl_iban","spl_date"};
            int index = 0 ;
            for(Label item : labels){
                item.setText(default_strings.getString(Lable_text[index])+" "+String.valueOf(clicked_row.get(Data[index])));
                index++;
            }
        }
        private void set_splCompany_Edit(){
            lbl_edt_sex.setVisible(false);
            lbl_edt_lastname.setVisible(false);
            txt_edt_lastname.setVisible(false);
            cmb_edt_sex.setVisible(false);
            try{
                cmb_edt_state.setItems(new ComboBoxDataParser().get_states_greece());
                cmb_edt_city.setItems(new ComboBoxDataParser().get_big_cities_greece());
                cmb_edt_splType.setItems(new ComboBoxDataParser().get_CustomerType());
                cmb_edt_country.setItems(new ComboBoxDataParser().get_countries());
            }catch(Exception e){
                e.printStackTrace();
            }
            String[] row = {"spl_name","spl_address","spl_zipcode",
                            "spl_phone","spl_mail","spl_fax","spl_bank","spl_iban"};
            TextField[] edit_fields = {txt_edt_name,txt_edt_address,txt_edt_zipcode,
                       txt_edt_phone,txt_edt_mail,txt_edt_fax,txt_edt_bank,txt_edt_IBAN};
            int index = 0;
            for (TextField field :edit_fields){
                field.setText((String) clicked_row.get(row[index]));
                index++;
            }
            cmb_edt_state.getSelectionModel().select((String) clicked_row.get("spl_state"));
            cmb_edt_city.getSelectionModel().select((String) clicked_row.get("spl_city"));
            cmb_edt_splType.getSelectionModel().select((String) clicked_row.get("spl_supplierType"));
            cmb_edt_country.getSelectionModel().select((String) clicked_row.get("spl_country"));
        }
    private void close_window(){
        Stage window = (Stage) lbl_name.getScene().getWindow();
        window.close();
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
    private void OpenSearchView(){
        try{
            FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/suppliers/SearchView.fxml").openStream());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(new Dimension().SearchView_window_height);
            stage.setWidth(new Dimension().SearchView_window_width);
               stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                   @Override
                   public void handle(WindowEvent we) {
                       new WindowsManager().toggle_window("suppliers/SearchView.fxml");
                       stage.close();
                   }
               });
            stage.setTitle(default_strings.getString("window_supplier_manager"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
            stage.show();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    @FXML
    private void btn_Delete_Action(ActionEvent event) {
        if(new SPL_View().delete_supplier(ID)){
            Alert_dialog(AlertType.INFORMATION,
                         "dlg_customerDelete_title",
                         "dlg_customerDelete_header",
                         "dlg_supplierDelete_message"); 
            close_window();
            OpenSearchView();
        }
    }
}
