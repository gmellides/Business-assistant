/*
 * Title: c_Person.java 
 * Type: Class
 * Author: Gabriel Mellides
 * Description: This is a Super Class and describes a Person.
 */
package erpsystem.entities.people;

import java.time.LocalDate;

public class Person {
    
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

    public void setBirthdate(LocalDate Birthdate) {
        this.Birthdate = Birthdate;
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

    public LocalDate getBirthdate() {
        return Birthdate;
    }

    
    // ----- Class Fields
    private String FirstName;
    private String LastName;
    private String Sex;
    private LocalDate Birthdate;

}
