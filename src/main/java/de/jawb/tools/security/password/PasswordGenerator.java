package de.jawb.tools.security.password;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * @author dit (15.10.2020)
 */
class PasswordGenerator {

    /**
     * Generiert ein zufaelliges Passwort
     *
     * @param length
     *            Laenge des Passworts
     * @return Passwort
     */
    static Password generatePassword(int length) {
        return generatePassword(new PasswordGeneratorConfiguration(length, -1, PasswordCharSet.createDefault()));
    }

    /**
     * Generiert ein zufaelliges Passwort mit spez. Zeichen (optional)
     *
     * @param cfg
     *            PasswordGeneratorConfiguration
     * @return Passwort
     */
    static Password generatePassword(PasswordGeneratorConfiguration cfg) {

        final Random r = new SecureRandom();
        int length = cfg.length();
        PasswordCharSet charSet = cfg.charSet();

        if (length >= 4) {

            int minStrength = cfg.minStrength();

            if(minStrength > 0){

                Password password;
                int tries = 0;
                do {
                    password = createPrettyPassword(r, length, charSet);
                } while (password.analysisResult().score() < minStrength && ++tries < 10);

                return password;
            } else {
                return createPrettyPassword(r, length, charSet);
            }


        } else {

            char[] chars = new char[length];
            for (int i = 0; i < length; i++) {
                chars[i] = getRandomFromString(r, charSet.get(PasswordCharType.All));
            }
            return new Password(chars);
        }

    }

    private static PasswordCharType getRandom(Random r, List<PasswordCharType> types){
        return types.get(r.nextInt(types.size()));
    }

    private static Password createPrettyPassword(Random r, int length, PasswordCharSet charSet){
        char[] chars = new char[length];

        int nextIndex = 0;

        List<PasswordCharType> types = charSet.types();

        if(types.size() == 1){

            while(nextIndex < length){
                chars[nextIndex++] = getRandomFromString(r, charSet.get(types.get(0)));
            }

        } else {

            PasswordCharType nextType;
            PasswordCharType lastType;

            if(types.contains(PasswordCharType.LowerCaseLetter)){
                nextType = PasswordCharType.LowerCaseLetter;
            } else if (types.contains(PasswordCharType.UpperCaseLetter)){
                nextType = PasswordCharType.UpperCaseLetter;
            } else {
                nextType = getRandom(r, types);
            }

            while (nextIndex < length - 1) {

                lastType = nextType;

                chars[nextIndex++] = getRandomFromString(r, charSet.get(nextType));

                while ((nextType = getRandom(r, types)) == lastType) ;
            }

            if(types.contains(PasswordCharType.LowerCaseLetter)){
                nextType = PasswordCharType.LowerCaseLetter;
            } else if (types.contains(PasswordCharType.UpperCaseLetter)){
                nextType = PasswordCharType.UpperCaseLetter;
            } else {
                nextType = getRandom(r, types);
            }

            chars[nextIndex++] = getRandomFromString(r, charSet.get(nextType));
        }

        return new Password(chars);
     }

    private static char getRandomFromString(Random r, String src) {
        return src.charAt(r.nextInt(src.length()));
    }

    public static void main(String[] args) {
        System.out.println(generatePassword(
                new PasswordGeneratorConfiguration(15, 32,
                        PasswordCharSet.create("abcdefghijklmnpqrstuvwxyz", "ABCDEFGHJKLMNPQRSTUVWXYZ", "123456789", "!&=+-")))
                .asString());
    }
}
