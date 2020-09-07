package tools.security;

import de.jawb.tools.security.password.PasswordAnalysisResult.PasswordProperty;
import de.jawb.tools.security.password.PasswordScoreCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

public class PasswordScoreCalculatorTest {

    @Test
    public void testPasswordBlackList_1() {
        Assert.assertEquals(new HashSet<>(Arrays.asList("123")), PasswordScoreCalculator.getBlackListedStrings("gnvhf123"));
        Assert.assertEquals(new HashSet<>(Arrays.asList("123", "letmein")), PasswordScoreCalculator.getBlackListedStrings("gletmeinnvhf123"));
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
