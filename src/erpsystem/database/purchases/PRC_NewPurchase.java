/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.purchases;

import erpsystem.database.finance.FinancePurchase;
import erpsystem.entities.actions.Purchase;
import erpsystem.util.datetime.DateTimeProvider;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PRC_NewPurchase extends PRC_Database{
    
    private final String INSERT_QUERY = "INSERT INTO purchases(prc_id,spl_id,"
                                      + "prd_id,prc_quantity,prc_finalPrice,prc_paymentMethod,"
                                      + "prc_date) "
                                      + "VALUES(?,?,?,?,?,?,?);";
    
    public boolean insert_purchase(Purchase prc){
        boolean flag = false;
            try{
                Connect();
                prepared_statement = connection.prepareStatement(INSERT_QUERY);
                    prepared_statement.setInt(1,0);
                    prepared_statement.setInt(2,prc.getSupplierID());
                    prepared_statement.setInt(3,prc.getProductID());
                    prepared_statement.setInt(4, prc.getPrc_quantity());
                    prepared_statement.setFloat(5,prc.getPurchasePrice());
                    prepared_statement.setString(6, prc.getPaymentMethod());      
                    prepared_statement.setTimestamp(7, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                flag = prepared_statement.execute();
                update_outcomes(prc);
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        return flag;
    }
    
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
