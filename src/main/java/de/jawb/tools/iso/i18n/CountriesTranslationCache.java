package de.jawb.tools.iso.i18n;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.jawb.tools.iso.Country;
import de.jawb.tools.iso.Language;

/**
 * @author dit (24.11.2012)
 */
public class CountriesTranslationCache {
    
    private static CountriesTranslationCache _instance;
    
    /**
     * Gibt Singleton-Objekt der Klasse.
     * 
     * @return {@link CountriesTranslationCache}
     */
    public static final CountriesTranslationCache instance() {
        if (_instance == null) {
            _instance = new CountriesTranslationCache();
        }
        return _instance;
    }
    
    private Map<Language, List<ISOPair>> _cache;
    
    /**
     * Gibt eine sortierte Liste mit Laendern
     * 
     * @param lang
     *            Sprache
     * @return Sortierte Liste
     */
    public final List<ISOPair> getSortedCountryList(Language lang) {
        
        if (_cache == null) {
            _cache = new HashMap<Language, List<ISOPair>>();
        }
        
        List<ISOPair> list = _cache.get(lang);
        if (list == null) {
            list = new ArrayList<ISOPair>(Country.values().length);
            
            IsoTranslator translator = IsoTranslators.getTranslator(lang);
            
            if (translator == null) {
                //
                // SPRACHE WIRD NICHT UNTERSTUETZT
                //
                return null;
            }
            
            for (Country c : Country.values()) {
                String name = translator.translate(c);
                
                ISOPair pair = new ISOPair();
                pair.setName(name);
                pair.setIsoCode(c.code);
                
                list.add(pair);
            }
            
            if (lang == Language.German) {
                Collections.sort(list, new ComparatorGerman());
            } else {
                Collections.sort(list, new ComparatorEnglish());
            }
            
            _cache.put(lang, list);
        }
        
        return list;
    }
    
    /**
     * Reset & Clean
     */
    public void clean() {
        if (_cache != null) {
            _cache.clear();
        }
        _cache = null;
    }
    
    public static void main(String[] args) {
        
        CountriesTranslationCache.instance().getSortedCountryList(Language.German);
        
        System.out.println(instance().getSortedCountryList(Language.German));
        
    }
}
