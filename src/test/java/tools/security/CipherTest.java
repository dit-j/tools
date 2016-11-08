package tools.security;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.jawb.tools.security.Generator;
import de.jawb.tools.security.crypt.cipher.CipherFactory;
import de.jawb.tools.security.crypt.cipher.ICipher;

@RunWith(Parameterized.class)
public class CipherTest {

    private String original;

    public CipherTest(String txt) {
        this.original = txt;
    }

    @Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[] { //
                "Minions ipsum poopayee bappleees la bodaaa belloo!", //
                "Süßigkeiten", //
                "asd 44 -)? _%`", //
                "Hallo-Wält", //
                "randoM.e_5$9wj#m)dyc0", //
                Generator.generateToken(250) //
        });
    }

    @Test
    public void testAES_1() {
        ICipher c = CipherFactory.createAES_128("example-unsafe_key");
        String encoded = c.encrypt(original);
        String decoded = c.decrypt(encoded);
        Assert.assertEquals(original, decoded);
    }

    @Test
    public void testAES_2() {
        ICipher c = CipherFactory.createMaskedAESCipher("example-XOR-Key", "example-AES-Key");
        String encoded = c.encrypt(original);
        String decoded = c.decrypt(encoded);
        Assert.assertEquals(original, decoded);
    }
}
