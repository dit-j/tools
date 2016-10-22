package de.jawb.tools.security.crypt.mask;

class RotMasker implements IMasker {

    private final static String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/-=";
    private static final int    ROT_VAL  = ALPHABET.length() / 2;

    private int index(char ch) {
        int i = ALPHABET.indexOf(ch);
        if (i >= 0) {
            return i;
        }
        throw new IllegalArgumentException("non base64 character: " + Character.toString(ch) + " [" + ALPHABET + "]");
    }

    @Override
    public String mask(String str) {
        StringBuilder sb = new StringBuilder();
        for (char ch : str.toCharArray()) {
            int index = index(ch);
            int nextIndex = (index + ROT_VAL) % ALPHABET.length();
            sb.append(ALPHABET.charAt(nextIndex));
        }
        return sb.toString();
    }

    @Override
    public String unmask(String encodedStr) {
//        StringBuilder sb = new StringBuilder();
//        for (char ch : encodedStr.toCharArray()) {
//            int index = index(ch);
//            int nextIndex = (index - ROT_VAL) % ALPHABET.length();
//            sb.append(ALPHABET.charAt(nextIndex));
//        }
        return mask(encodedStr); // involution
    }

    public static void main(String[] args) {
        System.out.println(new RotMasker().mask("abcdf8fjhndjcnbeDDGsid"));
    }
}
