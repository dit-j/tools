package de.jawb.tools.security.crypt.cipher;

class AES_128_Cipher implements ICipher{

    private final String password;
    
    AES_128_Cipher(String password) {
        this.password = password;
    }
    
    
    @Override
    public String encrypt(String str) {
        return AESCore.encrypt(password, str, 128);
    }

    @Override
    public String decrypt(String encryptedString) {
        return AESCore.decrypt(password, encryptedString, 128);
    }
    
}
