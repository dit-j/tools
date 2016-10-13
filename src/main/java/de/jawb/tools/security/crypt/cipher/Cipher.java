package de.jawb.tools.security.crypt.cipher;

import java.util.Arrays;
import java.util.List;

import de.jawb.tools.security.crypt.mask.IMasker;
import de.jawb.tools.security.crypt.mask.MaskerFactory;

public class Cipher implements ICipher {

    private List<? extends IMasker> maskers = Arrays.asList(   //
            MaskerFactory.base64(),                            //
            MaskerFactory.xor("asc$t56lcoednmcpw")             //
    );

    private List<? extends ICipher> ciphers = Arrays.asList(   //
            CipherFactory.createAES_128("blablabla")           //
    );

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
    public String descrypt(String encryptedString) {
        String temp = encryptedString;
        for (int i = ciphers.size() - 1; i >= 0; i--) {
            temp = ciphers.get(i).descrypt(temp);
        }
        for (int i = maskers.size() - 1; i >= 0; i--) {
            temp = maskers.get(i).unmask(temp);
        }
        return temp;
    }

}
