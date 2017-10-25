/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.products;

import erpsystem.entities.product.Product;
import java.sql.SQLException;

public class PRD_Purchase extends PRD_Database{
 
    private final String INSERT_QUERY = "INSERT INTO product(prd_id,prd_name,"
                                      + "prd_description,prd_quantity,prd_prcPrice,prd_profit,"
                                      + "prd_sellPrice,prd_VAT,prd_category) "
                                      + "VALUES (?,?,?,?,?,?,?,?,?);";
    public boolean insert_product(Product input){
        boolean flag = false;
        try{
            Connect();
            prepared_statement = connection.prepareStatement(INSERT_QUERY);
                prepared_statement.setInt(1,0);
                prepared_statement.setString(2,input.getName());
                prepared_statement.setString(3,input.getDescription());
                prepared_statement.setInt(4,input.getQuantity());
                prepared_statement.setFloat(5, input.getPurchasePrice() );
                prepared_statement.setFloat(6, input.getProfit());
                prepared_statement.setFloat(7, input.getSellPrice());
                prepared_statement.setInt(8, input.getVAT());
                prepared_statement.setString(9, input.getCategory());
            flag = prepared_statement.execute();
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return flag;
    }
    
    public int get_productID(){
        int prd_id = 0;
        try{
            Connect();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT MAX(prd_id) FROM product");
            while(rs.next()){
                prd_id = rs.getInt(1);
             }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return prd_id;
    }
    
}
