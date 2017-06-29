/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.corpotations;


public class SupplierCompany {

    public SupplierCompany(){
        
    }
    
    public SupplierCompany(String CompanyName, String Address, int ZipCode, String City, String State, String Country, String SupplierType, String Phone, String fax, String Mail, String Bank, String IBAN) {
        this.CompanyName = CompanyName;
        this.Address = Address;
        this.ZipCode = ZipCode;
        this.City = City;
        this.State = State;
        this.Country = Country;
        this.SupplierType = SupplierType;
        this.Phone = Phone;
        this.fax = fax;
        this.Mail = Mail;
        this.Bank = Bank;
        this.IBAN = IBAN;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

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

    public void setSupplierType(String SupplierType) {
        this.SupplierType = SupplierType;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setFax(String fax) {
        this.fax = fax;
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

    public String getCompanyName() {
        return CompanyName;
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

    public String getSupplierType() {
        return SupplierType;
    }

    public String getPhone() {
        return Phone;
    }

    public String getFax() {
        return fax;
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
    
    
    
    
    
    private String CompanyName;
    private String Address;
    private int ZipCode;
    private String City;
    private String State;
    private String Country;
    private String SupplierType;
    private String Phone;
    private String fax;
    private String Mail;
    private String Bank;
    private String IBAN;
}
