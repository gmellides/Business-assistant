/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.business;

import java.time.LocalDate;

public class Business {

    public Business(String Business_Name, 
                    String Business_Description, 
                    String Business_Address, 
                    String Business_City, 
                    String Business_Phone, 
                    String Business_Fax, 
                    String Business_TaxReg, 
                    LocalDate Business_Date) {
        this.Business_Name = Business_Name;
        this.Business_Description = Business_Description;
        this.Business_Address = Business_Address;
        this.Business_City = Business_City;
        this.Business_Phone = Business_Phone;
        this.Business_Fax = Business_Fax;
        this.Business_TaxReg = Business_TaxReg;
        this.Business_Date = Business_Date;
    }

    public void setBusiness_Name(String Business_Name) {
        this.Business_Name = Business_Name;
    }

    public void setBusiness_Description(String Business_Description) {
        this.Business_Description = Business_Description;
    }

    public void setBusiness_Address(String Business_Address) {
        this.Business_Address = Business_Address;
    }

    public void setBusiness_City(String Business_City) {
        this.Business_City = Business_City;
    }

    public void setBusiness_Phone(String Business_Phone) {
        this.Business_Phone = Business_Phone;
    }

    public void setBusiness_Fax(String Business_Fax) {
        this.Business_Fax = Business_Fax;
    }

    public void setBusiness_TaxReg(String Business_TaxReg) {
        this.Business_TaxReg = Business_TaxReg;
    }

    public void setBusiness_Date(LocalDate Business_Date) {
        this.Business_Date = Business_Date;
    }

    public String getBusiness_Name() {
        return Business_Name;
    }

    public String getBusiness_Description() {
        return Business_Description;
    }

    public String getBusiness_Address() {
        return Business_Address;
    }

    public String getBusiness_City() {
        return Business_City;
    }

    public String getBusiness_Phone() {
        return Business_Phone;
    }

    public String getBusiness_Fax() {
        return Business_Fax;
    }

    public String getBusiness_TaxReg() {
        return Business_TaxReg;
    }

    public LocalDate getBusiness_Date() {
        return Business_Date;
    }

    private String Business_Name;
    private String Business_Description;
    private String Business_Address;
    private String Business_City; 
    private String Business_Phone;
    private String Business_Fax;
    private String Business_TaxReg;
    private LocalDate Business_Date;
}