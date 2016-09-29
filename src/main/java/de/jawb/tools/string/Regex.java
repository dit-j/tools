/**
 * 
 */
package de.jawb.tools.string;

/**
 * Beispiele:<br>
 * 
 * <pre>
 * [+-] nur ein Zeichen aus der Menge ist erlaubt. -> "+" oder "-"
 * [+-]+    ein oder mehrere Zeichen aus der Menge sind erlaubt, Reihenfolge egal. -> "-+---"
 * (+-) nur diese Zeichen, nur in dieser Reihenfolge, nur einmal. -> nur "+-"
 * (+-)+    nur diese Zeichen, nur in dieser Reihenfolge, ein oder mehrere male hintereinander -> "+-+-+-+-"
 * ?    ein oder kein Zeichen aus der vorstehenden Menge
 * </pre>
 * 
 * @author dit (22.03.2011)
 */
public enum Regex {
    
    /**
     * Ein beliebiges Zeichen.
     */
    ALL(
            "."),
    
    /**
     * Ein Non-Word-Zeichen. Alles ausser [a-zA-Z_0-9]
     */
    NON_WORD(
            "\\W"),
    /**
     * Ein Word-Zeichen. [a-zA-Z_0-9]
     */
    WORD(
            "\\w"),
    
    /**
     * Eine Ziffer. [0-9]
     */
    DIGIT(
            "\\d"),
    
    /**
     * Keine Ziffer. alles ausser: [0-9]
     */
    NON_DIGIT(
            "\\D"),
    
    /**
     * Ein LeerZeichen: [ \t\n\f\r\ usw.]
     */
    WHITESPACE(
            "\\s"),
    
    /**
     * Alles ausser LeerZeichen.
     */
    NON_WHITESPACE(
            "\\S"),
    
    /**
     * Alles ausser ^1 oder ^2 oder ^p etc.
     */
    Q3_COLOR_CODE(
            "\\^([\\S])");
    
    /**
     * Regex-String
     */
    public final String regex;
    
    //
    private Regex(String regex) {
        this.regex = regex;
    }
}
