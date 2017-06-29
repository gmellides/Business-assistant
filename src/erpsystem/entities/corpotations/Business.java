/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.corpotations;

import java.time.LocalDate;

public class Business {

    public Business(String Name, String Description, String Address, String City, String Phone1, String Phone2, String Fax, String TaxReg, String Mail, LocalDate Date) {
        this.Name = Name;
        this.Description = Description;
        this.Address = Address;
        this.City = City;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.Fax = Fax;
        this.TaxReg = TaxReg;
        this.Mail = Mail;
        this.Date = Date;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setPhone1(String Phone1) {
        this.Phone1 = Phone1;
    }

    public void setPhone2(String Phone2) {
        this.Phone2 = Phone2;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public void setTaxReg(String TaxReg) {
        this.TaxReg = TaxReg;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public void setDate(LocalDate Date) {
        this.Date = Date;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getPhone1() {
        return Phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public String getFax() {
        return Fax;
    }

    public String getTaxReg() {
        return TaxReg;
    }

    public String getMail() {
        return Mail;
    }

    public LocalDate getDate() {
        return Date;
    }

    private String Name;
    private String Description;
    private String Address;
    private String City; 
    private String Phone1;
    private String Phone2;
    private String Fax;
    private String TaxReg;
    private String Mail;
    private LocalDate Date;   
}