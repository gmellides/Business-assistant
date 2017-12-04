/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.sales;

import erpsystem.database.finance.FinanceDatabase;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SalesManager implements Initializable {

    @FXML
    private Button btn_Close;
    
    private ResourceBundle default_strings;
    @FXML
    private ImageView saleManager_img;
    @FXML
    private Label lbl_individual,lbl_companies;

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
                           default_strings.getString("btn_showSales"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        @FXML
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
    
    private void init_window(){
        saleManager_img.setImage(new Image(new File("resources/images/sales/sales.png").toURI().toString()));
        int[] data = new FinanceDatabase().count_sales_customers();
            lbl_individual.setText(String.valueOf(data[0]));
            lbl_companies.setText(String.valueOf(data[1]));
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
