package de.jawb.tools.iso;

import de.jawb.tools.iso.i18n.ITranslatable;

/**
 * AF Africa <br>
 * AN Antarctica<br>
 * AS Asia<br>
 * EU Europe<br>
 * NA North america<br>
 * OC Oceania<br>
 * SA South america<br>
 * 
 * @author dit (04.04.2013)
 */
public enum Continent implements ITranslatable {
    /**
     * Afrika<br>
     * code: 'af'
     */
    Africa(
            "af"),
    
    /**
     * Afrika<br>
     * code: 'af'
     */
    Antarctica(
            "an"),
    /**
     * NordAmerika<br>
     * code: 'na'
     */
    North_America(
            "na"),
    /**
     * SuedAmerika<br>
     * code: 'sa'
     */
    South_America(
            "sa"),
    /**
     * Asien<br>
     * code: 'as'
     */
    Asia(
            "as"),
    /**
     * Europa<br>
     * code: 'eu'
     */
    Europe(
            "eu"),
    /**
     * Australien + Ozeanien<br>
     * code: 'oc'
     */
    Oceania(
            "oc");
    
    public final String code;
    private final String i18nCode;
    
    /**
     * 
     */
    private Continent(String c) {
        code = c;
        i18nCode = "continent." + code;
    }
    
    /**
     * Gibt ein {@link Continent}-Objekt dass durch ein
     * zweistelligen String repraesentiert wird.
     * 
     * @param code
     *            z.B. 'eu'
     * @return {@link Continent} oder <code>null</code>
     *         falls Continent-code ungueltig ist.
     */
    public static Continent getByCode(String code) {
        
        for (Continent c : Continent.values()) {
            if (c.code.equals(code)) {
                return c;
            }
        }
        System.err.println(code);
        return null;
    }
    
    @Override
    public String key() {
        return i18nCode;
    }
}
