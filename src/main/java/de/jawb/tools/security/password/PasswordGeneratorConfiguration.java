package de.jawb.tools.security.password;

public class PasswordGeneratorConfiguration {

    private final int length;
    private final int minStrength;
    private final PasswordCharSet charSet;

    public PasswordGeneratorConfiguration(int length, int minStrength, PasswordCharSet charSet) {
        this.length = length;
        this.minStrength = minStrength;
        this.charSet = charSet;
    }

    public int length() {
        return length;
    }

    public int minStrength() {
        return minStrength;
    }

    public PasswordCharSet charSet() {
        return charSet;
    }
}
