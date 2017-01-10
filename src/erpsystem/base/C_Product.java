/**
 * Title: C_Product.java 
 * Type: Class
 * Author: Gabriel Mellides
 * Description: This class describes a Product 
 */
package erpsystem.base;

public class C_Product {

    // Set Methods 
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }
    public void setProductDescription(String ProductDescription) {
        this.ProductDescription = ProductDescription;
    }
    public void setQuantity(double Quantity) {
        this.Quantity = Quantity;
    }
    public void setMetric(String Metric) {
        this.Metric = Metric;
    }
    
    // == Get Methods
    public String getProductName() {
        return ProductName;
    }
    public String getProductDescription() {
        return ProductDescription;
    }
    public double getQuantity() {
        return Quantity;
    }
    public String getMetric() {
        return Metric;
    }  
    
    private String ProductName;
    private String ProductDescription;
    private double Quantity;
    private String Metric;
}
