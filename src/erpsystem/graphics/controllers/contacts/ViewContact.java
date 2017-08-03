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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    
    public Map clicked_row;
    private ResourceBundle language_strings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        language_strings = rb;
        if (init_window()){
          //  SetWindowData(); 
            
        }
    }    

    @FXML
    private void btn_Close_Action(ActionEvent event) {
        Stage this_window = (Stage) btn_Close.getScene().getWindow();
        this_window.close();
    }
    
    private boolean init_window(){
        contact_icon.setImage(new Image(new File("").toURI().toString()));
        return true;
    }

    public void set_window(Map input){
        Label[] labels = {lbl_firstname,lbl_lastname,lbl_country,
                          lbl_zipcode,lbl_sex,lbl_address,lbl_city,
                          lbl_greekState,lbl_phone1,lbl_phone2,lbl_mail,
                          lbl_comments,lbl_website};
        String[] default_strings = {"gnr_lbl_firstname","gnr_lbl_lastname","gnr_lbl_country",
                                    "gnr_lbl_zipcode","gnr_lbl_sex","gnr_lbl_address","gnr_lbl_city",
                                    "gnr_lbl_state","gnr_lbl_phone1","gnr_lbl_phone2",
                                    "gnr_lbl_mail","gnr_lbl_comments","gnr_lbl_website"};
        String[] map_values = {"firstname","lastname","country","zipcode",
                               "sex","address","city","greek_state","phone1",
                                "phone2","...this is a mail....","comments","website"};
        int index = 0;
        for (Label item:labels){
            item.setText(language_strings.getString(default_strings[index])+" "+input.get(map_values[index]));
            index++;
        }
       lbl_phone1_type.setText(String.valueOf(input.get("phone1_type")));
       lbl_phone2_type.setText(String.valueOf(input.get("phone2_type")));
       lbl_import_date.setText(String.valueOf(input.get("import_data")));
    }
}
