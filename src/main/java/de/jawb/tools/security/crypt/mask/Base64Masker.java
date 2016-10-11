package de.jawb.tools.security.crypt.mask;

import java.io.IOException;
import java.nio.charset.Charset;

import de.jawb.tools.security.Base64;

public class Base64Masker implements IMasker {

    private static final Charset FOR_NAME = Charset.forName("UTF-8");

    @Override
    public String mask(String str) {
        return Base64.encodeBytes(str.getBytes(FOR_NAME));
    }

    @Override
    public String unmask(String maskedString) {
        try {
            byte[] decoded = Base64.decode(maskedString.getBytes(FOR_NAME));
            return new String(decoded);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
