/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.people;

public class Customer extends Person{

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public void setZipCode(int ZipCode) {
        this.ZipCode = ZipCode;
    }
    public void setCity(String City) {
        this.City = City;
    }
    public void setState(String State) {
        this.State = State;
    }
    public void setCountry(String Country) {
        this.Country = Country;
    }
    public void setCustomer_Type(String Customer_Type) {
        this.Customer_Type = Customer_Type;
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

    public String getAddress() {
        return Address;
    }
    public int getZipCode() {
        return ZipCode;
    }
    public String getCity() {
        return City;
    }
    public String getState() {
        return State;
    }
    public String getCountry() {
        return Country;
    }
    public String getCustomer_Type() {
        return Customer_Type;
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
    
    private String Address;
    private int ZipCode;     
    private String City;
    private String State;
    private String Country;
    private String Customer_Type;
    private String Phone;
    private String Fax;
    private String Mail;
}
