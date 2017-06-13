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
    private Label lbl_firstname;
    @FXML
    private Label lbl_lastname;
    @FXML
    private Label lbl_country;
    @FXML
    private Label lbl_zipcode;
    @FXML
    private Label lbl_sex;
    @FXML
    private Label lbl_address;
    @FXML
    private Label lbl_city;
    @FXML
    private Label lbl_greekState;
    @FXML
    private Label lbl_phone1;
    @FXML
    private Label lbl_phone2;
    @FXML
    private Label lbl_mail;
    @FXML
    private Label lbl_phone1_type;
    @FXML
    private Label lbl_phone2_type;
    @FXML
    private Label lbl_comments;
    @FXML
    private Label lbl_website;
    @FXML
    private Label lbl_import_date;
    
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
    
    //lbl_phone1_type , lbl_phone2_type , lbl_import_date

    
    public void set_window(Map input){
       
        Label[] labels = {lbl_firstname,lbl_lastname,lbl_country,
                          lbl_zipcode,lbl_sex,lbl_address,lbl_city,
                          lbl_greekState,lbl_phone1,lbl_phone2,lbl_mail,
                          lbl_comments,lbl_website};
        String[] default_strings = {"lbl_firstname","lbl_lastname","lbl_country",
                                    "lbl_zipcode","lbl_sex","lbl_address","lbl_city",
                                    "lbl_state","lbl_phone1","lbl_phone2",
                                    "lbl_mail","lbl_comments","lbl_website"};
        String[] map_values = {"firstname","lastname","country","zipcode",
                               "sex","address","city","greek_state","phone1",
                                "phone2","this is a mail","comments","website"};
       int index = 0;
        for (Label item:labels){
            item.setText(language_strings.getString(default_strings[index])+" "+input.get(map_values[index]));
            index++;
        }
       
    }
    
    
    public Map clicked_row;
}
