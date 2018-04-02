package de.jawb.tools.security.crypt.cipher;

class AESCipherImpl implements ICipher {
    
    private final String password;
    private final int    keyLength;
    
    AESCipherImpl(String password, int keyLength) {
        this.password = password;
        this.keyLength = keyLength;
    }
    
    @Override
    public String encrypt(String plainText) {
        return AESCore.encrypt(password, plainText, keyLength);
    }
    
    @Override
    public String decrypt(String encryptedText) {
        return AESCore.decrypt(password, encryptedText, keyLength);
    }
    
    @Override
    public String toString() {
        return "AES-" + keyLength + "-Cipher (AES/CBC/PKCS5Padding)";
    }

    @Override
    public CipherType type() {
        return keyLength == 256 ? CipherType.AES_256 : CipherType.AES_128;
    }
}
