package tools.security;

import de.jawb.tools.security.crypt.mask.IMasker;
import de.jawb.tools.security.crypt.mask.MaskerFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

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
                "asd 44 -)? _%`", //
                "abc" });
    }

    @Test
    public void test64Masker() {
        IMasker masker = MaskerFactory.base64();
        Assert.assertEquals(txt, masker.unmask(masker.mask(txt)));
    }

    @Test
    public void testXORMasker() {
        IMasker masker = MaskerFactory.xor("xocn$s+4".toCharArray());
        Assert.assertEquals(txt, masker.unmask(masker.mask(txt)));
    }

    @Test
    public void testROTMasker() {
        IMasker base64 = MaskerFactory.base64();
        IMasker rot = MaskerFactory.rot();
        String txtAsBase64 = base64.mask(txt);
        String txtRot = rot.mask(txtAsBase64);
        String txtUnRot = rot.unmask(txtRot);

        Assert.assertEquals(txt, base64.unmask(txtUnRot));
    }

}
