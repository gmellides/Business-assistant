/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.menubar;

import erpsystem.entities.business.Business;
import erpsystem.util.system.FileManager;
import erpsystem.util.xml.read.BusinessXML_Parser;
import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lbl_nodata.setVisible(false);
        set_background_and_icon();
        workplace = new FileManager();
        File xml_file = new File(workplace.getApp_data_business()+"/business_data.xml");
        if(xml_file.exists()){
            set_logo();
            set_data(rb,xml_file);
          //  enable_components();
        }else{
            clear_window();  
            no_data(rb);
            set_logo();
        }   
    }
    
    // ===== FXML Buttons Action =====
    @FXML
    private void btnExportCard_Action(ActionEvent event) {
        
    }

    @FXML
    private void btnExportPDF_Action(ActionEvent event) {
        
    }
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage window = (Stage)  btn_ExportCard.getScene().getWindow();
        window.close();
    }
   // ===============================
    
    // ========== Methods ============
    /**
     * This method will be called only when xml file
     * doesn't exist 
     */
    public void clear_window(){
        b_logo.setVisible(false);
        graphic_line.setVisible(false);
        lbl_bName.setVisible(false);
          lbl_bDescription.setVisible(false);
          lbl_lblPhone.setVisible(false);
          lbl_Fax.setVisible(false);
          lbl_Address.setVisible(false);
          lbl_City.setVisible(false);
          lbl_TaxReg.setVisible(false);
          lbl_EstablishData.setVisible(false);
          lbl_LastEdit.setVisible(false);
        btn_ExportPDF.setVisible(false);
        btn_ExportCard.setVisible(false);
    }
    public void no_data(ResourceBundle bundle){
        lbl_nodata.setVisible(true);
        lbl_nodata.setText(bundle.getString("view_no_data"));
    }
    public void enable_components(){
        btn_ExportPDF.setVisible(true);
        btn_ExportCard.setVisible(true);
    }
    public void set_logo(){
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
    public void set_background_and_icon(){
        Image icon = new Image(new File("resources/images/menubar/view_businessData.png").toURI().toString());
        icon_imageview.setImage(icon); 
        background_pane.setStyle("-fx-background-color: #FFFFFF;");
    }
    public void set_data(ResourceBundle bundle,File xml_input){
        BusinessXML_Parser file_reader = new BusinessXML_Parser();
        b_data = file_reader.getData();
          lbl_bName.setText(bundle.getString("view_bus_businessname")+"   "+b_data.getBusiness_Name());
          lbl_bDescription.setText(bundle.getString("view_bus_description")+"   "+b_data.getBusiness_Description());
          lbl_lblPhone.setText(bundle.getString("view_bus_phone")+"  "+b_data.getBusiness_Phone());
          lbl_Fax.setText(bundle.getString("view_bus_fax")+"  "+b_data.getBusiness_Fax());
          lbl_Address.setText(bundle.getString("view_bus_address")+"  "+b_data.getBusiness_Address());
          lbl_City.setText(bundle.getString("view_bus_city")+"  "+b_data.getBusiness_City());
          lbl_TaxReg.setText(bundle.getString("view_bus_taxreg")+"  "+b_data.getBusiness_TaxReg());
          lbl_EstablishData.setText(bundle.getString("view_bus_date")+"  "+b_data.getBusiness_Date());
          SimpleDateFormat date_format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
          lbl_LastEdit.setText(bundle.getString("view_bus_LastEdit")+"  "+date_format.format(xml_input.lastModified()));
    }
    // ===============================
    
    private FileManager workplace;
    private Business b_data;
}