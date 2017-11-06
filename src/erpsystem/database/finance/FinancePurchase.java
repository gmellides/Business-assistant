/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.finance;

import erpsystem.entities.actions.Purchase;
import java.sql.SQLException;

public class FinancePurchase extends FinanceDatabase{
   
    public void update_outcomes(Purchase obj){
        String DebitQuery = "UPDATE finance SET fin_outcomes = fin_outcomes + ?, fin_out_debit = fin_out_debit + ?;";
        String CreditQuery = "UPDATE finance SET fin_outcomes = fin_outcomes + ?, fin_out_credit = fin_out_credit + ?;";
         try{
            if(obj.getPaymentMethod().equals("credit")){
                prepared_statement = connection.prepareStatement(CreditQuery);
            }else{
                prepared_statement = connection.prepareStatement(DebitQuery);   
            }
                prepared_statement.setFloat(1, obj.getPurchasePrice());
                prepared_statement.setFloat(2, obj.getPurchasePrice());
                prepared_statement.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
