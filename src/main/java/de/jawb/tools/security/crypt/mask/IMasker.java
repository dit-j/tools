package de.jawb.tools.security.crypt.mask;

public interface IMasker {

    String mask(String str);

    String unmask(String encodedStr);
}
