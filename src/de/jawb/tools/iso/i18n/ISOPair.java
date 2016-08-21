package de.jawb.tools.iso.i18n;

/**
 * @author dit (24.11.2012)
 */
public class ISOPair {
    
    private String _name;
    private String _isoCode;
    
    /**
     * Gibt uebersetzte Bezeichnung zurueck
     * 
     * @return Bezeichnung
     */
    public final String getName() {
        return _name;
    }
    
    /**
     * Setzt Bezeichnung
     * 
     * @param name
     *            neuer Wert
     */
    public final void setName(String name) {
        _name = name;
    }
    
    /**
     * Gibt isoCode zurueck
     * 
     * @return isoCode
     */
    public final String getIsoCode() {
        return _isoCode;
    }
    
    /**
     * Setzt isoCode
     * 
     * @param isoCode
     *            neuer Wert
     */
    public final void setIsoCode(String isoCode) {
        _isoCode = isoCode;
    }
    
    @Override
    public String toString() {
        return _isoCode + " = " + _name;
    }
    
}
