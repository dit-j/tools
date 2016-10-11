package de.jawb.tools.security.crypt.cipher;

public interface ICipher {

	String encrypt(String str);

	String descrypt(String encryptedString);
}
