/*
 * Title: c_Person.java 
 * Type: Class
 * Author: Gabriel Mellides
 * Description: This is a Super Class and describes a Person.
 */
package erpsystem.entities.people;

public class Person_s_c {
    
    // ----- Set Methods
    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
    public void setLastName(String LastName) {
        this.LastName = LastName;
    }
    public void setSex(String Sex) {
        this.Sex = Sex;
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
    
    // ----- Get Methods
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public String getSex() {
        return Sex;
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
    
    // ----- Class Fields
    private String FirstName;
    private String LastName;
    private String Sex;
    private String Address;
    private int ZipCode;
    private String State;
    private String Country; 
}
