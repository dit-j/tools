package de.jawb.tools.string;

import java.util.Locale;

/**
 * @author dit (24.03.2011)
 */
public class StringUtil {

    /**
     * Kuerzt einen String auf eine bestimmte Laenge.
     *
     * @param strTocut
     *            String-Objekt
     * @param maxLength
     *            die Laenge auf die gekuerzt werden soll.
     * @return gekuerzter String.
     */
    public static String cutStringToMaxLength(String strTocut, int maxLength) {
        if (strTocut.length() > maxLength) {
            return strTocut.substring(0, maxLength) + "...";
        }
        return strTocut;
    }

    /**
     * Rotiert ein String um eine Position.
     *
     * @param str
     *            ein String
     * @return aus [a][b][c] wird [c][a][b]
     */
    public static String rotate(String str) {
        char[] buffer = new char[str.length()];
        buffer[0] = str.charAt(str.length() - 1);
        System.arraycopy(str.toCharArray(), 0, buffer, 1, str.length() - 1);
        return new String(buffer);
    }

    /**
     * Falls ein String "" oder null ist gibt die Methode ein <code>null</code> zurück.
     *
     * @param str
     * @return <code>null</code> oder str
     */
    public static String emptyToNull(String str) {
        if (isEmpty(str)) {
            return null;
        }
        return str;
    }

    public static String trim(String str) {
        if(str == null){
            return null;
        }
        return str.trim();
    }

    public static String trimToNull(String str) {
        String trimmed = trim(str);
        if (isEmpty(trimmed)) {
            return null;
        }
        return trimmed;
    }

    /**
     * Aus einem String 'blaBla' wird 'BlaBla'
     *
     * @param str
     * @return
     */
    public static String firstCharToUpperCase(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    /**
     * Prueft ob ein String null oder Leer ist
     *
     * @param str
     *            ein String
     * @return <code>true</code> wenn String NICHT leer ist
     */
    public static boolean notEmpty(String str) {
        return (str != null) && !str.isEmpty();
    }

    /**
     * Sucht in dem Array nach einem leeren String. Verwendet die {@link #isEmpty(String)} - Methode.
     *
     * @param strings
     *            Ein String-Array
     * @return index des ersten leeren Strings
     */
    public static int findEmpty(String... strings) {
        if (strings == null) {
            return 0;
        }
        for (int i = 0; i < strings.length; i++) {
            if (isEmpty(strings[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prueft ob alle String in dem Array nicht leere Strings sind.
     *
     * @param strings
     *            String-Array
     * @return <code>true</code> wenn alle String nicht leer sind
     */
    public static boolean allNotEmpty(String... strings) {
        return findEmpty(strings) < 0;
    }

    public static boolean allEmpty(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (notEmpty(strings[i]))
                return false;
        }
        return true;
    }

    /**
     * Macht das selbe wie {@link #notEmpty(String)}<br>
     * Sorgt aber fuer bessere Lesbarkeit im Kode
     *
     * @param str
     *            ein String
     * @return <code>true</code> wenn String <code>null</code> oder leer ist
     */
    public static boolean isEmpty(String str) {
        return !notEmpty(str);
    }

    public static boolean isEmptyTrim(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Prueft ob ein String gueltige Laenge hat
     *
     * @param str
     *            der zu pruefende String
     * @param min
     *            Minimale Anzahl der Zeichen <i>(bei -1 wird die minimale Laenge ignoriert)</i>
     * @param max
     *            Maximale Anzahl der Zeichen <i>(bei -1 wird die maximale Laenge ignoriert)</i>
     * @return <code>true</code> wenn String eine gültige Laenge hat
     */
    public static boolean isValidLength(String str, int min, int max) {
        if (notEmpty(str)) {
            int l = str.length();
            if (((min >= 0) && (l < min)) || ((max > 0) && (l > max))) {
                return false;
            }
            return true;
        }
        return (str != null) && (min < 0);
    }

    public static String getFirstNotNull(String... strs) {
        for (String string : strs) {
            if (notEmpty(string)) {
                return string;
            }
        }
        return null;
    }

    public static String getNonEmptyString(String str, String defaultIfEmpty) {
        if (isEmpty(str)) {
            return defaultIfEmpty;
        }
        return str;
    }

    public static String toLowerCase(String text, String lang) {
        try {

            Locale l = null;
            if ("de".equalsIgnoreCase(lang)) {
                l = Locale.GERMAN;
            } else if ("en".equalsIgnoreCase(lang)) {
                l = Locale.ENGLISH;
            } else if ("fr".equalsIgnoreCase(lang)) {
                l = Locale.FRENCH;
            } else if ("it".equalsIgnoreCase(lang)) {
                l = Locale.ITALIAN;
            } else {
                l = new Locale(lang);
            }
            return text.toLowerCase(l);

        } catch (Exception ex) {
            return text.toLowerCase();
        }
    }
}
