/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.app_data;


public class Contacts_Info {
    public Contacts_Info(String Saved_contacts,
                    String Last_input){
        this.Saved_contacts = Integer.parseInt(Saved_contacts);
        this.Last_input = Last_input;
    }

    public void setSaved_contacts(int Saved_contacts) {
        this.Saved_contacts = Saved_contacts;
    }

    public void setLast_input(String Last_input) {
        this.Last_input = Last_input;
    }

    public int getSaved_contacts() {
        return Saved_contacts;
    }

    public String getLast_input() {
        return Last_input;
    }

    private int Saved_contacts;
    private String Last_input;
}
