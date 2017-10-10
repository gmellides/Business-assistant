/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.sales;

import erpsystem.entities.actions.Sale;
import erpsystem.util.datetime.DateTimeProvider;
import java.sql.SQLException;
import java.sql.Timestamp;

public class NewSaleDatabase extends SalesDatabase{
    /**
      * Insert a sale to database.
      * First on sale table it takes the customer id, 
      * payment method, final price and current date and time 
      * after this inserts products to sal_prd table.
      * @param obj 
      */
    public void insert_sale(Sale obj){
            String QUERY = "INSERT INTO sales(sale_id,cst_id,sal_paymentMethod,sal_finalPrice,sal_date) VALUES (?,?,?,?,?)";
            try{
                Connect();
                prepared_statement = connection.prepareStatement(QUERY);
                    prepared_statement.setInt(1, 0);
                    prepared_statement.setInt(2, obj.getCustomerID());
                    prepared_statement.setString(3, obj.getPaymentMethod());
                    prepared_statement.setFloat(4, obj.getFinal_Price());
                    prepared_statement.setTimestamp(5, Timestamp.valueOf(new DateTimeProvider().GetTimestamp()));
                prepared_statement.execute();
                add_products(obj);
                update_finance(obj);
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        public int get_id(){
            int sales = 0;
             try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT MAX(sale_id) FROM sales");
                while(rs.next()){
                    sales = rs.getInt(1);
                }
             }catch(SQLException e){
                 e.printStackTrace();
             }
            return sales;
        }
        /**
      * products to sal_prd table.
      * @param obj 
      */        
        private void add_products(Sale obj){
        String QUERY = "INSERT INTO sal_prd(sale_id,prd_id,sal_quantity,sal_price) VALUES (?,?,?,?)";
        try{
            prepared_statement = connection.prepareStatement(QUERY);
                int saleId = get_id();
                int[] prds = obj.getProducts();
                int[] quantity = obj.getQuantity();
                float[] Prices = obj.getPrice();
                for (int i=0; i<prds.length; i++){
                    prepared_statement.setInt(1, saleId);
                    prepared_statement.setInt(2, prds[i]);
                    prepared_statement.setInt(3, quantity[i]);
                    prepared_statement.setFloat(4, Prices[i]);
                    prepared_statement.execute();
                }
            update_products(obj);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
        private void update_products(Sale obj){
        String QUERY = "UPDATE product SET prd_quantity = prd_quantity - ? WHERE prd_id = ?";
        try{
            prepared_statement = connection.prepareStatement(QUERY);
            int[] prds = obj.getProducts();
            int[] quantity = obj.getQuantity();
            for (int i=0; i<prds.length; i++){
                prepared_statement.setInt(1, quantity[i]);
                prepared_statement.setInt(2, prds[i]);
                prepared_statement.execute();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }        
    }
    private void update_finance(Sale obj){
        String DebitQuery = "UPDATE finance SET fin_incomes = fin_incomes + ?, fin_in_debit = fin_in_debit + ?;";
        String CreditQuery = "UPDATE finance SET fin_incomes = fin_incomes + ?, fin_in_credit = fin_in_credit + ?;";
        try{
            if(obj.getPaymentMethod().equals("credit")){
                prepared_statement = connection.prepareStatement(CreditQuery);
            }else{
                prepared_statement = connection.prepareStatement(DebitQuery);   
            }
            int[] prds = obj.getProducts();
            int[] quantity = obj.getQuantity();
            for (int i=0; i<prds.length; i++){
                prepared_statement.setFloat(1, obj.getFinal_Price());
                prepared_statement.setFloat(2, obj.getFinal_Price());
                prepared_statement.execute();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
     }
    // income , income_credit , income_debit, outcome
    private float[] sum_incomes(){
        float[] value = null;
        try{
            Connect();
            statement = connection.createStatement();
            rs = statement.executeQuery("SELECT SUM(sal_finalPrice) FROM sales;");
            while(rs.next()){
                value[0] = rs.getFloat(1);
            }
            rs = statement.executeQuery("SELECT SUM(sal_finalPrice) FROM sales WHERE sal_paymentMethod = 'credit';");
            while(rs.next()){
                value[1] = rs.getFloat(1);
            }
            rs = statement.executeQuery("SELECT SUM(sal_finalPrice) FROM sales WHERE sal_paymentMethod = 'debit';");
            while(rs.next()){
                value[2] = rs.getFloat(1);
            }
               rs = statement.executeQuery("SELECT SUM() FROM sales;");
            while(rs.next()){
                value[4] = rs.getFloat(1);
            }
            rs = statement.executeQuery("SELECT SUM() FROM sales WHERE sal_paymentMethod = 'credit';");
            while(rs.next()){
                value[5] = rs.getFloat(1);
            }
            rs = statement.executeQuery("SELECT SUM() FROM sales WHERE sal_paymentMethod = 'debit';");
            while(rs.next()){
                value[6] = rs.getFloat(1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return value;
    }

}
