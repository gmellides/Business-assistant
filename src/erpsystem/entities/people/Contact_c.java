/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.entities.people;

public class Contact_c extends Person_s_c {
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
    public void setImport_date(String Import_date) {
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
    public String getImport_date() {
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
    
    // ---- Class Fields
    private String Phone_1;
    private String Phone_1_type;
    private String Phone_2;
    private String Phone_2_type;
    private String City;
    private String Mail;
    private String Comments;
    private String Website;
    private String Import_date;
}
