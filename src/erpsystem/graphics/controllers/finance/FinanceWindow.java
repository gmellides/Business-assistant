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
    @FXML
    private Label lbl_incomes,lbl_outcomes,lbl_incomeP_income,lbl_incomeP_debit,
    lbl_incomeP_credit,lbl_outcomeP_credit,lbl_outcomeP_debit,lbl_outcomeP_outcomes,
    lbl_countSal_debit,lbl_countSal_credit ;
    
    float incomes,outcomes;
    @FXML
    private Label lbl_sales_ind;
    @FXML
    private Label lbl_sales_cmp;
 

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
       incomes = new FinanceDatabase().get_incomes();
       outcomes = new FinanceDatabase().get_oucomes();
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
        PieChart.Data income = new PieChart.Data(default_strings.getString("btn_Incomes"), incomes);
        PieChart.Data outcome = new PieChart.Data(default_strings.getString("btn_Outcomes"), outcomes);
        IncomeOutcomeChart.getData().add(income);
        IncomeOutcomeChart.getData().add(outcome);
        lbl_incomes.setText(String.valueOf(incomes+"€"));
        lbl_outcomes.setText(String.valueOf(outcomes+"€"));
    }
    private void income_panelInit(){
        // Pie Init
        float credit_val = new FinanceDatabase().get_incomes_credit();
        float debit_val = new FinanceDatabase().get_incomes_debit();
        PieChart.Data credit = new PieChart.Data(default_strings.getString("rbtn_credit"), credit_val);
        PieChart.Data debit = new PieChart.Data(default_strings.getString("rbtn_Debit"), debit_val);
        IncomeChart.getData().add(credit);
        IncomeChart.getData().add(debit);
        lbl_incomeP_income.setText(String.valueOf(incomes)+"€");
        lbl_incomeP_debit.setText(String.valueOf(credit_val)+"€");
        lbl_incomeP_credit.setText(String.valueOf(debit_val)+"€");
        int[] data = new FinanceDatabase().count_sales_paymentMethod();
        lbl_countSal_debit.setText(String.valueOf(data[0]));
        lbl_countSal_credit.setText(String.valueOf(data[1]));
        data = new FinanceDatabase().count_sales_customers();
        lbl_sales_ind.setText(String.valueOf(data[0]));
        lbl_sales_cmp.setText(String.valueOf(data[1]));
    }
    private void outcome_panelInit(){
        // Pie Init
        float credit_val =  new FinanceDatabase().get_outcomes_credit();
        float debit_val = new FinanceDatabase().get_outcomes_debit();
        PieChart.Data credit = new PieChart.Data(default_strings.getString("rbtn_credit"), credit_val);
        PieChart.Data debit = new PieChart.Data(default_strings.getString("rbtn_Debit"), debit_val);
        OutcomeChart.getData().add(credit);
        OutcomeChart.getData().add(debit);
        lbl_outcomeP_outcomes.setText(String.valueOf(outcomes+"€"));
        lbl_outcomeP_credit.setText(String.valueOf(credit_val)+"€");
        lbl_outcomeP_debit.setText(String.valueOf(debit_val)+"€");
    }
}
