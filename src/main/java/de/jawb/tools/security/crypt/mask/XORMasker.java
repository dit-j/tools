package de.jawb.tools.security.crypt.mask;

public class XORMasker implements IMasker {

    private String xorKey;

    public XORMasker(String xorKey) {
        this.xorKey = xorKey;
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
