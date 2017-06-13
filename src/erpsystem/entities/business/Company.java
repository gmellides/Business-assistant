/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.business;


public class Company {

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setZipCode(int ZipCode) {
        this.ZipCode = ZipCode;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setMail(String Mail) {
        this.Mail = Mail;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return City;
    }

    public int getZipCode() {
        return ZipCode;
    }

    public String getCountry() {
        return Country;
    }

    public String getPhone() {
        return Phone;
    }

    public String getMail() {
        return Mail;
    }
    
    private String CompanyName;
    private String Address;
    private String City;
    private int ZipCode;
    private String Country;
    private String Phone;
    private String Mail;
}
