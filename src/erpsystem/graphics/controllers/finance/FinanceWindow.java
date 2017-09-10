/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.finance;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FinanceWindow implements Initializable {

    @FXML
    private ImageView img_money;
    @FXML
    private Pane MainPanel,IncomePanel,OutcomePanel;
    @FXML
    private PieChart IncomeOutcomeChart;
    @FXML
    private PieChart SalesPurchacesChart;
    @FXML
    private PieChart IncomeTaxesChart;
    
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
        private void btn_Close_Action(ActionEvent event) {
            close_window();
        }
        
    private void init_window(){
       img_money.setImage(new Image(new File("resources/images/finance/money.png").toURI().toString()));
       Pie_Init();
    }    
    
    private void Pie_Init(){
       // Inocome-OutCome Pie
       PieChart.Data one = new PieChart.Data(default_strings.getString("btn_Incomes"), 90);
       PieChart.Data two = new PieChart.Data(default_strings.getString("btn_Outcomes"), 10);
       
       PieChart.Data three = new PieChart.Data(default_strings.getString("btn_Outcomes"), 120);
       IncomeOutcomeChart.getData().add(one);
       IncomeOutcomeChart.getData().add(two);
       IncomeOutcomeChart.getData().add(three);
       // Sales-Purchaces Pie
       
       // Income-Taxes Pie
       
    }
    private void close_window(){
        Stage window = (Stage) img_money.getScene().getWindow();
        window.close();
    }
}