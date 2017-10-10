/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.sales;

import erpsystem.util.system.Dimension;
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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SalesManager implements Initializable {

    @FXML
    private Button btn_Close;
    
    private ResourceBundle default_strings;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        init_window();
    } 
        @FXML
        private void btn_NewSale_Action(ActionEvent event) {
            try{
                OpenWindow("sales/NewSale.fxml",
                           new Dimension().NewSale_window_width,
                           new Dimension().NewSale_window_height,
                           default_strings.getString("window_newSale"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        @FXML
        private void btn_ShowSales_Action(ActionEvent event) {
            try{
                OpenWindow("sales/ViewSales.fxml",
                           new Dimension().SearchView_window_width,
                           new Dimension().SearchView_window_height,
                           default_strings.getString("window_newSale"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
    
    private void init_window(){
        
    }
    private void close_window(){
        Stage win = (Stage) btn_Close.getScene().getWindow();
        win.close();
    }
    private void OpenWindow(String WindowPath,
                                int Width,
                                int Height,
                                String WindowName) throws IOException{
        FXMLLoader fxml_loader = new FXMLLoader();
            fxml_loader.setResources(ResourceBundle.getBundle("erpsystem.language.strings_gr"));
            Parent root = fxml_loader.load(getClass().getResource("/erpsystem/graphics/windows/"+WindowPath).openStream());
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setHeight(Height);
            stage.setWidth(Width);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent we) {
             //     window_check.toggle_window(WindowPath);
                    stage.close();
                }
                });
            stage.setTitle(WindowName);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
            stage.show();         
        }
}
