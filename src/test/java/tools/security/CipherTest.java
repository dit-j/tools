package tools.security;

import org.junit.Assert;
import org.junit.Test;

import de.jawb.tools.security.crypt.cipher.AESCipher;
import de.jawb.tools.security.crypt.cipher.ICipher;

public class CipherTest {


    @Test
    public void testAES_128() {

        ICipher c = new AESCipher("imuGnd&+e4ifDF");
        
        String rawstr = "Hallo-Wält";
        String encoded = c.encrypt(rawstr);
        String decoded = c.descrypt(encoded);
        Assert.assertEquals(rawstr, decoded);
    }
}
