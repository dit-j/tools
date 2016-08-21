package de.jawb.tools.iso.i18n;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author dit (04.04.2013)
 */
class IsoTranslatorImpl implements IsoTranslator {
    
    private ResourceBundle     _bundle;
    private SupportedLanguages _language;
    
    IsoTranslatorImpl(SupportedLanguages sl) {
        _language = sl;
    }
    
    @Override
    public String translate(ITranslatable translatable) {
        
        if (_bundle == null) {
            _bundle = ResourceBundle.getBundle(_language.path);
        }
        
        try {
            return _bundle.getString(translatable.key());
        } catch (MissingResourceException e) {
            System.err.println("ERROR in " + toString() + ": " + translatable.key() + "= ???");
            return '?' + translatable.key() + '?';
        }
    }
    
    @Override
    public String toString() {
        return IsoTranslatorImpl.class.getName();
    }
    
}
