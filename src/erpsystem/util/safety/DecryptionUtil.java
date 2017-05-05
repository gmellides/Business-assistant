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

/**
 *
 * @author gabri
 */
public class DecryptionUtil {
     private String Passphrase = "koue25nfier356n4"; //16bit Password
    private Key decryption_key;
    private Cipher decrypt_engine;
    
    public String decrypt_string(String encrypted) throws NoSuchAlgorithmException{
        String Decrypted_Data = "";
            try{
            decryption_key = new SecretKeySpec(Passphrase.getBytes(),"AES");
            decrypt_engine = Cipher.getInstance("AES");
            decrypt_engine.init(Cipher.DECRYPT_MODE,decryption_key);
            Base64.Decoder decode_engine = Base64.getDecoder();
            byte[] d = decode_engine.decode(encrypted);
            Decrypted_Data = new String(decrypt_engine.doFinal(d));
            }catch(InvalidKeyException|IllegalBlockSizeException|BadPaddingException|NoSuchPaddingException e){
                e.printStackTrace();
            }
        return Decrypted_Data;
    }
}
