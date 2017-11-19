/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.financial;

public class BasicCalculations {
    
    public float get_product_profit(float purc_price,float sell_price){
        return sell_price - purc_price;
    }

    public double calc_sell_price(int VAT,double purc_price,double prefered_profit){
        double vat = (VAT/100.0)+1.0;
        return (purc_price+prefered_profit)*vat;
    }
    
    public double calc_purchase_cost(int Quantity,double PurchasePrice){
        return Quantity*PurchasePrice;
    }
}
