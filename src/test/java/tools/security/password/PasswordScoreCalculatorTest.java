package tools.security.password;

import de.jawb.tools.security.password.PasswordAnalysisResult.PasswordProperty;
import de.jawb.tools.security.password.PasswordScoreCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PasswordScoreCalculatorTest {

    @Test
    public void testPasswordUnsafeParts_1() {

        List<String> unsafeParts = PasswordScoreCalculator.getUnsafeParts("123456123guntletmeinigjh".toCharArray());

        Assert.assertEquals(3,          unsafeParts.size());

        Assert.assertEquals("123456",   unsafeParts.get(0));
        Assert.assertEquals("123",      unsafeParts.get(1));
        Assert.assertEquals("letmein",  unsafeParts.get(2));

    }

    @Test
    public void testPasswordUnsafeParts_2() {
        Assert.assertEquals(Collections.emptyList(), PasswordScoreCalculator.getUnsafeParts("fuRn4d".toCharArray()));
        Assert.assertEquals(Arrays.asList("123456"), PasswordScoreCalculator.getUnsafeParts("123456guntigjh".toCharArray()));
        Assert.assertEquals(Arrays.asList("123456", "123", "letmein"), PasswordScoreCalculator.getUnsafeParts("123456123guntletmeinigjh".toCharArray()));
        Assert.assertEquals(Arrays.asList("123", "letmein"), PasswordScoreCalculator.getUnsafeParts("123letmein".toCharArray()));
        Assert.assertEquals(Arrays.asList("123"), PasswordScoreCalculator.getUnsafeParts("gnvhf123".toCharArray()));
        Assert.assertEquals(Arrays.asList("123", "letmein"), PasswordScoreCalculator.getUnsafeParts("gletmeinnvhf123".toCharArray()));
    }

    @Test
    public void testPasswordLCCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("ABC").property(PasswordProperty.countLC));
        Assert.assertEquals(3, PasswordScoreCalculator.calculateScore("abc").property(PasswordProperty.countLC));
        Assert.assertEquals(3, PasswordScoreCalculator.calculateScore("a1b3cC").property(PasswordProperty.countLC));
        Assert.assertEquals(3, PasswordScoreCalculator.calculateScore("a$9b#Lc").property(PasswordProperty.countLC));
    }

    @Test
    public void testPasswordUCCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("abc").property(PasswordProperty.countUC));
        Assert.assertEquals(1, PasswordScoreCalculator.calculateScore("a1b3cC").property(PasswordProperty.countUC));
        Assert.assertEquals(2, PasswordScoreCalculator.calculateScore("A$9b#Lc").property(PasswordProperty.countUC));
    }

    @Test
    public void testPasswordNRCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("abc").property(PasswordProperty.countNr));
        Assert.assertEquals(2, PasswordScoreCalculator.calculateScore("a1b3cC").property(PasswordProperty.countNr));
        Assert.assertEquals(2, PasswordScoreCalculator.calculateScore("a$9b#L1c").property(PasswordProperty.countNr));
        Assert.assertEquals(4, PasswordScoreCalculator.calculateScore("a$9b#L1c4d2").property(PasswordProperty.countNr));
        Assert.assertEquals(1, PasswordScoreCalculator.calculateScore("H?!Co-.1").property(PasswordProperty.countNr));
    }

    @Test
    public void testPasswordSymCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("abc").property(PasswordProperty.countSym));
        Assert.assertEquals(2, PasswordScoreCalculator.calculateScore("a$9b#L1c").property(PasswordProperty.countSym));
        Assert.assertEquals(3, PasswordScoreCalculator.calculateScore("a$9b#L1_2").property(PasswordProperty.countSym));
        Assert.assertEquals(4, PasswordScoreCalculator.calculateScore("H?!Co-.1").property(PasswordProperty.countSym));
    }

    @Test
    public void testPasswordUniqueSymCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("a$9b#L1c").property(PasswordProperty.countRepeatSym));
        Assert.assertEquals(1, PasswordScoreCalculator.calculateScore("a+9b-+1c").property(PasswordProperty.countRepeatSym));
    }

    @Test
    public void testPasswordPercentageSymbols() {
        Assert.assertEquals(100, PasswordScoreCalculator.calculateScore("+-&/%").property(PasswordProperty.percentageSym));
        Assert.assertEquals( 50, PasswordScoreCalculator.calculateScore("abcd+-%&").property(PasswordProperty.percentageSym));
        Assert.assertEquals( 33, PasswordScoreCalculator.calculateScore("abcdef-%&").property(PasswordProperty.percentageSym));
    }

    @Test
    public void testPasswordScore() {

        Assert.assertTrue(PasswordScoreCalculator.calculateScore("12345").score() < 10);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("123456").score() < 10);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("1234567").score() < 10);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("abc").score() < 20);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3").score() > 40);

        Assert.assertTrue(PasswordScoreCalculator.calculateScore("156843279451358").score() > 40);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("2958344913546871413587993462897").score() > 70);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("2958344913546871413587993462897").score() < 90);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("21958344914354515209968714135879923462897").score() > 90);

        Assert.assertTrue(PasswordScoreCalculator.calculateScore("aTTG24Y").score() >= 50);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("rGwNkOP").score() > 30);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("rGwNkOP").score() < 50);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("r1nF2d6Z").score() >= 70);   // 8 Zeichen ohne Sonderzeichen
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3d%4").score() > 95); // 9 Zeichen mit Sonderzeichen

        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/c3%d").score() < 80);   // 8 Zeichen mit Sonderzeichen
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3%a").score() < 100);   // 8 Zeichen mit Sonderzeichen
    }

}
