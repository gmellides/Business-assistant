/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.safety;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryption {
    private final String Key = "%en^%cry(25pt39*ion%%$";
    private Cipher encryption_mechanism;
    private Key my_key;
  
    public String encrypt_string(String data) {
        String Encrypted_String = null;
            my_key = new SecretKeySpec(Key.getBytes(),"AES");
            try {
                encryption_mechanism = Cipher.getInstance("AES");
                encryption_mechanism.init(Cipher.ENCRYPT_MODE, my_key);
                byte[] encrypted_data = encryption_mechanism.doFinal(data.getBytes());
                Base64.Encoder encoder_mechanism = Base64.getEncoder();
                Encrypted_String = encoder_mechanism.encodeToString(encrypted_data);          
            }catch(InvalidKeyException|NoSuchAlgorithmException
                   |NoSuchPaddingException|IllegalBlockSizeException
                   |BadPaddingException ex) {
                Logger.getLogger(Encryption.class.getName()).log(Level.SEVERE, null, ex);
            }
        return Encrypted_String;
    }
    
}
