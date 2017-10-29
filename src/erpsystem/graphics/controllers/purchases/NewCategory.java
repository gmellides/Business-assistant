/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.purchases;

import erpsystem.util.system.Dimension;
import erpsystem.util.xml.write.ProductCategory;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NewCategory implements Initializable {

    @FXML
    private TextField txt_category;
    
    ResourceBundle default_strings;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
    }    

    @FXML
    private void btn_saveCategory_Action(ActionEvent event) {
        new ProductCategory().Update_xml(txt_category.getText());
        try{
            close_window();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    private void close_window() throws IOException{
        FXMLLoader fxml_loader = new FXMLLoader();
        fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
        Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/purchases/NewPurchase.fxml").openStream());
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setHeight(new Dimension().NewPurchase_window_height);
        stage.setWidth(new Dimension().NewPurchase_window_width);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
            //  window_check.toggle_window(WindowPath);
                stage.close();
            }
        });
        stage.setTitle(default_strings.getString("edit_admin_data"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
        stage.show();
      
        Stage win = (Stage) txt_category.getScene().getWindow();
        win.close();
    }
}
