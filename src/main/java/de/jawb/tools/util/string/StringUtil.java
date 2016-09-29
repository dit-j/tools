package de.jawb.tools.util.string;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import de.jawb.tools.util.io.file.FileAccess;

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
     * Sucht in dem Array nach einem leeren String.
     * Verwendet die {@link #isEmpty(String)} - Methode.
     * 
     * @param strings
     *            Ein String-Array
     * @return index des ersten leeren Strings
     */
    public static int findEmpty(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (isEmpty(strings[i])) return i;
        }
        return -1;
    }
    
    /**
     * Prueft ob alle String in dem Array nicht leere
     * Strings sind.
     * 
     * @param strings
     *            String-Array
     * @return <code>true</code> wenn alle String nicht leer
     *         sind
     */
    public static boolean allNotEmpty(String... strings) {
        return findEmpty(strings) < 0;
    }
    
    public static boolean allEmpty(String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (notEmpty(strings[i])) return false;
        }
        return true;
    }
    
    /**
     * Macht das selbe wie {@link #notEmpty(String)}<br>
     * Sorgt aber fuer bessere Lesbarkeit im Kode
     * 
     * @param str
     *            ein String
     * @return <code>true</code> wenn String
     *         <code>null</code> oder leer ist
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
     *            Minimale Anzahl der Zeichen <i>(bei -1
     *            wird die minimale Laenge ignoriert)</i>
     * @param max
     *            Maximale Anzahl der Zeichen <i>(bei -1
     *            wird die maximale Laenge ignoriert)</i>
     * @return <code>true</code> wenn String eine gültige
     *         Laenge hat
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
    
    public static void main(String[] args) throws IOException {
        
        Set<String> newLines = new LinkedHashSet<>();
        newLines.add("<html><body><div>");
        int j = 0;
        List<String> lines = FileAccess.getContentByLine(new File("d:\\xing.txt"));
        for (String l : lines) {
            
            String trimmed = l.trim();
            
            if (trimmed.length() > 0) {
                
                int i = trimmed.indexOf('?');
                if (i > 0) {
                    trimmed = trimmed.substring(0, i);
                }
                newLines.add("<a onclick=\"this.parentNode.removeChild(this)\" target=\"_blank\" href=\"" + trimmed + "\">" + j + "&nbsp;&nbsp;" + trimmed + "<br></a>");
                System.out.println(trimmed);
                ++j;
            }
        }
        newLines.add("</div></body></html>");
        
        FileAccess.writeContent(new File("d:\\xing.html"), new ArrayList<>(newLines), false);
        
    }
}