/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.safety;

import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;


public class decryption {
    private final String Key = "%en^%cry(25pt39*ion%%$";
    private Cipher decryption_mechanism;
    private Key my_key;
    
    public String decrypt_string(String encrypted){
        String Decrypted_Data = null;
            my_key = new SecretKeySpec(Key.getBytes(),"AES");
            Base64.Decoder decoder_mechanism = Base64.getDecoder();
            try{
                decryption_mechanism.init(Cipher.DECRYPT_MODE, my_key);
                byte[] encoded_string = decoder_mechanism.decode(encrypted.getBytes());
                Decrypted_Data = String.valueOf(decryption_mechanism.doFinal(encoded_string));
            }catch(InvalidKeyException|IllegalBlockSizeException|BadPaddingException  e){
                e.printStackTrace();
            }
        return Decrypted_Data;
    }
}
