/**
 *
 */
package de.jawb.tools.security;

import de.jawb.tools.security.password.PasswordAnalysisResult;
import de.jawb.tools.security.password.PasswordAnalysisResult.PasswordProperty;
import de.jawb.tools.security.password.PasswordScoreCalculator;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author dit (13.08.2012)
 */
public class Generator {

    public static final String CHARS_UP_LC_NR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final String CHARS_LC       = "abcdefghijklmnopqrstuvwxyz";
    public static final String CHARS_UC       = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String CHARS_NR       = "0123456789";
    public static final String CHARS_LC_UC    = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    public static final String CHARS_SPECIAL  = "()[]{}?!$%&/*+~-,.;:<=>_|@#";
    public static final String CHARS_ALL      = CHARS_UP_LC_NR + CHARS_SPECIAL;

    /**
     * Generiert ein zufaelliges Passwort
     *
     * @param length
     *            Laenge des Passworts
     * @return Passwort
     */
    public static String generatePassword(int length) {
        return generatePassword(length, false);
    }

    /**
     * Generiert ein zufaelliges Passwort mit spez. Zeichen (optional)
     *
     * @param length
     *            Laenge des Passworts
     * @param useSymbols
     *            Sonderzeichen: <tt>()[]{}?!$%&/=*+~,.;:<>-_</tt>
     * @return Passwort
     */
    public static String generatePassword(final int length, boolean useSymbols) {
        final StringBuilder sb = new StringBuilder();
        final Random r = new SecureRandom();

        if (length >= 4) {

            int minStrength = useSymbols ? Math.min(length * 10, 100) : Math.min((length * 10) - 10, 100);
            boolean done = false;
            do {
                PasswordAnalysisResult si = createPrettyPassword(r, sb, length, useSymbols);
                int countLC = si.property(PasswordProperty.countLC);
                int countUC = si.property(PasswordProperty.countUC);
                int countNr = si.property(PasswordProperty.countNr);
                int score   = si.score();
                done = countLC > 0 && countUC > 0 && countNr > 0 && score >= minStrength;
            } while (!done);

        } else {
            final String chars = useSymbols ? CHARS_ALL : CHARS_UP_LC_NR;
            for (int i = 0; i < length; i++) {
                sb.append(getRandomFromString(r, chars));
            }
        }

        return sb.toString();
    }

    private static PasswordAnalysisResult createPrettyPassword(Random r, StringBuilder sb, int length, boolean sym) {
        if (sb.length() > 0) sb.setLength(0);

        sb.append(getRandomFromString(r, CHARS_LC_UC));

        int lastType = 0;
        int nextType = 0;
        String src = null;
        int maxCharType = sym ? 4 : 3;

        for (int i = 0; i < length - 2; i++) {

            while ((nextType = r.nextInt(maxCharType)) == lastType)
                ;

            if (nextType == 0) {
                src = CHARS_LC_UC;
            } else if (nextType == 1) {
                src = CHARS_LC_UC;
            } else if (nextType == 2) {
                src = CHARS_NR;
            } else if (nextType == 3) {
                src = CHARS_SPECIAL;
            }
            lastType = nextType;
            sb.append(getRandomFromString(r, src));
        }

        sb.append(getRandomFromString(r, CHARS_LC_UC));

        return PasswordScoreCalculator.calculateScore(sb.toString().toCharArray());
    }

    private static char getRandomFromString(Random r, String src) {
        return src.charAt(r.nextInt(src.length()));
    }

    /**
     * Generiert eine "zufaellige" Zeichenkette der Laenge 40.
     *
     * @return z.B. <tt>4nwaxr26uz47lgc6bqj8dzv37n7ryqedmvfvpcru</tt>
     */
    public static String generateToken() {
        return generateToken(40);
    }

    /**
     * Generiert eine "zufaellige" Zeichenkette.
     *
     * @param length
     *            Laenge der Kette
     * @return z.B. <tt>4nwaxr26uz47lgc6bqj8dzv37n7ryqedmvfvpcru</tt>
     */
    public static String generateToken(int length) {
        StringBuilder sb = new StringBuilder();
        Random r = new SecureRandom();
        for (int i = 0; i < length; ++i) {
            int j = r.nextInt(CHARS_LC.length());
            sb.append(CHARS_LC.charAt(j));
        }
        return sb.toString();
    }

    /**
     * Generiert eine "zufaellige" Zeichenkette die aus X Bloecken besteht je 5
     * Zeichen.
     *
     * @param partsCount
     *            Anzahl der Bloecke
     * @return z.B. <tt>8G4YU-LQY03-B6V6J-8YALP-L7W0F</tt>
     */
    public static String generateBlockToken(int partsCount) {
        StringBuilder sb = new StringBuilder();
        Random r = new SecureRandom();
        int charsInBlock = 5;
        for (int b = 0; b < partsCount; b++) {
            for (int i = 0; i < charsInBlock; i++) {
                int j = r.nextInt(CHARS_UC.length());
                sb.append(CHARS_UC.charAt(j));
            }
            if (b != (partsCount - 1)) {
                sb.append("-");
            }
        }
        return sb.toString();
    }

    /**
     * Erzeugt aus einem String einen Integer. String muss genau vier Zeichen
     * lang sein.
     *
     * @param str
     *            ein String der aus 4 Zeichen besteht
     * @return Integer der diesen String repraesenstiert
     */
    public static int stringToSalt(String str) {

        if (str.length() > 4) {
            throw new IllegalArgumentException("str to int. max 4 chars");
        }

        byte[] salt = str.getBytes();
        int a = 0;
        int s = 24;
        for (byte b : salt) {
            a += (b << s);
            s -= 8;
        }
        return a;
    }

    /**
     * Wandelt einen Integer in einen String um
     *
     * @param salt
     *            Integer der einen String repraesenstiert
     * @return String der aus diesem Integer errechnet wurde
     */
    public static String saltToString(int salt) {
        byte[] temp = new byte[4];
        temp[0] = (byte) (salt >> 24);
        temp[1] = (byte) (salt >> 16);
        temp[2] = (byte) (salt >> 8);
        temp[3] = (byte) (salt);
        return new String(temp);
    }

    /**
     * Maskiert ein Password. Erste 3 Zeichen bleiben offen, der Rest wird durch
     * mehrere Sternchen ersetzt.
     *
     * @param password
     *            Password.
     * @return mypassword -> myp************
     */
    public static String maskPassword(String password) {
        String stars = "*******";
        if (password.length() > 3) {
            String first_part = password.substring(0, 3);
            return first_part + stars;
        }
        return stars;
    }
}
