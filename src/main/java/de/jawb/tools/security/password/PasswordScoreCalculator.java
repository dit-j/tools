package de.jawb.tools.security.password;

import de.jawb.tools.collections.CollectionsUtil;
import de.jawb.tools.security.password.PasswordAnalysisResult.Bonus;
import de.jawb.tools.security.password.PasswordAnalysisResult.PasswordProperty;
import de.jawb.tools.security.password.PasswordAnalysisResult.Penalty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dit (15.02.2018)
 */
public class PasswordScoreCalculator {

    private static final List<String> BLACK_LIST = Arrays.asList(
                                                    "password", "123456789", "12345678",
                                                    "1234567", "123456", "12345", "1234",
                                                    "123", "qwerty", "abc123", "football",
                                                    "monkey", "letmein", "111111", "1q2w3e4r",
                                                    "google", "1q2w3e4r5t", "123qwe", "zxcvbnm",
                                                    "1q2w3e", "666666", "123321", "suzuki", "yamaha", "honda"
                                             );

    private PasswordScoreCalculator() {
    }

    public static boolean containsBlackListed(char[] password) {
        for (String s : BLACK_LIST) {
            if (CollectionsUtil.contains(password, s.toCharArray())) return true;
        }
        return false;
    }

    public static Set<String> getBlackListedStrings(char[] password){
        Set<String> set = new HashSet<>();
        for (String s : BLACK_LIST) {
            if (CollectionsUtil.contains(password, s.toCharArray())) {
                set.add(s);
            }
        }
        return set;
    }

    @Deprecated // Nutze char[]
    public static PasswordAnalysisResult calculateScore(final String password) {
        return calculateScore(password.toCharArray());
    }

    public static PasswordAnalysisResult calculateScore(final char[] password) {

        PasswordAnalysisResult result = new PasswordAnalysisResult();

        char[] chars = removeBlackListed(password);
        int ignoredChars = password.length - chars.length;
        int countUC = 0, countLC = 0, countNr = 0;
        int countSymbols = 0, countRepeatChars;
        int countNrOrSymInTheMiddle = 0, countRequirements = 0;
        int countConsecutiveLC = 0;
        int countConsecutiveUC = 0;
        int countConsecutiveNr = 0;

        final int length = chars.length;
        final int minPwLength = 8; // req1
        final int minRequirements = 4;
        final Set<Character> uniqueChars   = new HashSet<>();
        final Set<Character> uniqueSymbols = new HashSet<>();

        for (int i = 0; i < length; i++) {
            char ch = chars[i];

            uniqueChars.add(ch);

            boolean lastChar = i == length - 1;

            if (Character.isUpperCase(ch)) { // req2
                countUC++;
                if (!lastChar && Character.isUpperCase(chars[i + 1])) {
                    countConsecutiveUC++;
                }
            } else if (Character.isLowerCase(ch)) { // req3
                countLC++;
                if (!lastChar && Character.isLowerCase(chars[i + 1])) {
                    countConsecutiveLC++;
                }
            } else if (Character.isDigit(ch)) { // req4
                countNr++;
                if (i > 0 && i < length - 1) {
                    countNrOrSymInTheMiddle++;
                }
                if (!lastChar && Character.isDigit(chars[i + 1])) {
                    countConsecutiveNr++;
                }
            } else if (!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch)) { // req5
                countSymbols++;
                if (i > 0 && i < length - 1) {
                    countNrOrSymInTheMiddle++;
                }
                uniqueSymbols.add(ch);
            }
        }

        countRepeatChars = length - uniqueChars.size();

        result.setProperty(PasswordProperty.countAllChars, password.length);
        result.setProperty(PasswordProperty.countIgnoredChars, ignoredChars);
        result.setProperty(PasswordProperty.countRepeatChars, countRepeatChars);
        result.setProperty(PasswordProperty.countRepeatSym, Math.max(0, countSymbols - uniqueSymbols.size()));
        result.setProperty(PasswordProperty.percentageSym, (countSymbols * 100) / password.length);
        result.setProperty(PasswordProperty.countUC, countUC);
        result.setProperty(PasswordProperty.countLC, countLC);
        result.setProperty(PasswordProperty.countNr, countNr);
        result.setProperty(PasswordProperty.countSym, countSymbols);
        result.setProperty(PasswordProperty.countConsecutiveLC, countConsecutiveLC);
        result.setProperty(PasswordProperty.countConsecutiveUC, countConsecutiveUC);
        result.setProperty(PasswordProperty.countConsecutiveNr, countConsecutiveNr);

        // Gesamtanzahl
        if (length >= minPwLength) {
            countRequirements++;
        }

        result.setBonus(Bonus.bonusCharsCount, length * 4);

        // GrossBuchstaben
        if (countUC > 0) {
            countRequirements++;
            result.setBonus(Bonus.bonusUC, (length - countUC) * 2);
        }

        // Kleinbuchstaben
        if (countLC > 0) {
            countRequirements++;
            result.setBonus(Bonus.bonusLC, (length - countLC) * 2);
        }

        // Nummern
        if (countNr > 0) {
            countRequirements++;
            result.setBonus(Bonus.bonusNr, countNr * 4);
        }

        // Sonderzeichen
        if (countSymbols > 0) {
            countRequirements++;
            if (countUC > 0 || countLC > 0) {
                result.setBonus(Bonus.bonusSymbols, countSymbols * 6);
            }
        }

        // Nummer oder Sonderzeichen in der Mitte des Passwortes
        if (countNrOrSymInTheMiddle > 0) {
            result.setBonus(Bonus.bonusSymbolsOrNrInTheMiddle, countNrOrSymInTheMiddle * 2);
        }

        // Anforderungen
        if (countRequirements >= minRequirements) {
            result.setBonus(Bonus.bonusMinRequirements, countRequirements * 2);
        }

        //
        // ABZUEGE
        //

        // Nur Klein- oder Grossbuchstaben
        if ((countLC > 0 || countUC > 0) && countNr == 0 && countSymbols == 0) {
            result.setPenalty(Penalty.penaltyLettersOnly, length);
        }

        // Nur Zahlen
        if (countNr > 0 && countLC == 0 && countUC == 0 && countSymbols == 0) {
            result.setPenalty(Penalty.penaltyNumbersOnly, length * 4);
        }

        // Mehrere Kleinbuchstaben hintereinander
        if (countConsecutiveLC > 0) {
            result.setPenalty(Penalty.penaltyConsecutiveLC, countConsecutiveLC * 2);
        }

        // Mehrere Grossbuchstaben hintereinander
        if (countConsecutiveUC > 0) {
            result.setPenalty(Penalty.penaltyConsecutiveUC, countConsecutiveUC * 2);
        }
        // Mehrere Zahlen hintereinander
        if (countConsecutiveNr > 0) {
            result.setPenalty(Penalty.penaltyConsecutiveNr, countConsecutiveNr * 2);
        }
        // Zeichen die mehrmals vorkommen
        if (countRepeatChars > 0) {
            result.setPenalty(Penalty.penaltyRepeatChars, countRepeatChars * 2);
        }

        return result; //
    }

    static char[] removeBlackListed(char[] password) {
        char[] temp = password;
        for (String s : BLACK_LIST) {
            temp = CollectionsUtil.remove(temp, s.toCharArray());
        }
        return temp;
    }

}
