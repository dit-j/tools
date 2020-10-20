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

                Password strongestSoFar = null;
                int tries =  0;
                final int maxTries = 20;

                do {
                    Password temp = createPrettyPassword(r, length, charSet);
                    PasswordAnalysisResult result   = temp.analysisResult();

                    boolean symOK    = charSet.hasSymbols() ? result.property(PasswordAnalysisResult.PasswordProperty.countSym) > 0 : true;
                    boolean repSymOK = charSet.hasSymbols() ? result.property(PasswordAnalysisResult.PasswordProperty.countRepeatSym) == 0 : true;
                    boolean perSymOk = charSet.hasSymbols() ? result.property(PasswordAnalysisResult.PasswordProperty.percentageSym) < 40 : true;
                    boolean lcOk     = charSet.hasLowerCases() ? result.property(PasswordAnalysisResult.PasswordProperty.countLC) > 0 : true;
                    boolean repOk    = charSet.hasLowerCases() || charSet.hasUpperCases() ? result.property(PasswordAnalysisResult.PasswordProperty.countRepeatChars) == 0 : true;
                    boolean ucOk     = charSet.hasUpperCases() ? result.property(PasswordAnalysisResult.PasswordProperty.countUC) > 0 : true;
                    boolean nrOk     = charSet.hasDigits() ? result.property(PasswordAnalysisResult.PasswordProperty.countNr) > 0 : true;
                    boolean scoreOk  = result.score() > minStrength;

                    if(scoreOk && symOK && repSymOK && perSymOk && lcOk && ucOk && nrOk && repOk){
                        return temp;
                    }

                    if(strongestSoFar == null){
                        strongestSoFar = temp;
                    } else {
                        if(strongestSoFar.analysisResult().score() < result.score()){
                            strongestSoFar = temp;
                        }
                    }

                } while (++tries < maxTries);

                return strongestSoFar;

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

            chars[nextIndex] = getRandomFromString(r, charSet.get(nextType));
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
