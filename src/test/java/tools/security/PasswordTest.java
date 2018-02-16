package tools.security;

import org.junit.Assert;
import org.junit.Test;

import de.jawb.tools.security.PasswordScoreCalculator;

public class PasswordTest {
    
    @Test
    public void testPasswordScore() {
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("abc") < 20);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3") > 50);
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3d%4") == 100);
        
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/c3%d") < 100); // kein Grossbuchstabe
        Assert.assertTrue(PasswordScoreCalculator.calculateScore("a1b/C3%a") < 100); // Zwei gleiche Buchstaben
    }
    
}
