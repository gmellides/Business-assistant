/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.inputcheck;


public class InputValidator {
    public boolean check_int(String input){
        boolean flag = false;
            if (input.matches("[0-9]")){
                flag = true;
            }
        return flag;
    }
}
