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
        if (StringUtil.notEmpty(s)) {
            if (s.contains("  ")) {
                return removeDoubleWhiteSpaces(s.replaceAll("  ", " "));
            } else if (s.contains("\t")) {
                return removeDoubleWhiteSpaces(s.replaceAll("\t", " "));
            }
        }
        return s;
    }


    public static String removeTrailingString(String str, String trailingString) {
        if (StringUtil.notEmpty(str)) {
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
        if (StringUtil.notEmpty(src)) {
            if (src.contains(" ")) {
                return src.replaceAll(Regex.WHITESPACE.regex, "");
            }
        }
        return src;
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
        if (StringUtil.notEmpty(s)) {
            StringBuilder sb = new StringBuilder();
            for (char ch : s.toCharArray()) {
                if (Character.isLetter(ch)) {
                    sb.append(ch);
                }
            }
            return sb.toString();
        }
        return s;
    }

}
