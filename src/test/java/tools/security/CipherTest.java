package tools.security;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.jawb.tools.security.Generator;
import de.jawb.tools.security.crypt.cipher.MaskedAESCipher;
import de.jawb.tools.security.crypt.cipher.CipherFactory;
import de.jawb.tools.security.crypt.cipher.ICipher;

@RunWith(Parameterized.class)
public class CipherTest {

    private String txt;

    public CipherTest(String txt) {
        this.txt = txt;
    }

    @Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[] { //
                "Minions ipsum poopayee bappleees la bodaaa belloo!", //
                "Süßigkeiten", //
                "asd 44 -)? _%`", //
                "Hallo-Wält", //
                Generator.generateToken(250) //
        });
    }

    @Test
    public void testAES_1() {
        ICipher c = CipherFactory.createAES_128("imuGnd&+äe4ifDF");
        String encoded = c.encrypt(txt);
        String decoded = c.descrypt(encoded);
        Assert.assertEquals(txt, decoded);
    }

    @Test
    public void testAES_2() {
        ICipher c = new MaskedAESCipher();
        String encoded = c.encrypt(txt);
        String decoded = c.descrypt(encoded);
        Assert.assertEquals(txt, decoded);
    }
}