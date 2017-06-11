/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar;

import erpsystem.entities.business.Business;
import erpsystem.util.export.pdf.business_data.BusinessPDF;
import erpsystem.util.system.FileManager;
import erpsystem.util.system.WindowsManager;
import erpsystem.util.xml.read.BusinessXML_Parser;
import erpsystem.util.xml.write.BusinessData;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class View_BusinessData implements Initializable {

    @FXML
    private Button btn_ExportCard;
    @FXML
    private Button btn_ExportPDF;
    @FXML
    private ImageView b_logo;
    @FXML
    private Line graphic_line;
    @FXML
    private Label lbl_bName;
    @FXML
    private Label lbl_bDescription;
    @FXML
    private Label lbl_lblPhone;
    @FXML
    private Label lbl_Address;
    @FXML
    private Label lbl_Fax;
    @FXML
    private Label lbl_City;
    @FXML
    private Label lbl_TaxReg;
    @FXML
    private Label lbl_EstablishData;
    @FXML
    private Label lbl_LastEdit;
    @FXML
    private Pane background_pane;
    @FXML
    private ImageView icon_imageview;
    @FXML
    private Label lbl_nodata;
    @FXML
    private Label lbl_Mail;
    @FXML
    private Label lbl_phone2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        init_window(rb);
        
        workplace = new FileManager();
        BusinessXML = new BusinessData();
            if(BusinessXML.get_File().exists()){
                set_logo();
                set_data(default_strings,BusinessXML.get_File());
              //  enable_components();
            }else{
                clear_window();  
                no_data_message(rb);
            }   
    }
    
    // ===== FXML Buttons Action =====
        @FXML
        private void btnExportCard_Action(ActionEvent event) {

        }
        @FXML
        private void btnExportPDF_Action(ActionEvent event) {
            export_pdf = new BusinessPDF();
            if(export_pdf.save_pdf(default_strings,business)){
                Alert succed_dialog = new Alert(Alert.AlertType.CONFIRMATION);
            succed_dialog.setTitle(default_strings.getString("dialog_businessData_exportpdf_Title"));
            succed_dialog.setContentText(default_strings.getString("dialog_businessData_exportpdf_Message"));
            succed_dialog.showAndWait();
            }
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            new WindowsManager().ViewBusiness_toggle(false);
            Stage window = (Stage)  btn_ExportCard.getScene().getWindow();
            window.close();
        }
   // ===============================
    
    // ========== Methods ============
        /**
         * Initialize the window 
         * - Sets the icon 
         * - Disables the label "No_data"
         * - Puts resource bundle to an object.
         * @param rb 
         */
        private void init_window(ResourceBundle bundle){
            default_strings = bundle;
            lbl_nodata.setVisible(false);
            icon_imageview.setImage(new Image(new File("resources/images/menubar/view_businessData.png").toURI().toString())); 
        }
        /**
         * This method will be called only when xml file
         * doesn't exist Disables all the Lables.
         */
        private void clear_window(){
            Label[] lables = {lbl_bName,lbl_bDescription,lbl_lblPhone,
                              lbl_phone2,lbl_Fax,lbl_Address,lbl_City,
                              lbl_TaxReg,lbl_EstablishData,lbl_LastEdit,
                              lbl_Mail};
            btn_ExportPDF.setVisible(false);
            btn_ExportCard.setVisible(false);
            b_logo.setVisible(false);         
                for(Label item:lables){
                    item.setVisible(false);
                }  
        }
        /**
         * Displays a message to inform the user that there is no data 
         * saved.
         * @param bundle 
         */
        private void no_data_message(ResourceBundle bundle){
            lbl_nodata.setVisible(true);
            lbl_nodata.setText(bundle.getString("view_no_data"));
        }
        
        private void enable_components(){
            btn_ExportPDF.setVisible(true);
            btn_ExportCard.setVisible(true);
        }
        
        private void set_logo(){
           FileManager path = new FileManager();
                String[] logo_name = new String[]{"/logo.png","/logo.bmp","/logo.jpg"};
                for (String name : logo_name){
                    File logo = new File(path.getApp_data_business()+name);
                    if (logo.exists()){
                        Image logo_img= new Image(new File(new FileManager().getApp_data_business()+name).toURI().toString());
                        b_logo.setImage(logo_img);
                        break;
                    }
                }
                if (b_logo.getImage() == null){
                    File default_logo = new File("resources/default_img/default.png");
                    Image logo_img= new Image(default_logo.toURI().toString());
                    b_logo.setImage(logo_img);
                }    
        }
        
        private void set_data(ResourceBundle bundle,File business_file){
            BusinessXML_Parser file_reader = new BusinessXML_Parser(business_file);
            business = file_reader.getData();
              lbl_bName.setText(bundle.getString("view_bus_businessname")+"   "+business.getName());
              lbl_bDescription.setText(bundle.getString("view_bus_description")+"   "+business.getDescription());
              lbl_lblPhone.setText(bundle.getString("lbl_phone")+"  "+business.getPhone1());
              lbl_Fax.setText(bundle.getString("lbl_fax")+"  "+business.getFax());
              lbl_Address.setText(bundle.getString("lbl_address")+"  "+business.getAddress());
              lbl_City.setText(bundle.getString("lbl_city")+"  "+business.getCity());
              lbl_TaxReg.setText(bundle.getString("view_bus_taxreg")+"  "+business.getTaxReg());
              lbl_EstablishData.setText(bundle.getString("view_bus_date")+"  "+business.getDate());
              lbl_Mail.setText(bundle.getString("lbl_mail")+"  "+business.getMail());
              SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
              lbl_LastEdit.setText(bundle.getString("view_bus_LastEdit")+"  "+date_format.format(business_file.lastModified()));
        }
    // ===============================
    
    private FileManager workplace;
    private Business business;
    private BusinessData BusinessXML;
    private BusinessPDF export_pdf;
    private ResourceBundle default_strings;
}
