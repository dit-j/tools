package tools.security;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import de.jawb.tools.security.crypt.mask.Base64Masker;
import de.jawb.tools.security.crypt.mask.IMasker;
import de.jawb.tools.security.crypt.mask.XORMasker;

@RunWith(Parameterized.class)
public class MaskTest {

    private String txt;

    public MaskTest(String txt) {
        this.txt = txt;
    }

    @Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[] { //
                "Minions ipsum poopayee bappleees la bodaaa belloo!", //
                "Süßigkeiten", //
                "asd 44 -)? _%`"
        });
    }

    @Test
    public void test64Masker() {
        IMasker masker = new Base64Masker();
        Assert.assertEquals(txt, masker.unmask(masker.mask(txt)));
    }
    
    @Test
    public void testXORMasker() {
        IMasker masker = new XORMasker("xocn$s+4");
        Assert.assertEquals(txt, masker.unmask(masker.mask(txt)));
    }

}
