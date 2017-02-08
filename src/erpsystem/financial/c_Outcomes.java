/**
 * Title: c_Outcomes.java 
 * Type: Class
 * Author: Gabriel Mellides
 * Description: This class describes a Customer.
 */
package erpsystem.financial;

public class c_Outcomes {

    /*
      Set Methods will get data from Database Automaticly
      Data will change every year this will controlled from
      automaticly 
    */
    
    public void setEnergy_bill(double Energy_bill) {
        this.Energy_bill = Energy_bill;
    }
    public void setPhone_bill(double Phone_bill) {
        this.Phone_bill = Phone_bill;
    }
    public void setTaxes(double Taxes) {
        this.Taxes = Taxes;
    }
    public void setFactory_outcomes(double Factory_outcomes) {
        this.Factory_outcomes = Factory_outcomes;
    }

    public double getEnergy_bill() {
        return Energy_bill;
    }
    public double getPhone_bill() {
        return Phone_bill;
    }
    public double getTaxes() {
        return Taxes;
    }
    public double getFactory_outcomes() {
        return Factory_outcomes;
    }

   
    // This Method calculates the Summary of 
    public double Calculate_outcomes(){
        return 0;
    }
    
    private double Energy_bill;
    private double Phone_bill;
    private double Taxes;
    private double Factory_outcomes;
}
