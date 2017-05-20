/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erpsystem.util.safety;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtil {
    private String Passphrase = "koue25nfier356n4";
    private Key encryption_key;
    private Cipher encrypt_engine;
    
    public String encrypt_string(String data){
        String Encrypted_data = "";
            try{
            encryption_key = new SecretKeySpec(Passphrase.getBytes(),"AES");
            encrypt_engine = Cipher.getInstance("AES");
            encrypt_engine.init(Cipher.ENCRYPT_MODE,encryption_key);
            byte[] encrypted_data_bytes = encrypt_engine.doFinal(data.getBytes());
            Base64.Encoder encoder_engine = Base64.getEncoder();
            Encrypted_data = encoder_engine.encodeToString(encrypted_data_bytes);
            }catch(InvalidKeyException|NoSuchAlgorithmException
                   |NoSuchPaddingException|IllegalBlockSizeException|BadPaddingException e){
                e.printStackTrace();
            }
        return Encrypted_data;
    }
}
