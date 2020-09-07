package de.jawb.tools.security.crypt.mask;

public class MaskerFactory {

    public static IMasker xor(char[] xorKey) {
        return new XORMasker(xorKey);
    }

    public static IMasker base64() {
        return new Base64Masker();
    }

    public static IMasker rot() {
        return new RotMasker();
    }
}
