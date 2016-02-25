/**
 * 
 */
package de.jawb.tools.util.os;

/**
 * Beschreibt drei moeglichen Betriebsysteme.
 * 
 * @author dit (05.06.2011)
 */
public enum OSUtil {
    /**
     * Windows
     */
    WINDOWS,
    /**
     * Linux
     */
    LINUX,
    /**
     * Macintosh
     */
    MAC,
    /**
     * Unbekannt
     */
    UNKNOWN;
    
    /**
     * Gibt das aktuelle Betriebsystem dieses Rechners.
     * 
     * @return aktuelles Betriebsystem.
     */
    public static final OSUtil getOS() {
        String os = System.getProperty("os.name").toLowerCase();
        
        if (os != null) {
            if (os.indexOf("win") != -1) {
                return WINDOWS;
            } else if (os.indexOf("mac") != -1) {
                return MAC;
            } else if ((os.indexOf("nux") != -1) || (os.indexOf("nix") != -1)) {
                return LINUX;
            } else {
                
            }
        }
        return UNKNOWN;
    }
    
}
