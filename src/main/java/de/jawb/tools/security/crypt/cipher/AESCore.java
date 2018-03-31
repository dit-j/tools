package de.jawb.tools.security.crypt.cipher;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import de.jawb.tools.security.base64.Base64;

/**
 * https://nelenkov.blogspot.de/2012/04/using-password-based-encryption-on.html 
 * https://android-developers.googleblog.com/2016/06/security-crypto-provider-deprecated-in.html
 * @author dit
 *
 */
class AESCore {
    
    private static final String SEPARATOR = "#";

    private static void checkKeyLengthSupport(int keyLenght){
        try {
            if(Cipher.getMaxAllowedKeyLength("AES") < keyLenght){
                throw new CipherException("AES-" + keyLenght + " is not supported");
            }
        } catch (Exception e) {
            if(e instanceof CipherException){
                throw (CipherException)e;
            }
            throw new CipherException(e);
        }
    }
    
    private static SecretKey generateKey(final String passwordOrPin, final byte[] salt, final int outputKeyLength) {
        
        checkKeyLengthSupport(outputKeyLength);
        
        try {
            
            // Number of PBKDF2 hardening rounds to use. Larger values increase
            // computation time. You should select a value that causes computation
            // to take > 100ms.
            final int iterations = 1000;
            
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec keySpec = new PBEKeySpec(passwordOrPin.toCharArray(), salt, iterations, outputKeyLength);
            byte[] keyBytes = secretKeyFactory.generateSecret(keySpec).getEncoded();
            
            //
            return new SecretKeySpec(keyBytes, "AES");
            
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }
    
    /**
     * 
     * @param password
     * @param text
     * @param keyLengthInBits
     * @return
     */
    static String encrypt(String password, String text, int keyLengthInBits) {

        checkKeyLengthSupport(keyLengthInBits);
        
        try {
            
            byte[] salt   = createSalt(keyLengthInBits / 8);
            byte[] iv     = createIV(16);
            
            SecretKey key = generateKey(password, salt, keyLengthInBits);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] encryptedData = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
            
            return toBase64(salt) + SEPARATOR + toBase64(iv) + SEPARATOR + toBase64(encryptedData);
            
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }
    
    /**
     * 
     * @param password
     * @param encodedData
     * @param keyLengthInBits
     * @return
     */
    static String decrypt(String password, String encodedData, int keyLengthInBits) {

        try {
            
            String[] parts = encodedData.split(SEPARATOR);
            
            byte[] salt = fromBase64(parts[0]);
            byte[] iv   = fromBase64(parts[1]);
            byte[] data = fromBase64(parts[2]);
            
            SecretKey key = generateKey(password, salt, keyLengthInBits);
            
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] decryptedData = cipher.doFinal(data);
            
            return new String(decryptedData, StandardCharsets.UTF_8);
            
        } catch (Exception e) {
            throw new CipherException(e);
        }
    }
    
    private static String toBase64(byte[] data){
        return Base64.encodeToString(data);
    }
    private static byte[] fromBase64(String base64Data){
        return Base64.decodeToBytes(base64Data);
    }
    
    private static byte[] createSalt(int keyLength){
        SecureRandom random = new SecureRandom();  
        byte[] salt = new byte[keyLength / 8];  
        random.nextBytes(salt);
        return salt;
    }
    private static byte[] createIV(int ivSize){
        SecureRandom random = new SecureRandom();  
        byte[] salt = new byte[ivSize];  
        random.nextBytes(salt);
        return salt;
    }
    
    public static void main(String[] args) {
        
        // S2chxO/ASL4j7r1w/7i1Jg==|X1HbTkU9yPw83UTiadXuBA==|rCKsXyJB8DDEj30+uJztDw==
        long s = System.currentTimeMillis();
        String encrypted = encrypt("test", "hallo welt", 256);
        System.out.println(encrypted);
        System.out.println(decrypt("test", encrypted, 256));
        long e = System.currentTimeMillis();
        System.out.println(e - s);
        
    }
}
