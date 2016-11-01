package de.jawb.tools.security.crypt.cipher;

import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import de.jawb.tools.security.Base64;

public class AESUtil {

    private static final String KEY_SPEC_ALGORITHM = "AES";
    private static final String CIPHER_ALG         = "AES";

    private static SecretKeySpec create(String secKey) {
        try {

            byte[] key = (secKey).getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);

            // nur die ersten 128 bit nutzen
            key = Arrays.copyOf(key, 16);

            // der fertige Schluessel
            return new SecretKeySpec(key, KEY_SPEC_ALGORITHM);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String encrypt(String secKey, String str) {
        try {

            Cipher cipher = Cipher.getInstance(CIPHER_ALG);
            cipher.init(Cipher.ENCRYPT_MODE, create(secKey));

            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));

            return Base64.encodeToString(encrypted);

        } catch (Exception ex) {
            throw new CipherException(ex);
        }
    }

    public static String descrypt(String secKey, String encryptedString) {
        try {

            byte[] cryptedBytes = Base64.decodeToBytes(encryptedString);
            Cipher cipher = Cipher.getInstance(CIPHER_ALG);
            cipher.init(Cipher.DECRYPT_MODE, create(secKey));
            byte[] cipherData = cipher.doFinal(cryptedBytes);

            return new String(cipherData, "UTF-8");

        } catch (Exception ex) {
            throw new CipherException(ex);
        }
    }

}
