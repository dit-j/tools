package de.jawb.tools.iso.i18n;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import de.jawb.tools.iso.Continent;
import de.jawb.tools.iso.Language;

/**
 * @author dit (24.11.2012)
 */
public class IsoTranslators {
    
    private static final Map<Language, IsoTranslatorImpl> _translators;
    
    static {
        _translators = new Hashtable<Language, IsoTranslatorImpl>();
        for (SupportedLanguages sl : SupportedLanguages.values()) {
            _translators.put(sl.language, new IsoTranslatorImpl(sl));
        }
    }
    
    /**
     * Kein Objekt der Klasse erzeugbar.
     */
    private IsoTranslators() {
    }
    
    /**
     * Liste mit Sprachen die vom {@link IsoTranslators}
     * unterstuetzt werden.
     * 
     * @return Set
     */
    public static Set<Language> getSupportedLanguages() {
        return _translators.keySet();
    }
    
    /**
     * @param language
     * @return
     */
    public static boolean isSupported(Language language) {
        return _translators.containsKey(language);
    }
    
    /**
     * Gibt einen Uebersetzer fuer eine bestimmte Sprache
     * 
     * @param language
     *            Sprache
     * @return {@link IsoTranslator}-Objekt oder
     *         <code>null</code> falls die Sprache nicht
     *         unterstuetzt wird.
     * @see IsoTranslators#getSupportedLanguages()
     */
    public static IsoTranslator getTranslator(Language language) {
        return _translators.get(language);
    }
    
    public static void main(String[] args) {
        
        IsoTranslator t = IsoTranslators.getTranslator(Language.German);
        
        int i = 0;
        for (Continent l : Continent.values()) {
            String translate = t.translate(l);
            
            if(translate.startsWith("?")){
                i++;
                System.out.println("continent." + l.code);
//                System.out.println(l.name + " : " + translate);
            }
        }
        System.out.println(i);
    }
    
}
