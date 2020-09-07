package de.jawb.tools.security.crypt.mask;

import java.util.Arrays;

class XORMasker implements IMasker {

    private char[] xorKey;

    XORMasker(char[] xorKey) {
        this.xorKey = xorKey;
    }

    @Override
    public void reset() {
        Arrays.fill(xorKey, '\0');
        xorKey = null;
    }

    @Override
    public String mask(String text) {
        return XORUtil.encode(text, xorKey);
    }

    @Override
    public String unmask(String encodedStr) {
        return XORUtil.decode(encodedStr, xorKey);
    }

}
