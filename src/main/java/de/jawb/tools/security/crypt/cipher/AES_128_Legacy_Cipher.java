package de.jawb.tools.security.crypt.cipher;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import de.jawb.tools.security.base64.Base64;

/**
 * @author dit
 */
@Deprecated
class AES_128_Legacy_Cipher implements ICipher {

    private final String secKey;

    AES_128_Legacy_Cipher(String secKey) {
        this.secKey = secKey;
    }

    @Override
    public String encrypt(String str) {
        return encrypt(secKey, str);
    }

    @Override
    public String decrypt(String encryptedString) {
        return descrypt(secKey, encryptedString);
    }

    private static SecretKeySpec create(String secKey) {
        try {

            byte[] key = (secKey).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);

            // nur die ersten 128 bit nutzen
            key = Arrays.copyOf(key, 16);
            
            // der fertige Schluessel
            return new SecretKeySpec(key, "AES");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String encrypt(String secKey, String str) {
        try {

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, create(secKey));

            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));

            return Base64.encodeToString(encrypted);

        } catch (Exception ex) {
            throw new CipherException(ex);
        }
    }

    private static String descrypt(String secKey, String encryptedString) {
        try {

            byte[] cryptedBytes = Base64.decodeToBytes(encryptedString);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, create(secKey));
            byte[] cipherData = cipher.doFinal(cryptedBytes);

            return new String(cipherData, "UTF-8");

        } catch (Exception ex) {
            throw new CipherException(ex);
        }
    }
}
