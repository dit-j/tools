package de.jawb.tools.security.crypt.mask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import de.jawb.tools.security.Base64;

/**
 * @author dit (17.05.2013)
 */
public class XORUtil {

    public static final String DEFAULT_ENCODING = "UTF-8";

    /**
     * Kodiert eine Nachricht mit Hilfe eines XOR-Kodierer
     * 
     * @param text
     *            Nachricht
     * @param xorKey
     *            XOR-Key
     * @return eine BASE64-kodierte Nachricht
     */
    public static String encode(String text, String xorKey) {
        try {
            String textXOR = xorMessage(text, xorKey);
            String rez = Base64.encodeBytes(textXOR.getBytes(DEFAULT_ENCODING));

            if (!isDecodable(rez, text, xorKey)) {
                throw new RuntimeException("String '" + text + "' kann nicht dekodiert werden.");
            }
            return rez;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
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
        try {
            String string = new String(Base64.decode(text), DEFAULT_ENCODING);
            return xorMessage(string, xorKey);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
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