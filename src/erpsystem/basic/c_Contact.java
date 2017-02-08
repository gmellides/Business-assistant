/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.basic;

import java.util.Date;


public class c_Contact extends c_Person {
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
    
    // ---- Class Fields
    private String Phone_1;
    private String Phone_2;
    private String Mail;
    private String Comments;
    private String Website;
    private Date Import_date;
}
