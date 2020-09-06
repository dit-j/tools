package de.jawb.tools.security.crypt.cipher;

public interface ICipher {

	String encrypt(String str);

	String decrypt(String encryptedString);

	CipherType type();

	ICipher clone();

	void reset();
}
