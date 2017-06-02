/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar;

import erpsystem.entities.business.BusinessAdmin;
import erpsystem.util.xml.read.AdminXML_Parser;
import erpsystem.util.system.FileManager;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View_BusinessAdmin implements Initializable {

    @FXML
    private Button btn_ExportPDF;
    @FXML
    private Button btn_ExportCard;
    @FXML
    private Button btn_Close;
    @FXML
    private ImageView icon_imageview;
    @FXML
    private Pane background_pane;
    @FXML
    private Label lbl_fistname;
    @FXML
    private Label lbl_lastname;
    @FXML
    private Label lbl_sex;
    @FXML
    private Label lbl_address;
    @FXML
    private Label lbl_phone1;
    @FXML
    private Label lbl_phone2;
    @FXML
    private Label lbl_birthdate;
    @FXML
    private Label lbl_city;
    @FXML
    private Label lbl_zipcode;
    @FXML
    private Label lbl_mail;
    @FXML
    private Label lbl_taxreg;
    @FXML
    private Label lbl_description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        set_background_and_icon();
        data = rb;
        workspace = new FileManager();
        if (new File(workspace.getApp_data_admin()+"/admin_data.xml").exists()){
            admin_xml = new AdminXML_Parser(new File(workspace.getApp_data_admin()+"/admin_data.xml"));
            put_data(rb,admin_xml.getData());
        }
    }    

    @FXML
    private void btnExportPDF_Action(ActionEvent event) {
        
    }

    @FXML
    private void btnExportCard_Action(ActionEvent event) {
    }

    @FXML
    private void btnClose_Action(ActionEvent event) {
        Stage this_stage = (Stage) btn_Close.getScene().getWindow();
        this_stage.close();
    }
    
    public void set_background_and_icon(){
        icon_imageview.setImage(new Image(new File("resources/images/menubar/view_adminData.png").toURI().toString()));
    }
    private boolean put_data(ResourceBundle default_strings,BusinessAdmin admin){
        boolean flag = false;
            Label[] labels = {lbl_fistname,lbl_lastname,lbl_sex,lbl_address,
                              lbl_phone1,lbl_phone2,lbl_birthdate,lbl_city,
                              lbl_zipcode,lbl_mail,lbl_taxreg,lbl_description};
            
            String[] data = {admin.getFirstName(),admin.getLastName(),admin.getSex(),
                             admin.getAddress(),admin.getPhone1(),admin.getPhone2(),
                             String.valueOf(admin.getBirthdate()),admin.getCity(),
                             String.valueOf(admin.getZipCode()),admin.getMail(),
                             admin.getTaxReg(),admin.getDescription()};
            
            String[] default_stringID = {"lbl_firstname","lbl_lastname","lbl_sex","lbl_address",
                                         "lbl_phone1","lbl_phone2","edt_admin_bdate","lbl_city",
                                         "lbl_zipcode","lbl_mail","edt_admin_taxreg","edt_admin_description"};
            int index = 0;
            for (Label item : labels){
                item.setText(default_strings.getString(default_stringID[index])+" "+data[index]);
                index++;
            }
            flag = true;
        return flag;
    }
    
   
    private AdminXML_Parser admin_xml;
    private FileManager workspace;
    private ResourceBundle data;
}
