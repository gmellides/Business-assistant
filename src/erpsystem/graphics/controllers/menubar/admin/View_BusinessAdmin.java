/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar.admin;

import erpsystem.entities.corpotations.BusinessAdmin;
import erpsystem.util.export.pdf.admin_data.AdminCard;
import erpsystem.util.export.pdf.admin_data.AdminPDF;
import erpsystem.util.xml.read.AdminDataParser;
import erpsystem.util.system.FileManager;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View_BusinessAdmin implements Initializable {

    @FXML
    private Button btn_ExportCard,btn_ExportPDF,btn_Close;
    @FXML
    private ImageView icon_imageview;
    @FXML
    private Pane background_pane;
    @FXML
    private Label lbl_fistname,lbl_lastname,lbl_sex,lbl_address,lbl_phone1,
    lbl_phone2,lbl_birthdate,lbl_city,lbl_zipcode,lbl_mail,lbl_taxreg,lbl_description;

    private BusinessAdmin adminData;
    private AdminDataParser admin_xml;
    private FileManager workspace;
    private ResourceBundle default_strings;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_background_and_icon();
        default_strings = rb;
        workspace = new FileManager();
        if (new File(workspace.getApp_data_admin()+"/admin_data.xml").exists()){
            admin_xml = new AdminDataParser(new File(workspace.getApp_data_admin()+"/admin_data.xml"));
            adminData = admin_xml.getData();
            put_data(adminData);
        }
    }    

    @FXML
    private void btnExportPDF_Action(ActionEvent event) {
        AdminPDF export_pdf = new AdminPDF();
        if (export_pdf.save_pdf(default_strings, adminData)){
            Alert_dialog(Alert.AlertType.CONFIRMATION,
                         "dlg_businessData_exportpdf_title",
                         "dlg_businessData_exportpdf_header",
                         "dlg_businessData_exportpdf_message");
            close_window();
        }
    }

    @FXML
    private void btnExportCard_Action(ActionEvent event) {
        if (new AdminCard().save_card(default_strings, adminData)){
            System.out.println("ok");
        }
    }

    @FXML
    private void btnClose_Action(ActionEvent event) {
        close_window();
    }
    
    public void set_background_and_icon(){
        icon_imageview.setImage(new Image(new File("resources/images/menubar/view_adminData.png").toURI().toString()));
    }
    private boolean put_data(BusinessAdmin admin){
        boolean flag = false;
            Label[] labels = {lbl_fistname,lbl_lastname,lbl_sex,lbl_address,
                              lbl_phone1,lbl_phone2,lbl_birthdate,lbl_city,
                              lbl_zipcode,lbl_mail,lbl_taxreg,lbl_description};
            
            String[] data = {admin.getFirstName(),admin.getLastName(),admin.getSex(),
                             admin.getAddress(),admin.getPhone1(),admin.getPhone2(),
                             String.valueOf(admin.getBirthdate()),admin.getCity(),
                             String.valueOf(admin.getZipCode()),admin.getMail(),
                             admin.getTaxReg(),admin.getDescription()};
            
            String[] default_stringID = {"gnr_lbl_firstname","gnr_lbl_lastname","gnr_lbl_sex","gnr_lbl_address",
                                         "gnr_lbl_phone1","gnr_lbl_phone2","edt_admin_bdate","gnr_lbl_city",
                                         "gnr_lbl_zipcode","gnr_lbl_mail","edt_admin_taxreg","edt_admin_description"};
            int index = 0;
            for (Label item : labels){
                item.setText(default_strings.getString(default_stringID[index])+" "+data[index]);
                index++;
            }
            flag = true;
        return flag;
    }
    private void close_window(){
        Stage this_stage = (Stage) btn_Close.getScene().getWindow();
        this_stage.close();
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
    
}
