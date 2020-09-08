package tools.security;

import de.jawb.tools.security.Generator;
import de.jawb.tools.security.crypt.cipher.SecUtil;
import org.junit.Assert;
import org.junit.Test;

public class SecUtilTest {

    @Test
    public void testCleanCharArray() {

        final String password = Generator.generatePassword(100, true);
        char[] array = password.toCharArray();

        //
        SecUtil.clean(array);

        String text = new String(array);

        Assert.assertEquals(password.length(), array.length);
        Assert.assertEquals(password.length(), text.length());

        Assert.assertFalse(text.contains(password));
    }

}
