/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.purchases;

import erpsystem.database.products.PRD_Purchase;
import erpsystem.entities.actions.Purchase;
import erpsystem.entities.product.Product;

public class PRC_NewPurchase extends PRC_Database{
    
    private final String INSERT_QUERY = "INSERT INTO purchases(prc_id,spl_id,"
                                      + "prd_id,prc_price,prc_paymentMethod,"
                                      + "prd_date) "
                                      + "VALUES(?,?,?,?,?,?);";
    
    public void insert_purchase(Purchase prc,
                                Product prd){
        new PRD_Purchase().insert_product(prd);
        int prd_id = new PRD_Purchase().get_productID();
        
    }
    
}
