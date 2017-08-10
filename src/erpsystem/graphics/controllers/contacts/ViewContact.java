/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.contacts;

import java.io.File;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewContact implements Initializable {

    @FXML
    private Button btn_Close;
    @FXML
    private ImageView contact_icon;
    @FXML
    private Label lbl_firstname,lbl_lastname,lbl_country,
                  lbl_zipcode,lbl_sex,lbl_address,lbl_city,
                  lbl_greekState,lbl_phone1,lbl_phone2,lbl_mail,
                  lbl_comments,lbl_website,lbl_phone1_type,lbl_phone2_type,lbl_import_date;
    @FXML
    private Pane editPanel,viewPanel;
    @FXML
    private TextField txt_edt_firstname,txt_edt_lastname,txt_edt_address,txt_edt_phone1,
                      txt_edt_phone2,txt_edt_zipcode,txt_edt_mail,txt_edt_website;
    
    @FXML
    private ComboBox<String> cmb_edt_sex,cmb_edt_country,cmb_edt_state,cmb_edt_city,
                             cmb_edt_phone2Type,cmb_edt_phone1Type;
    @FXML
    private TextArea txt_edt_comments;
    @FXML
    private ToggleButton btn_editToggle;
    
    public Map clicked_row;
    private ResourceBundle default_strings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
    }
    /**
     * This Close current window 
     * @param event 
     */
    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage this_window = (Stage) btn_Close.getScene().getWindow();
        this_window.close();
    }
    /**
     * Edit and View panel toggle. This action for edit button changes the 
     * panel visibility from View Panel (a panel that the user can only view 
     * a specific contact) to Edit Panel (a panel that he can edit one or more 
     * contact fields or delete selected contact). 
     * @param event 
     */
    @FXML
    private void btn_Edit_Action(ActionEvent event) {
        boolean flag = btn_editToggle.isSelected();
        if (flag){
            viewPanel.setVisible(false);
            editPanel.setVisible(true);
            btn_editToggle.setText(default_strings.getString("gnr_btn_view"));
        }else{
            viewPanel.setVisible(true);
            editPanel.setVisible(false);
            btn_editToggle.setText(default_strings.getString("gnr_btn_edit"));
        }
    }
    /**
     * This method is public and will be called from double click on 
     * a table row on SearchView.fxml. this method puts data to View Panel 
     * and Edit Panel. This method is something like a window initializer 
     * it fills the view and edit panel also sets the icon and keep selected 
     * row to a map for reuse.
     * @param input 
     */
    public void set_window(Map input){
        contact_icon.setImage(new Image(new File("resources/images/contacts/view_contact.png").toURI().toString()));
        clicked_row = input;
        set_viewPanel();
        set_editPanel();
    }
    /**
     * Fills the Edit Panel with data from Map object with name
     * 'clicked_row'
     */
    private void set_editPanel(){
        TextField[] fields = {txt_edt_firstname,txt_edt_lastname,txt_edt_address,txt_edt_phone1,
                              txt_edt_phone2,txt_edt_zipcode,txt_edt_mail,txt_edt_website};
        String[] map_values = {"firstname","lastname","address","phone1",
                               "phone2","zipcode","mail","website"};
        int index = 0;
        for (TextField item : fields){
            item.setText(String.valueOf(clicked_row.get(map_values[index])));
            index++;
        }
        txt_edt_comments.setText(String.valueOf(clicked_row.get("comments")));
        cmb_edt_sex.getSelectionModel().select(String.valueOf(clicked_row.get("sex")));
        cmb_edt_country.getSelectionModel().select(String.valueOf(clicked_row.get("country")));
        cmb_edt_state.getSelectionModel().select(String.valueOf(clicked_row.get("greek_state")));
        cmb_edt_city.getSelectionModel().select(String.valueOf(clicked_row.get("city")));
        cmb_edt_phone1Type.getSelectionModel().select(String.valueOf(clicked_row.get("phone1_type")));
        cmb_edt_phone2Type.getSelectionModel().select(String.valueOf(clicked_row.get("phone2_type")));
    }
    /**
     * Fills the View Panel with data from Map object with name
     * 'clicked_row'
     */
    private void set_viewPanel(){
        Label[] labels = {lbl_firstname,lbl_lastname,lbl_country,
                          lbl_zipcode,lbl_sex,lbl_address,lbl_city,
                          lbl_greekState,lbl_phone1,lbl_phone2,lbl_mail,
                          lbl_comments,lbl_website};
        String[] stringsId = {"gnr_lbl_firstname","gnr_lbl_lastname","gnr_lbl_country",
                              "gnr_lbl_zipcode","gnr_lbl_sex","gnr_lbl_address","gnr_lbl_city",
                              "gnr_lbl_state","gnr_lbl_phone1","gnr_lbl_phone2",
                              "gnr_lbl_mail","gnr_lbl_comments","gnr_lbl_website",};
        String[] map_values = {"firstname","lastname","country","zipcode",
                               "sex","address","city","greek_state","phone1",
                               "phone2","mail","comments","website"};
        int index = 0;
        for (Label item:labels){
            item.setText(default_strings.getString(stringsId[index])+" "+clicked_row.get(map_values[index]));
            index++;
        }
       lbl_phone1_type.setText(String.valueOf(clicked_row.get("phone1_type")));
       lbl_phone2_type.setText(String.valueOf(clicked_row.get("phone2_type")));
       lbl_import_date.setText(String.valueOf(clicked_row.get("import_data")));
    }
}
