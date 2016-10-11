/**
 * 
 */
package de.jawb.tools.security.crypt.cipher;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import de.jawb.tools.security.Base64;

/**
 * @author dit
 */
public class AESCipher implements ICipher {

    private static final String ALG = "AES";

    private static final String ALGORITHM = "AES";

    private SecretKeySpec keySpec;

    public AESCipher(String secKey) {
        try {

            byte[] key = (secKey).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            // nur die ersten 128 bit nutzen
            key = Arrays.copyOf(key, 16);
            // der fertige Schluessel
            keySpec = new SecretKeySpec(key, "AES");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String encrypt(String str) {

        try {
            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);

            byte[] encrypted = cipher.doFinal(str.getBytes());
            System.out.println("encrypted string: " + Base64.encodeBytes(encrypted));

            return Base64.encodeBytes(encrypted);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public String descrypt(String encryptedString) {

        try {

            byte[] cryptedBytes = Base64.decode(encryptedString);
            Cipher cipher = Cipher.getInstance(ALG);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] cipherData = cipher.doFinal(cryptedBytes);

            return new String(cipherData);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        AESCipher c = new AESCipher("blabla");
        System.out.println(c.encrypt("hund"));
        System.out.println(c.descrypt("nZi6VABz9uXuWc9Kpu1TEg=="));
    }

}
