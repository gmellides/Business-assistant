/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.database.finance;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FinanceDatabase {
    private final String database_driver = "jdbc:ucanaccess://";
    private final String database_path = new File("databases/app_data.accdb").getAbsolutePath();
    private final String Username = null;
    private final String Password = null;

    protected Connection connection;
    protected Statement statement;
    protected ResultSet rs;
    protected PreparedStatement prepared_statement;
    
    protected void Connect(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            connection = DriverManager.getConnection(database_driver+database_path);
        }catch(ClassNotFoundException|SQLException e){
            e.printStackTrace();
        }
    }
        public float get_oucomes(){
            float outcomes = 0;
                try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT fin_outcomes FROM finance;");
                while (rs.next()){
                    outcomes = rs.getInt(1);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return outcomes;
        }
        public float get_incomes(){
            float incomes = 0;
                try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT fin_incomes FROM finance;");
                while (rs.next()){
                    incomes = rs.getInt(1);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return incomes;
        }
        public float get_outcomes_credit(){
            float outcomes = 0;
                try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT fin_out_credit FROM finance;");
                while (rs.next()){
                    outcomes = rs.getInt(1);
                }
                
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return outcomes;
        }
        public float get_incomes_credit(){
            float incomes = 0;
                try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT fin_in_credit FROM finance;");
                while (rs.next()){
                    incomes = rs.getInt(1);
                }
                
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return incomes;
        }
        public float get_outcomes_debit(){
            float outcomes = 0;
                try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT fin_out_debit FROM finance;");
                while (rs.next()){
                    outcomes = rs.getInt(1);
                }
                
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return outcomes;
        }
        public float get_incomes_debit(){
            float incomes = 0;
                try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT fin_in_debit FROM finance;");
                while (rs.next()){
                    incomes = rs.getInt(1);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            return incomes;
        }
        public int[] count_sales_paymentMethod(){
            int cred = 0;
            int deb = 0;
                try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT COUNT(*) FROM sales WHERE sal_paymentMethod = 'debit';");
                while (rs.next()){
                    deb = rs.getInt(1);
                }
                rs = statement.executeQuery("SELECT COUNT(*) FROM sales WHERE sal_paymentMethod = 'credit';");
                while (rs.next()){
                    cred = rs.getInt(1);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            int[] data = {deb,cred};
            return data;
        }
        public int[] count_sales_customers(){
            int individual = 0;
            int companies = 0; 
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT COUNT(*) "
                                            + "FROM [sales]"
                                            + "INNER JOIN [customer] ON sales.cst_id=customer.cst_id "
                                            + "WHERE customer.cst_isCompany=0;");
                while (rs.next()){
                    individual = rs.getInt(1);
                }
                rs = statement.executeQuery("SELECT COUNT(*) "
                                            + "FROM [sales] "
                                            + "INNER JOIN [customer] ON sales.cst_id=customer.cst_id "
                                            + "WHERE customer.cst_isCompany= -1;");
                while (rs.next()){
                    companies = rs.getInt(1);
                }
                Disconnect();
            }catch(SQLException e){
                e.printStackTrace();
            }
            int[] data = {individual,companies};
            return data;
        }
    protected void Disconnect() throws SQLException{
        if (connection != null)
            connection.close();
        
        if (prepared_statement !=null)
            prepared_statement.close();
        
        if (statement != null)
            statement.close();
        
        if (rs != null)
            rs.close();
        
    }
}
