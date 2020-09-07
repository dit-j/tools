package de.jawb.tools.security.crypt.mask;

/**
 * @author dit (17.05.2013)
 */
public class XORUtil {

    /**
     * Kodiert eine Nachricht mit Hilfe eines XOR-Kodierer
     *
     * @param original
     *            Nachricht (am Besten davor mit Base64 maskieren)
     * @param xorKey
     *            XOR-Key
     * @return eine XOR-kodierte Nachricht
     * @throws IllegalArgumentException
     *             wenn text nicht dekodierbar ist.
     */
    public static String encode(String original, char[] xorKey) {
        String textXOR = xorMessage(original, xorKey);
        if (!isDecodable(textXOR, original, xorKey)) {
            throw new IllegalArgumentException("String '" + original + "' kann nicht kodiert werden. (Involution unmoeglich)");
        }
        return textXOR;
    }

    private static boolean isDecodable(String encoded, String original, char[] xorKey) {
        return decode(encoded, xorKey).equals(original);
    }

    /**
     * Dekodiert eine Nachricht mit Hilfe eines XOR-Kodierer
     *
     * @param text
     *            Kodierte Nachricht
     * @param xorKey
     *            XOR-Key
     * @return Originalnachricht
     */
    public static String decode(String text, char[] xorKey) {
        return xorMessage(text, xorKey);
    }

    private static String xorMessage(String message, char[] key) {
        try {
            char[] mesg = message.toCharArray();

            int ml = mesg.length;
            int kl = key.length;
            char[] newmsg = new char[ml];

            for (int i = 0; i < ml; i++) {
                newmsg[i] = (char) (mesg[i] ^ key[i % kl]);
            }
            mesg = null;
            key = null;
            return new String(newmsg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
