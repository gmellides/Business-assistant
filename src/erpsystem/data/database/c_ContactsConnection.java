/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.data.database;

import erpsystem.people.c_Contact;

/**
 * Database Password: %*&#contacts#&*%
 */
public class c_ContactsConnection {
    public boolean Connect(){
        return true;
    }
            
    public boolean insert_record(c_Contact input){
        return true;
    }
    public boolean delete_record(){
        return true;
    }
    public boolean update_record(c_Contact input){
        return true;
    }
    public boolean Disconnect(){
        return true;
    }
}
