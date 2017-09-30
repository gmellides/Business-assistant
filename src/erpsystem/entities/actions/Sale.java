/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.actions;

public class Sale {

    public void setCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }
    public void setProducts(int[] Products) {
        this.Products = Products;
    }
    public void setQuantity(int[] Quantity) {
        this.Quantity = Quantity;
    }
    public void setPrice(float[] Price) {
        this.Price = Price;
    }
    public void setFinal_Price(float Final_Price) {
        this.Final_Price = Final_Price;
    }
    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }
    
    public int getCustomerID() {
        return CustomerID;
    }
    public int[] getProducts() {
        return Products;
    }
    public int[] getQuantity() {
        return Quantity;
    }
    public float[] getPrice() {
        return Price;
    }
    public float getFinal_Price() {
        return Final_Price;
    }
    public String getPaymentMethod() {
        return PaymentMethod;
    }
    
    private int CustomerID;
    private int[] Products;
    private int[] Quantity;
    private float[] Price;
    private float Final_Price;
    private String PaymentMethod;
}
