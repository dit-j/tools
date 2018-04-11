package de.jawb.tools.security.crypt.cipher;

import java.util.Arrays;
import java.util.List;

import de.jawb.tools.security.crypt.mask.IMasker;
import de.jawb.tools.security.crypt.mask.MaskerFactory;

/**
 *
 * @author dit
 * @deprecated Diese Klasse verwendet den veralteten AES_128_Legacy Cipher!
 */
@Deprecated
public class MaskedAESCipher implements ICipher {

    private List<? extends IMasker> maskers;
    private List<? extends ICipher> ciphers;

    /**
     * @param xorKey
     * @param aes128Key
     */
    MaskedAESCipher(String xorKey, char[] aesKey) {
        maskers = Arrays.asList( //
                MaskerFactory.base64(), //
                MaskerFactory.xor(xorKey) //
        );
        ciphers = Arrays.asList( //
                CipherFactory.createAES_128_Legacy(aesKey) //
        );
    }

    @Override
    public String encrypt(String str) {
        String temp = str;
        for (IMasker m : maskers) {
            temp = m.mask(temp);
        }
        for (ICipher c : ciphers) {
            temp = c.encrypt(temp);
        }
        return temp;
    }

    @Override
    public String decrypt(String encryptedString) {
        String temp = encryptedString;
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            temp = ciphers.get(i).decrypt(temp);
        }
        for (int i = maskers.size() - 1; i >= 0; i--) {
            temp = maskers.get(i).unmask(temp);
        }
        return temp;
    }

    @Override
    public CipherType type() {
        return CipherType.AES_128_XOR_Legacy;
    }

}
