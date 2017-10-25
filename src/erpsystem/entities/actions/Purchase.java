/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.actions;

public class Purchase {

    public void setSupplierID(int SupplierID) {
        this.SupplierID = SupplierID;
    }
    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }
    public void setPurchasePrice(float PurchasePrice) {
        this.PurchasePrice = PurchasePrice;
    }
    public void setPaymentMethod(String PaymentMethod) {
        this.PaymentMethod = PaymentMethod;
    }

    public int getSupplierID() {
        return SupplierID;
    }
    public int getProductID() {
        return ProductID;
    }
    public float getPurchasePrice() {
        return PurchasePrice;
    }
    public String getPaymentMethod() {
        return PaymentMethod;
    }
   
    private int SupplierID;
    private int ProductID;
    private float PurchasePrice;
    private String PaymentMethod;
}
