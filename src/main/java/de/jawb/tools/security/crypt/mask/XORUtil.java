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
    public static String encode(String original, String xorKey) {
        String textXOR = xorMessage(original, xorKey);
        if (!isDecodable(textXOR, original, xorKey)) {
            throw new IllegalArgumentException("String '" + original + "' kann nicht kodiert werden. (Involution unmoeglich)");
        }
        return textXOR;
    }

    private static boolean isDecodable(String encoded, String original, String xorKey) {
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
    public static String decode(String text, String xorKey) {
        return xorMessage(text, xorKey);
    }

    private static String xorMessage(String message, String key) {
        try {
            char[] keys = key.toCharArray();
            char[] mesg = message.toCharArray();

            int ml = mesg.length;
            int kl = keys.length;
            char[] newmsg = new char[ml];

            for (int i = 0; i < ml; i++) {
                newmsg[i] = (char) (mesg[i] ^ keys[i % kl]);
            }
            mesg = null;
            keys = null;
            return new String(newmsg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}