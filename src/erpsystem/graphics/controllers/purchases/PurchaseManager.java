/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.purchases;

import erpsystem.util.system.Dimension;
import java.io.File;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PurchaseManager implements Initializable {

    @FXML
    private Button btn_ExportPDF,btn_BackUp;
    @FXML
    private ImageView prc_mgr_Imageview;
    
    private ResourceBundle default_strings;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        default_strings = rb;
        prc_mgr_Imageview.setImage(new Image(new File("resources/images/purchases/purchase_manager.png").toURI().toString()));
    }

        @FXML
        private void btn_newPurchace_Action(ActionEvent event) {
            try{
                OpenWindow("purchases/NewPurchase.fxml",
                           new Dimension().NewPurchase_window_width,
                           new Dimension().NewPurchase_window_height,
                           default_strings.getString("bnt_newPurchase"));
            }catch(IOException e){
                e.printStackTrace();
            }
            close_window();
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
        @FXML
        private void btn_ShowPurchases_Action(ActionEvent event) {
            try{
                OpenWindow("purchases/ViewPurchases.fxml",
                            new Dimension().SearchView_window_width,
                            new Dimension().SearchView_window_height,
                            default_strings.getString("btn_showPurchases"));
            }catch(IOException e){
                e.printStackTrace();
            }
            close_window();
        }
    private void close_window(){
        Stage window = (Stage) btn_BackUp.getScene().getWindow();
        window.close();
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
                    stage.close();
                    if (!WindowPath.equals("purchases/PurchaseManager.fxml")){
                        try{
                            OpenWindow("purchases/PurchaseManager.fxml",
                                       new Dimension().Manager_window_width,
                                       new Dimension().Manager_window_height,
                                       default_strings.getString("window_customer_manager"));
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }   
                }
            });
         stage.setTitle(WindowName);
         stage.setScene(scene);
         stage.setResizable(false);
         stage.getIcons().add(new Image(getClass().getResource("/logo/icon.png").toExternalForm()));
         stage.show();
    } 

}
