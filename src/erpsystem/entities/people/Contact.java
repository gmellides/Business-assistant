/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.people;

import java.sql.Date;

public class Contact extends Person {
    // ---- Set Methods
    public void setPhone_1(String Phone_1) {
        this.Phone_1 = Phone_1;
    }
    public void setPhone_2(String Phone_2) {
        this.Phone_2 = Phone_2;
    }
    public void setMail(String Mail) {
        this.Mail = Mail;
    }
    public void setComments(String Comments) {
        this.Comments = Comments;
    }
    public void setWebsite(String Website) {
        this.Website = Website;
    }
    public void setImport_date(Date Import_date) {
        this.Import_date = Import_date;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setPhone_1_type(String Phone_1_type) {
        this.Phone_1_type = Phone_1_type;
    }

    public void setPhone_2_type(String Phone_2_type) {
        this.Phone_2_type = Phone_2_type;
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

    public void setCountry(String Country) {
        this.Country = Country;
    }
    
    // ---- Get Methods
    public String getPhone_1() {
        return Phone_1;
    }
    public String getPhone_2() {
        return Phone_2;
    }
    public String getMail() {
        return Mail;
    }
    public String getComments() {
        return Comments;
    }
    public String getWebsite() {
        return Website;
    }
    public Date getImport_date() {
        return Import_date;
    }

    public String getCity() {
        return City;
    }

    public String getPhone_1_type() {
        return Phone_1_type;
    }

    public String getPhone_2_type() {
        return Phone_2_type;
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

    public String getCountry() {
        return Country;
    }
    
    // ---- Class Fields
    private String Address;
    private int ZipCode;
    private String State;
    private String Country; 
    private String Phone_1;
    private String Phone_1_type;
    private String Phone_2;
    private String Phone_2_type;
    private String City;
    private String Mail;
    private String Comments;
    private String Website;
    private Date Import_date;
}
