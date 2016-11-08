package de.jawb.tools.string;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringCleanerTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testRemoveDoubleWhiteSpaces() {
        Assert.assertEquals(" ", StringCleaner.removeDoubleWhiteSpaces("  "));
        Assert.assertEquals(" a ", StringCleaner.removeDoubleWhiteSpaces(" a  "));
        Assert.assertEquals(" a ", StringCleaner.removeDoubleWhiteSpaces(" a   "));
        Assert.assertEquals(" a ", StringCleaner.removeDoubleWhiteSpaces(" a    "));
        Assert.assertEquals(" a ", StringCleaner.removeDoubleWhiteSpaces(" a\t"));

        Assert.assertNull(StringCleaner.removeDoubleWhiteSpaces(null));
    }

    @Test
    public void testRemoveTrailingString() {
        Assert.assertEquals("a", StringCleaner.removeTrailingString("a.exe", ".exe"));
        Assert.assertEquals("a", StringCleaner.removeTrailingString("a", ".exe"));
        Assert.assertEquals("", StringCleaner.removeTrailingString("", ".exe"));
    }

    @Test
    public void testRemoveWhiteSpaces() {
        Assert.assertEquals("", StringCleaner.removeWhiteSpaces("   "));
        Assert.assertEquals("a", StringCleaner.removeWhiteSpaces("a"));
        Assert.assertEquals("a", StringCleaner.removeWhiteSpaces(" a "));
        Assert.assertEquals("a", StringCleaner.removeWhiteSpaces(" a   "));
        Assert.assertEquals("a", StringCleaner.removeWhiteSpaces(" a    "));
        Assert.assertEquals("", StringCleaner.removeWhiteSpaces(""));
        Assert.assertNull(StringCleaner.removeWhiteSpaces(null));
    }

    @Test
    public void testRemoveAllNonWordCharacters() {
        Assert.assertEquals("a", StringCleaner.removeAllNonWordCharacters("a"));
        Assert.assertEquals("a", StringCleaner.removeAllNonWordCharacters("a&"));
        Assert.assertEquals("a", StringCleaner.removeAllNonWordCharacters("a&!\"§$%&/()=?"));
    }

    @Test
    public void testRemoveAllUsingRegex() {
        Assert.assertEquals("", StringCleaner.removeAllUsingRegex("abcd!\"§$%&/()=?+--.:,;<>|", Regex.ALL));
        Assert.assertEquals("", StringCleaner.removeAllUsingRegex("a", Regex.ALL));
        Assert.assertEquals("", StringCleaner.removeAllUsingRegex("", Regex.ALL));
        Assert.assertEquals("a", StringCleaner.removeAllUsingRegex("a4", Regex.DIGIT));
        Assert.assertEquals("4", StringCleaner.removeAllUsingRegex("a4", Regex.NON_DIGIT));
        Assert.assertEquals("  ", StringCleaner.removeAllUsingRegex("abc 454 ", Regex.NON_WHITESPACE));
        Assert.assertEquals("abcd", StringCleaner.removeAllUsingRegex("abcd!\"§$%&/()=?+--.:,;<>|", Regex.NON_WORD));
        Assert.assertEquals("abcdabc", StringCleaner.removeAllUsingRegex("abcd^1a^2b^3c", Regex.Q3_COLOR_CODE));
        Assert.assertEquals("ab", StringCleaner.removeAllUsingRegex("a b", Regex.WHITESPACE));
        Assert.assertEquals("!\"§$%&/()=?+--.:,;<>|", StringCleaner.removeAllUsingRegex("abcd!\"§$%&/()=?+--.:,;<>|", Regex.WORD));
    }

    @Test
    public void testRemoveAllNotLetter() {
        Assert.assertEquals("a", StringCleaner.removeAllNotLetter("a4"));
        Assert.assertEquals("", StringCleaner.removeAllNotLetter("4"));
        Assert.assertEquals("", StringCleaner.removeAllNotLetter(""));
    }

}
