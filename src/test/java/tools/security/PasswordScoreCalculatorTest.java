package tools.security;

import org.junit.Assert;
import org.junit.Test;

import de.jawb.tools.security.PasswordScoreCalculator;

public class PasswordScoreCalculatorTest {
    
    @Test
    public void testPasswordLCCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("ABC").countLC);
        Assert.assertEquals(3, PasswordScoreCalculator.calculateScore("abc").countLC);
        Assert.assertEquals(3, PasswordScoreCalculator.calculateScore("a1b3cC").countLC);
        Assert.assertEquals(3, PasswordScoreCalculator.calculateScore("a$9b#Lc").countLC);
    }
    
    @Test
    public void testPasswordUCCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("abc").countUC);
        Assert.assertEquals(1, PasswordScoreCalculator.calculateScore("a1b3cC").countUC);
        Assert.assertEquals(2, PasswordScoreCalculator.calculateScore("A$9b#Lc").countUC);
    }

    @Test
    public void testPasswordNRCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("abc").countNr);
        Assert.assertEquals(2, PasswordScoreCalculator.calculateScore("a1b3cC").countNr);
        Assert.assertEquals(2, PasswordScoreCalculator.calculateScore("a$9b#L1c").countNr);
        Assert.assertEquals(4, PasswordScoreCalculator.calculateScore("a$9b#L1c4d2").countNr);
        Assert.assertEquals(1, PasswordScoreCalculator.calculateScore("H?!Co-.1").countNr);
    }

    @Test
    public void testPasswordSymCount() {
        Assert.assertEquals(0, PasswordScoreCalculator.calculateScore("abc").countSym);
        Assert.assertEquals(2, PasswordScoreCalculator.calculateScore("a$9b#L1c").countSym);
        Assert.assertEquals(3, PasswordScoreCalculator.calculateScore("a$9b#L1_2").countSym);
        Assert.assertEquals(4, PasswordScoreCalculator.calculateScore("H?!Co-.1").countSym);
    }
    
    @Test
    public void testPasswordScore() {
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("abc").score < 20);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3").score > 50);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3d%4").score == 100);
        
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/c3%d").score < 100); 
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3%a").score < 100); 
    }
    
}
