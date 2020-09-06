package de.jawb.tools.security.crypt.cipher;

import java.util.Arrays;

class AESCipherImpl implements ICipher {

    private char[] password;
    private int    keyLength;

    AESCipherImpl(char[] password, int keyLength) {
        this.password = password;
        this.keyLength = keyLength;
    }

    @Override
    public ICipher clone() {
        return new AESCipherImpl(this.password.clone(), keyLength);
    }

    @Override
    public void reset() {
        Arrays.fill(password, '0');
        password = null;
        keyLength = -1;
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
