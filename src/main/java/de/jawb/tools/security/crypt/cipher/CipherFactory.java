package de.jawb.tools.security.crypt.cipher;

import javax.crypto.Cipher;

public class CipherFactory {
    
    public static ICipher createAES_128_Legacy(String key) {
        return new AES_128_Legacy_Cipher(key);
    }
    
    public static ICipher createAES_128(String key) {
        return new AESCipherImpl(key, 128);
    }
    
    public static ICipher createAES_256(String key) {
        return new AESCipherImpl(key, 256);
    }
    
    @Deprecated
    public static ICipher createMaskedAESCipher(String xorKey, String aesKey) {
        return new MaskedAESCipher(xorKey, aesKey);
    }
    
    public static boolean isAES_256Supported(){
        try {
            return Cipher.getMaxAllowedKeyLength("AES") >= 256;
        } catch (Exception e) {
            return false;
        }
    }
}
