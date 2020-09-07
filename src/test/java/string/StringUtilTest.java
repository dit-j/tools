package string;

import de.jawb.tools.string.StringUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringUtilTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testCharsequenceToCharArray() {
        Assert.assertEquals("dabc", new String(StringUtil.getChars("dabc")));
    }

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
        Assert.assertTrue(StringUtil.findEmpty((String[]) null) == 0);

        Assert.assertFalse(StringUtil.findEmpty("a", "b", " ", "d") == 2);
    }

    @Test
    public void testAllNotEmpty() {
        Assert.assertTrue(StringUtil.allNotEmpty("a", "b", "c", "d"));
        Assert.assertFalse(StringUtil.allNotEmpty("a", "b", "", "d"));
    }

    @Test
    public void testAllEmpty() {
        Assert.assertTrue(StringUtil.allEmpty("", "", "", ""));
        Assert.assertTrue(StringUtil.allEmpty("", null, ""));

        Assert.assertFalse(StringUtil.allEmpty("", null, "", "d"));
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
        Assert.assertTrue(StringUtil.isValidLength("abcd", 3, -1));
        Assert.assertTrue(StringUtil.isValidLength("abcd", 2, -1));
        Assert.assertTrue(StringUtil.isValidLength("", -1, 3));
        Assert.assertTrue(StringUtil.isValidLength("asdasd", -1, -1));

        Assert.assertFalse(StringUtil.isValidLength("", 1, 3));
        Assert.assertFalse(StringUtil.isValidLength("abc", 4, 10));
        Assert.assertFalse(StringUtil.isValidLength("abcd", 2, 3));
        Assert.assertFalse(StringUtil.isValidLength("abcd", 1, 3));
        Assert.assertFalse(StringUtil.isValidLength(null, 1, 3));
    }

    @Test
    public void testGetFirstNotNull() {
        Assert.assertEquals("a", StringUtil.getFirstNotNull("a", "b", "c"));
        Assert.assertEquals("a", StringUtil.getFirstNotNull(null, null, "a", "b", "c"));
        Assert.assertEquals("a", StringUtil.getFirstNotNull(null, "a", null, "a", "b", "c"));

        Assert.assertNull(StringUtil.getFirstNotNull((String)null));
        Assert.assertNull(StringUtil.getFirstNotNull(""));
    }

    @Test
    public void testGetNonEmptyString() {
        Assert.assertEquals("a", StringUtil.getNonEmptyString(null, "a"));
        Assert.assertEquals("a", StringUtil.getNonEmptyString("", "a"));
        Assert.assertEquals("a", StringUtil.getNonEmptyString("a", "a"));
    }

}
