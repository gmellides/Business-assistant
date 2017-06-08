/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.graphics.controllers.storage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

public class StorageManager implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (load_info(rb))
            set_window(rb);
    }  
    
    private boolean load_info(ResourceBundle rb){
        boolean flag = false;
        
        
        return flag;
    }
    private void set_window(ResourceBundle rb){
        
    }
}
