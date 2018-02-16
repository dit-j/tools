package de.jawb.tools.security;

/**
 * @author dit (15.02.2018)
 */
public class PasswordScoreCalculator {

    /**
     * Liefert Passwortstaerke von 0 bis 100%
     * @param password
     * @return Passwortstaerke in Prozent
     */
    public static int calculateScore(String password){
        int score    = 0;
        char[] chars = password.toCharArray();
        int countUC  = 0, countLC = 0, countNr = 0, countSym = 0, countRepeatChars = 0, countNrOrSymInTheMiddle = 0, countRequirements = 0;
        int countConsecutiveLC = 0;
        int countConsecutiveUC = 0;
        int countConsecutiveNr = 0;
        
        final int length          = chars.length;
        final int minPwLength     = 8; // req1
        final int minRequirements = (length >= minPwLength) ? 3 : 4;

        for (int i = 0; i < length; i++){
            char ch = chars[i];
            boolean lastChar = i == length - 1;
            
            if(Character.isUpperCase(ch)){          // req2
                countUC++;
                if(!lastChar && Character.isUpperCase(chars[i + 1])){
                    countConsecutiveUC++;
                }
            } else if(Character.isLowerCase(ch)) {  // req3
                countLC++;
                if(!lastChar && Character.isLowerCase(chars[i + 1])){
                    countConsecutiveLC++;
                }
            } else if(Character.isDigit(ch)) {      // req4
                countNr++;
                if(i > 0 && i < length - 1){
                    countNrOrSymInTheMiddle++;
                }
                if(!lastChar && Character.isDigit(chars[i + 1])){
                    countConsecutiveLC++;
                }
            } else if(!Character.isLetterOrDigit(ch) && !Character.isWhitespace(ch)){ // req5
                countSym++;
                if(i > 0 && i < length - 1){
                    countNrOrSymInTheMiddle++;
                }
            }
            //
            for(int j = 0; j < length; j++){
                if(ch == chars[j] && i != j){
                    countRepeatChars++;
                }
            }
        }

        if(length >= minPwLength) countRequirements = 1;
        if(countLC > 0)  countRequirements++;
        if(countUC > 0)  countRequirements++;
        if(countNr > 0)  countRequirements++;
        if(countSym > 0) countRequirements++;

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
        if(countRequirements >= minRequirements){
            score = score + countRequirements * 2;
        }

        // Nur Klein- oder Grossbuchstaben
        if((countLC > 0 || countUC > 0) && countNr == 0 && countSym == 0){
            score = score - length;
        }
        
        // Nur Zahlen
        if(countNr > 0 && countLC == 0 && countUC == 0 && countNr == 0 && countSym == 0){
            score = score - length;
        }
        

        // Mehrere Kleinbuchstaben hintereinander
        if(countConsecutiveLC > 0){
            score = score - countConsecutiveLC * 2;
        }
        // Mehrere Grossbuchstaben hintereinander
        if(countConsecutiveUC > 0){
            score = score - countConsecutiveUC * 2;
        }
        // Mehrere Zahlen hintereinander
        if(countConsecutiveNr > 0){
            score = score - countConsecutiveNr * 2;
        }
        // Zeichen die mehrmals vorkommen
        if(countRepeatChars > 0){
            score = score - countRepeatChars * 4;
        }

        return score < 0 ? 0 : Math.min(score, 100);
    }
    
}
