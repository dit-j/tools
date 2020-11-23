package de.jawb.tools.security.password;

import de.jawb.tools.security.crypt.cipher.SecUtil;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

public class Password implements CharSequence {

    private final char[] chars;
    private PasswordAnalysisResult parCached;

    public Password(char[] chars) {
        this.chars = chars;
    }

    @Override
    public int length() {
        return chars.length;
    }

    @Override
    public char charAt(int index) {
        return chars[index];
    }

    public void destroy(){
        SecUtil.clean(chars);
    }

    // Unsafe
    public String asString(){
        return new String(chars);
    }

    public char[] getChars(){
        return this.chars.clone();
    }

    public byte[] getBytes() {

        CharBuffer charBuffer = CharBuffer.wrap(chars);
        ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
        byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
                byteBuffer.position(), byteBuffer.limit());

        SecUtil.clean(byteBuffer);

        return bytes;
    }

    public PasswordAnalysisResult analysisResult(){
        if(parCached == null){
            parCached = PasswordScoreCalculator.calculateScore(chars);
        }
        return parCached;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return new Password(Arrays.copyOfRange(chars, start, end));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Password password = (Password) o;

        return Arrays.equals(chars, password.chars);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(chars);
    }

    @Override
    public String toString() {
        return "Password{xxxx}";
    }

    //
    //
    //

    public static Password create(PasswordGeneratorConfiguration cfg){
        return PasswordGenerator.generatePassword(cfg);
    }
}
