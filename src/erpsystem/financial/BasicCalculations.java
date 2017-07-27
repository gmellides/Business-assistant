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
    
    public float calc_prefered_profit(float purc_price,int prefered_profit){
        float percentance = (prefered_profit/100)+1;
        return (purc_price*percentance)-purc_price;
    }
    
    public float calc_final_price(int VAT,float purc_price,float prefered_profit){
        float vat_number = (VAT/100)+1;
        return (purc_price+prefered_profit)*vat_number;
    }
    
}
