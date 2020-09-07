package de.jawb.tools.iso.i18n;

import de.jawb.tools.iso.Language;


/**
 * @author dit (04.04.2013)
 */
enum SupportedLanguages {
    GERMAN(
            Language.German,
            "de.jawb.tools.iso.files.lang_de"),
    ENGLISH(
            Language.English,
            "de.jawb.tools.iso.files.lang_en");

    final Language language;
    final String   path;

    SupportedLanguages(Language l, String p) {
        language = l;
        path = p;
    }
}
