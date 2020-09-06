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

    private char[] secKey;

    AES_128_Legacy_Cipher(char[] secKey) {
        this.secKey = secKey;
    }

    @Override
    public ICipher clone() {
        return new AES_128_Legacy_Cipher(this.secKey.clone());
    }

    @Override
    public void reset() {
        Arrays.fill(secKey, '0');
        secKey = null;
    }

    @Override
    public String encrypt(String str) {
        return encrypt(secKey, str);
    }

    @Override
    public String decrypt(String encryptedString) {
        return descrypt(secKey, encryptedString);
    }

    private static SecretKeySpec create(char[] secKey) {
        try {
            byte[] key = new String(secKey).getBytes("UTF-8");
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

    private static String encrypt(char[] secKey, String str) {
        try {

            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, create(secKey));

            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));

            return Base64.encodeToString(encrypted);

        } catch (Exception ex) {
            throw new CipherException(ex);
        }
    }

    private static String descrypt(char[] secKey, String encryptedString) {
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

    @Override
    public CipherType type() {
        return CipherType.AES_128_Legacy;
    }

    @Override
    public String toString() {
        return "AES-128-Cipher-Legacy";
    }

    public static void main(String[] args) {

        ICipher c = CipherFactory.createAES_256("fn$+lk/jm".toCharArray());

//        System.out.println(c.encrypt("name"));
//        System.out.println(c.encrypt("userName"));
//        System.out.println(c.encrypt("password"));
        String decoded = c.decrypt("n6oyAQ==krvlVV/KUziw4iIWp9x8Kg==Cef/hyL60IBIdnP0xa6/xA==");
        String decoded2 = c.decrypt("LF0mpVeK/aOZkcLfTbL0WGpjhdAvZZ6NdB5Y89EUj8s=#Eb+nZoclbSf38JVr2OX2Gg==#IufnK3JUFIuUlmimXjwulA==");

        System.out.println(decoded);
        System.out.println(decoded2);

    }
}
