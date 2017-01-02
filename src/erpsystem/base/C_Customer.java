/**
 * Title: Customer.java 
 * Type: Class
 * Author: Gabriel Mellides
 * Description: This class describes a customer
 */
package erpsystem.base;

public class C_Customer {
    // -- Set Methods
        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }
        public void setLastName(String LastName) {
            this.LastName = LastName;
        }
        public void setPhone(String Phone) {
            this.Phone = Phone;
        }
        public void setAddress(String Address) {
            this.Address = Address;
        }
        public void setCity(String City) {
            this.City = City;
        }
        public void setCountry(String Country) {
            this.Country = Country;
        }
        public void setCustomer_type(int Customer_type) {
            this.Customer_type = Customer_type;
        }
    
    // -- Get Methods
        public String getFirstName() {
            return FirstName;
        }
        public String getLastName() {
            return LastName;
        }
        public String getPhone() {
            return Phone;
        }
        public String getAddress() {
            return Address;
        }
        public String getCity() {
            return City;
        }
        public String getCountry() {
            return Country;
        }
        public int getCustomer_type() {
            return Customer_type;
        }
   
    // ---- Fields ----
        private String FirstName;
        private String LastName;
        //phone datatype may is wrong.
        private String Phone;
        private String Address;
        private String City;
        private String Country;
        private int Customer_type;
}// C_Customer
