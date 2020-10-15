package de.jawb.tools.security.password;

import de.jawb.tools.string.StringUtil;

import java.util.LinkedList;
import java.util.List;

public class PasswordCharSet {

    private final String CHARS_LC;
    private final String CHARS_UC;
    private final String CHARS_NR;
    private final String CHARS_LC_UC;
    private final String CHARS_SPECIAL;
    private final String CHARS_ALL;

    private final List<PasswordCharType> types;

    private PasswordCharSet(String lc, String uc, String digits, String special) {
        CHARS_LC       = StringUtil.nullToEmpty(lc);
        CHARS_UC       = StringUtil.nullToEmpty(uc);
        CHARS_NR       = StringUtil.nullToEmpty(digits);
        CHARS_SPECIAL  = StringUtil.nullToEmpty(special);
        CHARS_LC_UC    = CHARS_LC + CHARS_UC;
        CHARS_ALL      = CHARS_LC_UC + CHARS_NR + CHARS_SPECIAL;

        types = new LinkedList<>();

        if(CHARS_LC.length() > 0){
            types.add(PasswordCharType.LowerCaseLetter);
        }
        if(CHARS_UC.length() > 0){
            types.add(PasswordCharType.UpperCaseLetter);
        }
        if(CHARS_LC_UC.length() > 0){
            types.add(PasswordCharType.LowerOrUpperCaseLetter);
        }
        if(CHARS_NR.length() > 0){
            types.add(PasswordCharType.Digit);
        }
        if(CHARS_SPECIAL.length() > 0){
            types.add(PasswordCharType.Special);
        }

        if(types.size() == 0){
            throw new IllegalStateException("charset may not be empty");
        }
    }

    private PasswordCharSet() {
        this("abcdefghijklmnopqrstuvwxyz",
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ",
                "0123456789",
                "()[]{}?!$%&/*+~-,.;:<=>_|@#");
    }

    public static PasswordCharSet create(String lc, String uc, String digits, String special){
        return new PasswordCharSet(lc, uc, digits, special);
    }

    public static PasswordCharSet createDefault(){
        return new PasswordCharSet();
    }

    public List<PasswordCharType> types(){
        return types;
    }

    public String get(PasswordCharType type){

        if (type == PasswordCharType.LowerOrUpperCaseLetter) return CHARS_LC_UC;
        if (type == PasswordCharType.LowerCaseLetter) return CHARS_LC;
        if (type == PasswordCharType.UpperCaseLetter) return CHARS_UC;
        if (type == PasswordCharType.Digit          ) return CHARS_NR;
        if (type == PasswordCharType.Special        ) return CHARS_SPECIAL;

        return CHARS_ALL;
    }

}
