/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.product;


public class Product {
    public Product(){
        
    }

    public Product(String Name, String Description, String Category, int Quantity, float PurchasePrice, int VAT, float Profit, float SellPrice) {
        this.Name = Name;
        this.Description = Description;
        this.Category = Category;
        this.Quantity = Quantity;
        this.PurchasePrice = PurchasePrice;
        this.VAT = VAT;
        this.Profit = Profit;
        this.SellPrice = SellPrice;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setPurchasePrice(float PurchasePrice) {
        this.PurchasePrice = PurchasePrice;
    }

    public void setVAT(int VAT) {
        this.VAT = VAT;
    }

    public void setProfit(float Profit) {
        this.Profit = Profit;
    }

    public void setSellPrice(float SellPrice) {
        this.SellPrice = SellPrice;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getCategory() {
        return Category;
    }

    public int getQuantity() {
        return Quantity;
    }

    public float getPurchasePrice() {
        return PurchasePrice;
    }

    public int getVAT() {
        return VAT;
    }

    public float getProfit() {
        return Profit;
    }

    public float getSellPrice() {
        return SellPrice;
    }
   
    private String Name;
    private String Description;
    private String Category;
    private int Quantity;
    private float PurchasePrice;
    private int VAT;
    private float Profit;
    private float SellPrice; // -- for a product
}
