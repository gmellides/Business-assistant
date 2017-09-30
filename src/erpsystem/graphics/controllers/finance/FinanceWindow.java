/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.finance;

import erpsystem.database.finance.FinanceDatabase;
import erpsystem.database.purchases.PRC_Database;
import erpsystem.database.sales.SalesDatabase;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
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
    private PieChart IncomeOutcomeChart,IncomeChart,OutcomeChart;
    @FXML
    private ToggleButton btn_basicInfo,btn_IncomePanel,btn_Outcomes;
    @FXML
    private Label lbl_sales,lbl_purchases;
    
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
       btn_basicInfo.setSelected(true);
       btn_IncomePanel.setSelected(false);
       btn_Outcomes.setSelected(false);
       basic_panelInit();
       income_panelInit();
       outcome_panelInit();
    }    
    
    private void close_window(){
        Stage window = (Stage) img_money.getScene().getWindow();
        window.close();
    }

    @FXML
    private void btn_BasicInfo_Action(ActionEvent event) {
       btn_basicInfo.setSelected(true);
       btn_IncomePanel.setSelected(false);
       btn_Outcomes.setSelected(false);
       MainPanel.setVisible(true);
       IncomePanel.setVisible(false);
       OutcomePanel.setVisible(false);
    }
    @FXML
    private void btn_Income_Panel(ActionEvent event) {
       btn_basicInfo.setSelected(false);
       btn_IncomePanel.setSelected(true);
       btn_Outcomes.setSelected(false);
       MainPanel.setVisible(false);
       IncomePanel.setVisible(true);
       OutcomePanel.setVisible(false);
    }
    @FXML
    private void btn_Outcomes_Action(ActionEvent event) {
       btn_basicInfo.setSelected(false);
       btn_IncomePanel.setSelected(false);
       btn_Outcomes.setSelected(true);
       MainPanel.setVisible(false);
       IncomePanel.setVisible(false);
       OutcomePanel.setVisible(true);
    }
    
    
    private void basic_panelInit(){
        lbl_sales.setText(String.valueOf(new SalesDatabase().count_sales()));
        lbl_purchases.setText(String.valueOf(new PRC_Database().count_purchases()));
        // Pie Init 
        PieChart.Data income = new PieChart.Data(default_strings.getString("btn_Incomes"), new FinanceDatabase().get_incomes());
        PieChart.Data outcome = new PieChart.Data(default_strings.getString("btn_Outcomes"), new FinanceDatabase().get_oucomes());
        IncomeOutcomeChart.getData().add(income);
        IncomeOutcomeChart.getData().add(outcome);
    }
    private void income_panelInit(){
        // Pie Init
        PieChart.Data credit = new PieChart.Data(default_strings.getString("rbtn_credit"), new FinanceDatabase().get_incomes_credit());
        PieChart.Data debit = new PieChart.Data(default_strings.getString("rbtn_Debit"), new FinanceDatabase().get_incomes_debit());
        IncomeChart.getData().add(credit);
        IncomeChart.getData().add(debit);
    }
    private void outcome_panelInit(){
        // Pie Init
        PieChart.Data credit = new PieChart.Data(default_strings.getString("rbtn_credit"), new FinanceDatabase().get_outcomes_credit());
        PieChart.Data debit = new PieChart.Data(default_strings.getString("rbtn_Debit"), new FinanceDatabase().get_outcomes_debit());
        OutcomeChart.getData().add(credit);
        OutcomeChart.getData().add(debit);
    }
}
