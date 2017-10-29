/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.purchases;

import erpsystem.entities.actions.Purchase;
import erpsystem.util.datetime.DateTimeProvider;
import java.sql.SQLException;
import java.sql.Timestamp;

public class PRC_NewPurchase extends PRC_Database{
    
    private final String INSERT_QUERY = "INSERT INTO purchases(prc_id,spl_id,"
                                      + "prd_id,prc_price,prc_paymentMethod,"
                                      + "prc_date) "
                                      + "VALUES(?,?,?,?,?,?);";
    
    public boolean insert_purchase(Purchase prc){
        boolean flag = false;
            try{
                Connect();
                prepared_statement = connection.prepareStatement(INSERT_QUERY);
                    prepared_statement.setInt(1,0);
                    prepared_statement.setInt(2,prc.getSupplierID());
                    prepared_statement.setInt(3,prc.getProductID());
                    prepared_statement.setFloat(4,prc.getPurchasePrice());
                    prepared_statement.setString(5, prc.getPaymentMethod());      
                    prepared_statement.setTimestamp(6, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                flag = prepared_statement.execute();
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        return flag;
    }
    
}
