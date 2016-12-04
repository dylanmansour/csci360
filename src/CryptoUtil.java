import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtil 
{
    public String encrypt(String plainText, String key) 
    {
        try 
        {
            //String key = "Bar12345Bar12345"; // 128 bit key
            // Create key and cipher
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            // encrypt the text
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b: encrypted) {
                sb.append((char)b);
            }

            // the encrypted String
            String enc = sb.toString();
            return enc;
        }
        catch(Exception e)
        {
                e.printStackTrace();
                return "";
        }
    }
        
    public String decrypt(String encText, String key)
    {
        try{
            Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");    
            
            // convert the encText string to byte array for decryption
            byte[] bb = new byte[encText.length()];
            for (int i=0; i<encText.length(); i++) {
                bb[i] = (byte) encText.charAt(i);
            }
    
            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String dec = new String(cipher.doFinal(bb));
            return dec;
        }
    
        catch(Exception e) 
        {
            e.printStackTrace();
            return "";
        }
    }
}
