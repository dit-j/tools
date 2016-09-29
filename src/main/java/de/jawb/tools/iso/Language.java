/**
 * 
 */
package de.jawb.tools.iso;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import de.jawb.tools.iso.i18n.ITranslatable;

/**
 * LanguageList ISO 639-1
 * 
 * @author dit (24.11.2012)
 */
public enum Language implements ITranslatable {
    
    Abkhazian(
            "ab"),
    Afar(
            "aa"),
    Afrikaans(
            "af"),
    Albanian(
            "sq"),
    Amharic(
            "am"),
    Arabic(
            "ar"),
    Armenian(
            "hy"),
    Assamese(
            "as"),
    Aymara(
            "ay"),
    Azerbaijani(
            "az"),
    Bashkir(
            "ba"),
    Basque(
            "eu"),
    Bengali(
            "bn"),
    Bhutani(
            "dz"),
    Bihari(
            "bh"),
    Bislama(
            "bi"),
    Breton(
            "br"),
    Bulgarian(
            "bg"),
    Burmese(
            "my"),
    Byelorussian(
            "be"),
    Cambodian(
            "km"),
    Catalan(
            "ca"),
    Chinese(
            "zh"),
    Corsican(
            "co"),
    Croatian(
            "hr"),
    Czech(
            "cs"),
    Danish(
            "da"),
    Dutch(
            "nl"),
    English(
            "en"),
    Esperanto(
            "eo"),
    Estonian(
            "et"),
    Faeroese(
            "fo"),
    Farsi(
            "fa"),
    Fiji(
            "fj"),
    Finnish(
            "fi"),
    French(
            "fr"),
    Frisian(
            "fy"),
    Gaelic(
            "gd"),
    Galician(
            "gl"),
    Georgian(
            "ka"),
    German(
            "de"),
    Greek(
            "el"),
    Greenlandic(
            "kl"),
    Guarani(
            "gn"),
    Gujarati(
            "gu"),
    Hausa(
            "ha"),
    Hebrew(
            "he"),
    Hindi(
            "hi"),
    Hungarian(
            "hu"),
    Icelandic(
            "is"),
    Ido(
            "io"),
    Indonesian(
            "in"),
    Interlingua(
            "ia"),
    Interlingue(
            "ie"),
    Inuktitut(
            "iu"),
    Inupiak(
            "ik"),
    Irish(
            "ga"),
    Italian(
            "it"),
    Japanese(
            "ja"),
    Javanese(
            "jv"),
    Kannada(
            "kn"),
    Kashmiri(
            "ks"),
    Kazakh(
            "kz"),
    Kinyarwanda(
            "rw"),
    Kirghiz(
            "ky"),
    Kirundi(
            "rn"),
    Korean(
            "ko"),
    Kurdish(
            "ku"),
    Laothian(
            "lo"),
    Latin(
            "la"),
    Latvian(
            "lv"),
    Limburgish(
            "li"),
    Lingala(
            "ln"),
    Lithuanian(
            "lt"),
    Macedonian(
            "mk"),
    Malagasy(
            "mg"),
    Malay(
            "ms"),
    Malayalam(
            "ml"),
    Maltese(
            "mt"),
    Maori(
            "mi"),
    Marathi(
            "mr"),
    Moldavian(
            "mo"),
    Mongolian(
            "mn"),
    Nauru(
            "na"),
    Nepali(
            "ne"),
    Norwegian(
            "no"),
    Occitan(
            "oc"),
    Oriya(
            "or"),
    Oromo(
            "om"),
    Pashto(
            "ps"),
    Polish(
            "pl"),
    Portuguese(
            "pt"),
    Punjabi(
            "pa"),
    Quechua(
            "qu"),
    Rhaeto_Romance(
            "rm"),
    Romanian(
            "ro"),
    Russian(
            "ru"),
    Samoan(
            "sm"),
    Sangro(
            "sg"),
    Sanskrit(
            "sa"),
    Serbian(
            "sr"),
    Sesotho(
            "st"),
    Setswana(
            "tn"),
    Shona(
            "sn"),
    Sindhi(
            "sd"),
    Sinhalese(
            "si"),
    Siswati(
            "ss"),
    Slovak(
            "sk"),
    Slovenian(
            "sl"),
    Somali(
            "so"),
    Spanish(
            "es"),
    Sundanese(
            "su"),
    Swahili(
            "sw"),
    Swedish(
            "sv"),
    Tagalog(
            "tl"),
    Tajik(
            "tg"),
    Tamil(
            "ta"),
    Tatar(
            "tt"),
    Telugu(
            "te"),
    Thai(
            "th"),
    Tibetan(
            "bo"),
    Tigrinya(
            "ti"),
    Tonga(
            "to"),
    Tsonga(
            "ts"),
    Turkish(
            "tr"),
    Turkmen(
            "tk"),
    Twi(
            "tw"),
    Uighur(
            "ug"),
    Ukrainian(
            "uk"),
    Urdu(
            "ur"),
    Uzbek(
            "uz"),
    Vietnamese(
            "vi"),
    Wallon(
            "wa"),
    Welsh(
            "cy"),
    Wolof(
            "wo"),
    Xhosa(
            "xh"),
    Yiddish(
            "yi"),
    Yoruba(
            "yo"),
    Zulu(
            "zu");
    
    private static Map<String, Language> _cache = new HashMap<String, Language>();
    
    static {
        Language[] MOST_USED = { //
        Language.German,//
            Language.English,// 
            Language.French,// 
            Language.Italian, //
            Language.Spanish, //
            Language.Russian, //                           
        };
        for (Language l : MOST_USED) {
            _cache.put(l.isoCode, l);
        }
    }
    
    public static Language getByCode(String code) {
        return getByCode(code, null);
    }
    
    public static Language getByCode(String code, Language dflt) {
        
        String lowerCase = code.toLowerCase();
        Language l = _cache.get(lowerCase);
        
        if (l == null) {
            l = Language.getByISOCode(lowerCase, dflt);
            if (l == null) throw new RuntimeException("bad language iso-code: " + code);
            _cache.put(lowerCase, l);
        }
        
        return l;
    }
    
    /**
     * @param code
     *            z.B. 'de'
     * @param dflt
     *            RueckgabeParameter falls code ungueltig
     *            ist
     * @return 'de' -> German<br>
     */
    static Language getByISOCode(String code, Language dflt) {
        for (Language l : Language.values()) {
            if (l.isoCode.equalsIgnoreCase(code)) {
                return l;
            }
        }
        return dflt;
    }
    
    public final String  isoCode;
    private final String i18nCode;
    
    private Language(String iCode) {
        isoCode = iCode;
        i18nCode = "lang." + isoCode;
    }
    
    public String getName() {
        if (this == Rhaeto_Romance) {
            return "Rhaeto-Romance";
        }
        return super.toString();
    }
    
    @Override
    public String key() {
        return i18nCode;
    }
    
    public String getCode() {
        return isoCode;
    }
    
    public Locale getLocale() {
        return getLocale(this.isoCode);
    }
    
    
    @Override
    public String toString() {
        return isoCode;
    }
    
    public static Locale getLocale(String isoLangCode) {
        if("de".equals(isoLangCode)) return Locale.GERMAN;
        if("en".equals(isoLangCode)) return Locale.ENGLISH;
        return new Locale(isoLangCode);
    }
    
}
