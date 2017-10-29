/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.finance;

import java.sql.SQLException;

public class FinanceSale extends FinanceDatabase{
    public void change_status(int SaleID){
        String UpdateSale = "UPDATE sales SET sal_paymentMethod = ? WHERE sale_id = ?";
        String SelectSalePrice = "SELECT sal_finalPrice FROM sales WHERE sale_id = "+SaleID;
        String UpdateFinance = "UPDATE finance "
                             + "SET fin_in_credit = fin_in_credit - ?,"
                             + "fin_in_debit = fin_in_debit + ?";
        try{
            Connect();
                // Update Sales Table
                prepared_statement = connection.prepareStatement(UpdateSale);
                    prepared_statement.setString(1, "debit");
                    prepared_statement.setInt(2, SaleID);
                prepared_statement.executeUpdate();
                prepared_statement.close();
                float sale_price = 0;
                   statement = connection.createStatement();
                   rs = statement.executeQuery(SelectSalePrice);
                   while(rs.next()){
                       sale_price = rs.getFloat(1);
                   }
                   statement.close();
                   rs.close();
                // Update Finance Table
                prepared_statement = connection.prepareStatement(UpdateFinance);
                    prepared_statement.setFloat(1, sale_price);
                    prepared_statement.setFloat(2, sale_price);
                prepared_statement.executeUpdate();
            Disconnect();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
