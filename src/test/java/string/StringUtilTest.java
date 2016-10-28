package string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.jawb.tools.string.StringUtil;

public class StringUtilTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testCutStringToMaxLength() {
        Assert.assertEquals("abcd...", StringUtil.cutStringToMaxLength("abcdefghijkl", 4));
        Assert.assertEquals("abcd", StringUtil.cutStringToMaxLength("abcd", 4));
    }

    @Test
    public void testRotate() {
        Assert.assertEquals("dabc", StringUtil.rotate("abcd"));
    }

    @Test
    public void testFirstCharToUpperCase() {
        Assert.assertEquals("Abcd", StringUtil.firstCharToUpperCase("abcd"));
        Assert.assertEquals("Abcd", StringUtil.firstCharToUpperCase("Abcd"));
    }

    @Test
    public void testNotEmpty() {
        Assert.assertTrue(StringUtil.notEmpty("abcd"));

        Assert.assertFalse(StringUtil.notEmpty(""));
    }

    @Test
    public void testFindEmpty() {
        Assert.assertTrue(StringUtil.findEmpty("a", "b", "", "d") == 2);
        Assert.assertTrue(StringUtil.findEmpty("a", null, "", "d") == 1);
        Assert.assertTrue(StringUtil.findEmpty((String[])null) == 0);

        Assert.assertFalse(StringUtil.findEmpty("a", "b", " ", "d") == 2);
    }

    @Test
    public void testAllNotEmpty() {
        Assert.assertTrue(StringUtil.allNotEmpty("a", "b", "c", "d") );
        Assert.assertFalse(StringUtil.allNotEmpty("a", "b", "", "d") );
    }

    @Test
    public void testAllEmpty() {
        Assert.assertTrue(StringUtil.allEmpty("", "", "", "") );
        Assert.assertTrue(StringUtil.allEmpty("", null, "") );

        Assert.assertFalse(StringUtil.allEmpty("", null, "", "d") );
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(StringUtil.isEmpty(""));
        Assert.assertTrue(StringUtil.isEmpty(null));

        Assert.assertFalse(StringUtil.isEmpty("a"));
        Assert.assertFalse(StringUtil.isEmpty("  "));
    }

    @Test
    public void testIsEmptyTrim() {
        Assert.assertTrue(StringUtil.isEmptyTrim(""));
        Assert.assertTrue(StringUtil.isEmptyTrim(" "));
        Assert.assertTrue(StringUtil.isEmptyTrim("    "));
        Assert.assertTrue(StringUtil.isEmptyTrim(null));

        Assert.assertFalse(StringUtil.isEmptyTrim(" a"));
        Assert.assertFalse(StringUtil.isEmptyTrim("a  "));
        Assert.assertFalse(StringUtil.isEmptyTrim(" a "));
    }

    @Test
    public void testIsValidLength() {
        Assert.assertTrue(StringUtil.isValidLength("abcd", 4, 10));
        Assert.assertTrue(StringUtil.isValidLength("abcd", 3, 4));

        Assert.assertFalse(StringUtil.isValidLength("abcd", 1, 3));
    }

}
