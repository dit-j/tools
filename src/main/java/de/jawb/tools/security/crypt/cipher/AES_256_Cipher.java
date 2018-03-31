package de.jawb.tools.security.crypt.cipher;

class AES_256_Cipher implements ICipher {
    
    private final String password;
    
    AES_256_Cipher(String password) {
        this.password = password;
    }
    
    @Override
    public String encrypt(String str) {
        return AESCore.encrypt(password, str, 256);
    }
    
    @Override
    public String decrypt(String encryptedString) {
        return AESCore.decrypt(password, encryptedString, 256);
    }
    
}
