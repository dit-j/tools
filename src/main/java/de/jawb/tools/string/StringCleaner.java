package de.jawb.tools.string;

/**
 * Ein globaler HilfsCleaner.
 * 
 * @author dit (23.09.2010)
 */
public class StringCleaner {
    
    /**
     * Entfernt doppelte Leerzeichen.
     * 
     * @param s
     *            String
     * @return Zeichenkette ohne doppelten Leerzeichen.
     */
    public static String removeDoubleWhiteSpaces(String s) {
        if (s == null) {
            return null;
        }
        if (s.contains("  ")) {
            return removeDoubleWhiteSpaces(s.replaceAll("  ", " "));
        }
        //TODO keine Konflikte?
        else if (s.contains("\t")) {
            return removeDoubleWhiteSpaces(s.replaceAll("\t", " "));
        } else {
            return s;
        }
    }
    
    
    public static String removeTrailingString(String str, String trailingString) {
        if(StringUtil.notEmpty(str)) {
            if (str.endsWith(trailingString)) {
                return str.substring(0, str.length() - trailingString.length());
            }
        }
        return str;
    }
    
    
    /**
     * Entfernt <b>alle</b> Leerzeichen aus einem String.
     * 
     * @param src
     *            String mit Leerzeichen.
     * @return Zeichenkette ohne LeerZeichen
     */
    public static String removeWhiteSpaces(String src) {
        if (src == null) {
            return null;
        }
        
        if (src.contains(" ")) {
            return src.replaceAll(Regex.WHITESPACE.regex, "");
        } else {
            return src;
        }
    }
    
    /**
     * Laesst nur Buchstaben,Ziffer und '_' Zeichen in dem String.
     * 
     * @param s
     *            String to clean
     * @return "gereinigter" String
     */
    public static String removeAllNonWordCharacters(String s) {
        return s.replaceAll(Regex.NON_WORD.regex, "");
    }
    
    /**
     * Laesst nur die Zeichen die in einem Regulaeren Ausdruck definiert sind.
     * 
     * @param string
     *            String
     * @param regex
     *            Regulaerer Ausdruck s. {@link Regex}
     * @return "gereinigter" String
     */
    public static String removeAllUsingRegex(String string, Regex regex) {
        return string.replaceAll(regex.regex, "");
    }
    
    /**
     * Laesst nur Buchstaben uebrig.
     * 
     * @param s
     *            Zeichenkette die bearbeitet werden soll.
     * @return Zeichenkette die nur aus Buchstaben besteht.
     */
    public static String removeAllNotLetter(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (Character.isLetter(ch)) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
    
    /**
     * Entfernt alle unnoetigen Zeichen aus dem String der als KnotenName
     * verwendet wird.
     * 
     * @param string
     *            String mit FarbeCodes oder SonderZeichen.
     * @return ein String vom Typ {@link Regex#NON_WORD} in "toLowerCase"
     */
    public static String createValideFileName(String string) {
        String newString = string.replaceAll(Regex.Q3_COLOR_CODE.regex, "");
        newString = newString.replaceAll(Regex.NON_WORD.regex, "");
        return newString;
    }
    
    public static void main(String[] args) {
//        System.out.println(removeAllNotLetter("sdf�lskdjf+-_%%sdff--->>>=="));
//        System.out.println(removeTrailingString("/aa", "/aaa"));
    }
    
}
