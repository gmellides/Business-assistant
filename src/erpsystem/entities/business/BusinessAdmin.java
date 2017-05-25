/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.business;

import erpsystem.entities.people.Person_s_c;

public class BusinessAdmin extends Person_s_c{

    public BusinessAdmin(String FirstName,
                         String LastName,
                         String Sex,
                         String Address,
                         int ZipCode,
                         String TaxReg,
                         String Phone1,
                         String Phone2,
                         String Mail) {
        this.setFirstName(FirstName);
        this.setLastName(LastName);
        this.setAddress(Address);
        this.setZipCode(ZipCode);
        this.setSex(Sex); 
        this.TaxReg = TaxReg;
        this.Phone1 = Phone1;
        this.Phone2 = Phone2;
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
    
    private String TaxReg;
    private String Phone1;
    private String Phone2;
    private String Mail;
}
