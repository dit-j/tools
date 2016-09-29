/**
 * 
 */
package de.jawb.tools.string;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author dit (13.08.2012)
 */
public class SecStringUtil {
    
    public static final String CHARS         = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static final String CHARS_LC      = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String CHARS_UC      = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String CHARS_SPECIAL = "()[]{}?!$%&/=*+~,.;:<>-_";
    public static final String CHARS_ALL     = CHARS + CHARS_SPECIAL;
                                             
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
     * Generiert ein zufaelliges Passwort mit spez. Zeichen
     * (optional)
     * 
     * @param length
     *            Laenge des Passworts
     * @param useSpecialCharacter
     *            Sonderzeichen:
     *            <tt>()[]{}?!$%&/=*+~,.;:<>-_</tt>
     * @return Passwort
     */
    public static String generatePassword(int length, boolean useSpecialCharacter) {
        String chars;
        if (useSpecialCharacter) {
            chars = CHARS_ALL;
        } else {
            chars = CHARS;
        }
        StringBuilder sb = new StringBuilder();
        Random r = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int j = r.nextInt(chars.length());
            sb.append(chars.charAt(j));
        }
        return sb.toString();
    }
    
    /**
     * Generiert eine "zufaellige" Zeichenkette der Laenge
     * 40.
     * 
     * @return z.B.
     *         <tt>4nwaxr26uz47lgc6bqj8dzv37n7ryqedmvfvpcru</tt>
     */
    public static String generateToken() {
        return generateToken(40);
    }
    
    /**
     * Generiert eine "zufaellige" Zeichenkette.
     * 
     * @param length
     *            Laenge der Kette
     * @return z.B.
     *         <tt>4nwaxr26uz47lgc6bqj8dzv37n7ryqedmvfvpcru</tt>
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
     * Generiert eine "zufaellige" Zeichenkette die aus X
     * Bloecken besteht je 5 Zeichen.
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
     * Erzeugt aus einem String einen Integer. String muss
     * genau vie Zeichen lang sein.
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
     * Maskiert ein Password. Erste 3 Zeichen bleiben offen,
     * der Rest wird durch mehrere Sternchen ersetzt.
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
    
    public static void main(String[] args) throws Exception {
        // String pass = SecurityUtil.generatePassword(10);
        // String dfpw = pass.substring(0, 6);
        // String salt = pass.substring(6);
        // int saltInt = SecurityUtil.stringToSalt(salt);
        //
        // System.out.println(pass);
        // System.out.println(dfpw);
        // System.out.println(salt);
        // System.out.println(saltInt);
        
        // int s = stringToSalt("t3vc");
        // System.out.println(s);
        // String a = saltToString(s);
        // System.out.println(a);
        
    }
}
