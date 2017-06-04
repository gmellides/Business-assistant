/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.business;

import erpsystem.entities.people.Person;
import java.time.LocalDate;

public class BusinessAdmin extends Person{

    public BusinessAdmin(String FirstName,
                         String LastName,
                         LocalDate Birthday,
                         String Sex,
                         String Address,
                         int ZipCode,
                         String City,
                         String Phone1,
                         String Phone2,
                         String Description,
                         String TaxReg,
                         String Mail) {
        this.setFirstName(FirstName);
        this.setLastName(LastName);
        this.setBirthdate(Birthday);
        this.setSex(Sex); 
        this.Address = Address;
        this.ZipCode = ZipCode;
        this.TaxReg = TaxReg;
        this.City = City;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
        this.Description = Description;
        this.Mail = Mail;
    }

    public void setTaxReg(String TaxReg) {
        this.TaxReg = TaxReg;
    }
    public void setPhone1(String Phone1) {
        this.Phone1 = Phone1;
    }
    public void setPhone2(String Phone2) {
        this.Phone2 = Phone2;
    }
    public void setMail(String Mail) {
        this.Mail = Mail;
    }
    public void setAddress(String Address) {
        this.Address = Address;
    }
    public void setZipCode(int ZipCode) {
        this.ZipCode = ZipCode;
    }
    public void setState(String State) {
        this.State = State;
    }
    public void setCity(String City) {
        this.City = City;
    }
    public void setDescription(String Description) {
        this.Description = Description;
    }
   
    public String getTaxReg() {
        return TaxReg;
    }
    public String getPhone1() {
        return Phone1;
    }
    public String getPhone2() {
        return Phone2;
    }
    public String getMail() {
        return Mail;
    }
    public String getAddress() {
        return Address;
    }
    public int getZipCode() {
        return ZipCode;
    }
    public String getState() {
        return State;
    }
    public String getCity() {
        return City;
    }
    public String getDescription() {
        return Description;
    }
  
    private String Address;
    private int ZipCode;
    private String City;
    private String State;
    private String TaxReg;
    private String Phone1;
    private String Phone2;
    private String Description;
    private String Mail; 
}
