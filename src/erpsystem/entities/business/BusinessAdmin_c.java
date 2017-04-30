/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.business;

import erpsystem.entities.people.Person_s_c;

public class BusinessAdmin_c extends Person_s_c{

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
