package de.jawb.tools.security.crypt.cipher;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import de.jawb.tools.security.base64.Base64;

/**
 * https://nelenkov.blogspot.de/2012/04/using-password-based-encryption-on.html
 * https://android-developers.googleblog.com/2016/06/security-crypto-provider-
 * deprecated-in.html
 *
 * @author dit
 */
class AESCore {

    private static final Charset UTF_8     = Charset.forName("UTF-8");
    private static final String  SEPARATOR = "#";

    private static void checkKeyLengthSupport(int keyLenght) {
        try {
            if (Cipher.getMaxAllowedKeyLength("AES") < keyLenght) {
                throw new CipherException("AES-" + keyLenght + " is not supported. Current vm version: " + System.getProperty("java.version"));
            }
        } catch (Exception e) {
            if (e instanceof CipherException) {
                throw (CipherException) e;
            }
            throw new CipherException(e);
        }
    }

    private static SecretKey generateKey(final char[] passwordOrPin, final byte[] salt, final int outputKeyLength) {

        checkKeyLengthSupport(outputKeyLength);

        try {

            // Number of PBKDF2 hardening rounds to use. Larger values increase
            // computation time. You should select a value that causes computation
            // to take > 100ms.
            final int iterations = 1000;

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec keySpec = new PBEKeySpec(passwordOrPin, salt, iterations, outputKeyLength);
            byte[] keyBytes = secretKeyFactory.generateSecret(keySpec).getEncoded();

            //
            return new SecretKeySpec(keyBytes, "AES");

        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    /**
     * @param password
     * @param text
     * @param keyLengthInBits
     * @return
     */
    static String encrypt(char[] password, String text, int keyLengthInBits) {

        checkKeyLengthSupport(keyLengthInBits);

        try {

            byte[] salt = createSalt(keyLengthInBits / 8);
            byte[] iv   = createIV(16);

            SecretKey key = generateKey(password, salt, keyLengthInBits);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] encryptedData = cipher.doFinal(text.getBytes(UTF_8));

            return toBase64(salt) + SEPARATOR + toBase64(iv) + SEPARATOR + toBase64(encryptedData);

        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    /**
     * @param password
     * @param encodedData
     * @param keyLengthInBits
     * @return
     */
    static String decrypt(char[] password, String encodedData, int keyLengthInBits) {

        try {

            String[] parts = encodedData.split(SEPARATOR);

            byte[] salt = fromBase64(parts[0]);
            byte[] iv   = fromBase64(parts[1]);
            byte[] data = fromBase64(parts[2]);

            SecretKey key = generateKey(password, salt, keyLengthInBits);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] decryptedData = cipher.doFinal(data);

            return new String(decryptedData, UTF_8);

        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    private static String toBase64(byte[] data) {
        return Base64.encodeToString(data);
    }

    private static byte[] fromBase64(String base64Data) {
        return Base64.decodeToBytes(base64Data);
    }

    private static byte[] createSalt(int keySize) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[keySize];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] createIV(int ivSize) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[ivSize];
        random.nextBytes(salt);
        return salt;
    }

    private static byte[] toBytes(char[] chars) {
        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
        Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
        Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
        return bytes;
    }

    public static void main(String[] args) {
        // S2chxO/ASL4j7r1w/7i1Jg==|X1HbTkU9yPw83UTiadXuBA==|rCKsXyJB8DDEj30+uJztDw==
        System.out.println(encrypt("letmein".toCharArray(), "name", 256));
        System.out.println(decrypt("letmein".toCharArray(), "cE0q4A==#haYUiPumFHseKAUtLj0HYg==#RaknPNnfqkadDobsLkvzyQ==", 256));
    }
}
