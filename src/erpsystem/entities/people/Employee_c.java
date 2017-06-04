/*
 * Title: c_Employee.java 
 * Type: Class
 * Author: Gabriel Mellides
 * Description: This is a Sub Class and describes an Employee.
 */
package erpsystem.entities.people;

import java.util.Date;

public class Employee_c extends Person{
    
    // ---- Set Methods
    public void setSpooseName(String SpooseName) {
        this.SpooseName = SpooseName;
    }
    public void setFatherName(String FatherName) {
        this.FatherName = FatherName;
    }
    public void setCategory(String Category) {
        this.Category = Category;
    }
    public void setPhone_1(String Phone_1) {
        this.Phone_1 = Phone_1;
    }
    public void setPhone_2(String Phone_2) {
        this.Phone_2 = Phone_2;
    }
    public void setIdentity_Card(String Identity_Card) {
        this.Identity_Card = Identity_Card;
    }
    public void setRegistration_Number(int Registration_Number) {
        this.Registration_Number = Registration_Number;
    }
    public void setSallary(double Sallary) {
        this.Sallary = Sallary;
    }
    public void setHire_Date(Date Hire_Date) {
        this.Hire_Date = Hire_Date;
    }
    public void setImport_Date(Date Import_Date) {
        this.Import_Date = Import_Date;
    }
    
    // ---- Get Methods
    public String getSpooseName() {
        return SpooseName;
    }
    public String getFatherName() {
        return FatherName;
    }
    public String getCategory() {
        return Category;
    }
    public String getPhone_1() {
        return Phone_1;
    }
    public String getPhone_2() {
        return Phone_2;
    }
    public String getIdentity_Card() {
        return Identity_Card;
    }
    public int getRegistration_Number() {
        return Registration_Number;
    }
    public double getSallary() {
        return Sallary;
    }
    public Date getHire_Date() {
        return Hire_Date;
    }
    public Date getImport_Date() {
        return Import_Date;
    }
    
    // ---- Class Fields
    private String SpooseName;
    private String FatherName;
    private String Category;
    private String Phone_1;
    private String Phone_2;
    private String Identity_Card;
    private int Registration_Number;
    private double Sallary;
    private Date Hire_Date;
    private Date Import_Date;
}
