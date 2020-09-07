package de.jawb.tools.security.crypt.mask;

import de.jawb.tools.security.base64.Base64;

class Base64Masker implements IMasker {

    @Override
    public String mask(String str) {
        return Base64.encodeToString(str);
    }

    @Override
    public void reset() {
        //
    }

    @Override
    public String unmask(String maskedString) {
        return Base64.decodeToString(maskedString);
    }

}
