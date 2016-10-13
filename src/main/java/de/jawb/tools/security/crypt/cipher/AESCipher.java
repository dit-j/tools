package de.jawb.tools.security.crypt.cipher;

/**
 * @author dit
 */
class AESCipher implements ICipher {

    private final String secKey;

    AESCipher(String secKey) {
        this.secKey = secKey;
    }

    @Override
    public String encrypt(String str) {
        return AESUtil.encrypt(secKey, str);
    }

    @Override
    public String descrypt(String encryptedString) {
        return AESUtil.descrypt(secKey, encryptedString);
    }
    
}
