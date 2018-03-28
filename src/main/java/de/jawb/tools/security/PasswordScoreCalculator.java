package de.jawb.tools.security;

import java.util.Arrays;
import java.util.List;

/**
 * @author dit (15.02.2018)
 */
public class PasswordScoreCalculator {
    
    private static List<String> BLACK_LIST = Arrays.asList(
            "password", 
            "123456789",
            "12345678",
            "1234567",
            "123456",
            "12345",
            "1234",
            "123",
            "qwerty",
            "abc123",
            "football",
            "monkey",
            "letmein",
            "111111",
            "1q2w3e4r",
            "google",
            "1q2w3e4r5t",
            "123qwe",
            "zxcvbnm",
            "1q2w3e",
            "666666",
            "123321",
            "suzuki",
            "yamaha",
            "honda"
    );
    
    
    public static class PasswordScoreInfo {
        
        public final int countUC, countLC, countNr, countSym, countRepeatChars;
        public final int countConsecutiveLC, countConsecutiveUC, countConsecutiveNr;
        public final int score;
        
        public PasswordScoreInfo(int countUC, int countLC, int countNr, int countSym, int countRepeatChars, int countConsecutiveLC, int countConsecutiveUC, int countConsecutiveNr, int score) {
            super();
            this.countUC = countUC;
            this.countLC = countLC;
            this.countNr = countNr;
            this.countSym = countSym;
            this.countRepeatChars = countRepeatChars;
            this.countConsecutiveLC = countConsecutiveLC;
            this.countConsecutiveUC = countConsecutiveUC;
            this.countConsecutiveNr = countConsecutiveNr;
            this.score = score;
        }

        @Override
        public String toString() {
            return "PasswordScoreInfo [score=" + score + ", countUC=" + countUC + ", countLC=" + countLC + ", countNr=" + countNr + ", countSym=" + countSym + "]";
        }
        
        
    }
    
    private static String removeBlackListed(String password){
        String temp = password;
        for(String s : BLACK_LIST){
            if(temp.contains(s)) temp = temp.replaceAll(s, "");
        }
        return temp;
    }
    
    public static boolean containsBlackListed(String password){
        for(String s : BLACK_LIST){
            if(password.contains(s)) return true;
        }
        return false;
    }
    
    /**
     * Liefert Passwortstaerke von 0 bis 100%
     * 
     * @param password
     * @return Passwortstaerke in Prozent
     */
    public static PasswordScoreInfo calculateScore(String password) {
        
        password = removeBlackListed(password);
        
        int score = 0;
        char[] chars = password.toCharArray();
        int countUC = 0, countLC = 0, countNr = 0, countSym = 0, countRepeatChars = 0, countNrOrSymInTheMiddle = 0, countRequirements = 0;
        int countConsecutiveLC = 0;
        int countConsecutiveUC = 0;
        int countConsecutiveNr = 0;
        
        final int length = chars.length;
        final int minPwLength = 8; // req1
        final int minRequirements = (length >= minPwLength) ? 3 : 4;
        
        for (int i = 0; i < length; i++) {
            char ch = chars[i];
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
                countSym++;
                if (i > 0 && i < length - 1) {
                    countNrOrSymInTheMiddle++;
                }
            }
            //
            for (int j = 0; j < length; j++) {
                if (ch == chars[j] && i != j) {
                    countRepeatChars++;
                }
            }
        }
        
        if (length >= minPwLength) countRequirements = 1;
        if (countLC > 0) countRequirements++;
        if (countUC > 0) countRequirements++;
        if (countNr > 0) countRequirements++;
        if (countSym > 0) countRequirements++;
        
        // Gesamtanzahl
        score = score + length * 4;
        
        // GrossBuchstaben
        score = score + (length - countUC) * 2;
        
        // Kleinbuchstaben
        score = score + (length - countLC) * 2;
        
        // Nummern
        score = score + countNr * 4;
        
        // Sonderzeichen
        score = score + countSym * 6;
        
        // Nummer oder Sonderzeichen in der Mitte des Passwortes
        score = score + countNrOrSymInTheMiddle * 2;
        
        // Anforderungen
        if (countRequirements >= minRequirements) {
            score = score + countRequirements * 2;
        }
        
        // Nur Klein- oder Grossbuchstaben
        if ((countLC > 0 || countUC > 0) && countNr == 0 && countSym == 0) {
            score = score - length;
        }
        
        // Nur Zahlen
        if (countNr > 0 && countLC == 0 && countUC == 0 && countSym == 0) {
            score = score - length * 4;
        }
        
        // Mehrere Kleinbuchstaben hintereinander
        if (countConsecutiveLC > 0) {
            score = score - countConsecutiveLC * 2;
        }
        // Mehrere Grossbuchstaben hintereinander
        if (countConsecutiveUC > 0) {
            score = score - countConsecutiveUC * 2;
        }
        // Mehrere Zahlen hintereinander
        if (countConsecutiveNr > 0) {
            score = score - countConsecutiveNr * 5;
        }
        // Zeichen die mehrmals vorkommen
        if (countRepeatChars > 0) {
            score = score - countRepeatChars * 4;
        }
        
        return new PasswordScoreInfo( //
                countUC, //
                countLC, //
                countNr, //
                countSym, //
                countRepeatChars, //
                countConsecutiveLC, //
                countConsecutiveUC, //
                countConsecutiveNr, //
                score < 0 ? 0 : Math.min(score, 100)); //
    }
    
}
