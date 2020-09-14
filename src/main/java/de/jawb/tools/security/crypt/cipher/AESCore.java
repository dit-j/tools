package de.jawb.tools.security.crypt.cipher;

import de.jawb.tools.security.base64.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.spec.KeySpec;

/**
 * https://nelenkov.blogspot.de/2012/04/using-password-based-encryption-on.html
 * https://android-developers.googleblog.com/2016/06/security-crypto-provider-
 * deprecated-in.html
 *
 * @author dit
 */
class AESCore {

    private static final Charset UTF_8            = StandardCharsets.UTF_8;
    private static final String  SEPARATOR        = "#";

            static final String  TRANSFORMATION   = "AES/CBC/PKCS5Padding";
            static final String  SECRET_KEY_ALG   = "PBKDF2WithHmacSHA1";

    private static void checkKeyLengthSupport(int keyLength) {
        try {
            if (Cipher.getMaxAllowedKeyLength("AES") < keyLength) {
                throw new CipherException("AES-" + keyLength + " is not supported. Current vm version: " + System.getProperty("java.version"));
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

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(SECRET_KEY_ALG);
            KeySpec keySpec = new PBEKeySpec(passwordOrPin, salt, iterations, outputKeyLength);
            byte[] keyBytes = secretKeyFactory.generateSecret(keySpec).getEncoded();

            //
            return new SecretKeySpec(keyBytes, "AES");

        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    static String encrypt(char[] password, String text, int keyLengthInBits) {

        checkKeyLengthSupport(keyLengthInBits);

        try {

            byte[] salt = randomBytes(keyLengthInBits / 8);
            byte[] iv   = randomBytes(16);

            SecretKey key = generateKey(password, salt, keyLengthInBits);

            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] encryptedData = cipher.doFinal(text.getBytes(UTF_8));

            return toBase64(salt) + SEPARATOR + toBase64(iv) + SEPARATOR + toBase64(encryptedData);
//            String saltBase64   = toBase64(salt);
//            String ivBase64     = toBase64(iv);
//            String dataBase64   = toBase64(encryptedData);
//            return saltBase64 + ivBase64 + dataBase64;

        } catch (Exception e) {
            throw new CipherException(e);
        }
    }

    static String decrypt(char[] password, String encodedData, int keyLengthInBits) {

        try {

            byte[] salt, iv, data;

            if(encodedData.contains(SEPARATOR)){

                String[] parts = encodedData.split(SEPARATOR);

                salt = fromBase64(parts[0]);
                iv   = fromBase64(parts[1]);
                data = fromBase64(parts[2]);

            } else {

                if(keyLengthInBits == 128){
                    salt = fromBase64(encodedData.substring(0, 24));
                    iv   = fromBase64(encodedData.substring(24, 48));
                    data = fromBase64(encodedData.substring(48));
                } else {

                    salt = fromBase64(encodedData.substring(0, 44));
                    iv   = fromBase64(encodedData.substring(44, 68));
                    data = fromBase64(encodedData.substring(68));

                }

            }

            SecretKey key = generateKey(password, salt, keyLengthInBits);
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
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

    private static byte[] randomBytes(int keySize) {
        byte[] salt = new byte[keySize];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

//    private static byte[] toBytes(char[] chars) {
//        CharBuffer charBuffer = CharBuffer.wrap(chars);
//        ByteBuffer byteBuffer = UTF_8.encode(charBuffer);
//        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
//
//        SecUtil.clean(charBuffer);// clear sensitive data
//        SecUtil.clean(byteBuffer);// clear sensitive data
//
//        return bytes;
//    }

}
