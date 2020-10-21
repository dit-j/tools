package tools.security;

import de.jawb.tools.security.Generator;
import de.jawb.tools.security.crypt.cipher.CipherFactory;
import de.jawb.tools.security.crypt.cipher.ICipher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

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

    public void testAES_1() {
        ICipher c = CipherFactory.createAES_128_Legacy("example-unsafe_key".toCharArray());
        String encoded = c.encrypt(original);
        String decoded = c.decrypt(encoded);
        Assert.assertEquals(original, decoded);
    }

    @Test
    public void testAES_2() {
        ICipher c = CipherFactory.createMaskedAESCipher("example-XOR-Key".toCharArray(), "example-AES-Key".toCharArray());
        String encoded = c.encrypt(original);
        String decoded = c.decrypt(encoded);
        Assert.assertEquals(original, decoded);
    }

    @Test
    public void testAES_128() {
        ICipher c = CipherFactory.createAES_128("example-unsafe_key".toCharArray());
        String encoded = c.encrypt(original);
        String decoded = c.decrypt(encoded);
        Assert.assertEquals(original, decoded);
    }

    @Test
    public void testAES_128_different_output() {
        ICipher c = CipherFactory.createAES_128("example-unsafe_key".toCharArray());
        String a = c.encrypt("test");
        String b = c.encrypt("test");
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testAES_256() {
        if (CipherFactory.isAES_256Supported()) {
            ICipher c = CipherFactory.createAES_256("example-unsafe_key".toCharArray());
            String encoded = c.encrypt(original);
            String decoded = c.decrypt(encoded);
            Assert.assertEquals(original, decoded);
        }
    }

    @Test
    public void testAES_256_different_output() {
        ICipher c = CipherFactory.createAES_256("example-unsafe_key".toCharArray());
        String a = c.encrypt("test");
        String b = c.encrypt("test");
        Assert.assertNotEquals(a, b);
    }

    @Test
    public void testAES_256_empty_string_are_encrypted() {
        ICipher c = CipherFactory.createAES_256("example-unsafe_key".toCharArray());

        String a = c.encrypt("");
        String b = c.encrypt("");

        Assert.assertNotEquals("", a);
        Assert.assertNotEquals("", b);
        Assert.assertNotEquals(a, b);
    }
}
