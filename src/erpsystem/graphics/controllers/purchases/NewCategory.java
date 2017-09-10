/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.purchases;

import erpsystem.util.xml.write.ProductCategory;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewCategory implements Initializable {

    @FXML
    private TextField txt_category;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btn_saveCategory_Action(ActionEvent event) {
        new ProductCategory().Update_xml(txt_category.getText());
        close_window();
    }
    private void close_window(){
        String newcat = txt_category.getText();
        new NewPurchase().assign_combobox(newcat);
        Stage win = (Stage) txt_category.getScene().getWindow();
        win.close();
    }
}
