
package erpsystem.database.products;

import java.sql.SQLException;
import java.util.HashMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsSales extends ProductsDatabase{
    
    public ObservableList<String> getproducts(){
        ObservableList<String> data = FXCollections.observableArrayList();
             try{
                Connect();
                    statement = connection.createStatement();
                    rs = statement.executeQuery("SELECT prd_id,prd_name,prd_category,prd_quantity from Product;");
                    while (rs.next()){
                        data.add(String.valueOf(rs.getInt("prd_id"))+" "+
                                                rs.getString("prd_name")+" ("+
                                                rs.getString("prd_category")+","+
                                                "Qunatity: "+
                                                rs.getString("prd_quantity")+")");  
                    }
                Disconnect();    
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
    
    public HashMap<Integer,String> select_sellPrice(){
        HashMap<Integer,String> prices = new HashMap<>();
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT prd_id,prd_sellPrice FROM Product;");
                while (rs.next()){
                     prices.put(rs.getInt("prd_id"),String.valueOf(rs.getFloat("prd_sellPrice")));
                }
                Disconnect();    
            }catch(SQLException e){
                e.printStackTrace();
            }
        return prices;
    }
    
    public HashMap<Integer,String> select_quantity(){
        HashMap<Integer,String> quantity = new HashMap<>();
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT prd_id,prd_quantity FROM Product;");
                while (rs.next()){
                     quantity.put(rs.getInt("prd_id"),String.valueOf(rs.getInt("prd_quantity")));
                }
                Disconnect();    
            }catch(SQLException e){
                e.printStackTrace();
            }
        return quantity;
    }

    public String select_prdName(int id){
        String data = null;
            try{
                Connect();
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT prd_name FROM Product WHERE prd_id = "+id+";");
                while (rs.next()){
                    data = rs.getString("prd_name");
                }
                Disconnect();    
            }catch(SQLException e){
                e.printStackTrace();
            }
        return data;
    }
}
