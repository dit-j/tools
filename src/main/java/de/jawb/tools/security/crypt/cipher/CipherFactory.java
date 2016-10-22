package de.jawb.tools.security.crypt.cipher;

public class CipherFactory {

    public static ICipher createAES_128(String key) {
        return new AESCipher(key);
    }
}
