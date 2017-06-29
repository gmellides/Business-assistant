/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.people;

/**
 *
 * @author gabri
 */
public class Supplier extends Person{

    public void setSupplier_Type(String Supplier_Type) {
        this.Supplier_Type = Supplier_Type;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setState(String State) {
        this.State = State;
    }

    public void setZipcode(int Zipcode) {
        this.Zipcode = Zipcode;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public void setBank(String Bank) {
        this.Bank = Bank;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
    
    public String getSupplier_Type() {
        return Supplier_Type;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getState() {
        return State;
    }

    public int getZipcode() {
        return Zipcode;
    }

    public String getPhone() {
        return Phone;
    }

    public String getFax() {
        return Fax;
    }

    public String getMail() {
        return Mail;
    }

    public String getBank() {
        return Bank;
    }

    public String getIBAN() {
        return IBAN;
    }

    private String Supplier_Type;
    private String Address;
    private String City;
    private String Country;
    private String State;
    private int Zipcode;
    private String Phone;
    private String Fax;
    private String Mail;
    private String Bank;
    private String IBAN;
}
