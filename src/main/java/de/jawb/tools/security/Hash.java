package de.jawb.tools.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author dit (24.07.2013)
 */
public class Hash {

    private static final String SHA_1   = "SHA-1";
    private static final String SHA_512 = "SHA-512";
    private static final String SHA_256 = "SHA-256";
    private static final String SHA_384 = "SHA-384";

    /**
     * Erzeugt einen 128 Bit-starken MD5 Hash fuer eingegebene Nachricht
     *
     * @param string
     *            Nachricht
     * @return MD5-code
     */
    public static String MD5(String string) {
        return createHash(string, "MD5");
    }

    /**
     * Erzeugt einen 160 Bit-starken SHA-1 Hash fuer eingegebene Nachricht
     *
     * @param string
     * @return SHA-1 Hash
     */
    public static String SHA_160(String string) {
        return createHash(string, SHA_1);
    }

    /**
     * Erzeugt einen 256 Bit-starken SHA Hash fuer eingegebene Nachricht
     *
     * @param string
     * @return SHA-256 Hash
     */
    public static String SHA_256(String string) {
        return createHash(string, SHA_256);
    }

    /**
     * Erzeugt einen 384 Bit-starken SHA Hash fuer eingegebene Nachricht
     *
     * @param string
     * @return SHA-384 Hash
     */
    public static String SHA_384(String string) {
        return createHash(string, SHA_384);
    }

    /**
     * Erzeugt einen 512 Bit-starken SHA Hash fuer eingegebene Nachricht
     *
     * @param string
     * @return SHA-512 Hash
     */
    public static String SHA_512(String string) {
        return createHash(string, SHA_512);
    }

    private static String createHash(String string, String type) {

        try {

            MessageDigest md = MessageDigest.getInstance(type);
            md.update(string.getBytes());
            byte[] mb = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mb.length; i++) {
                String s = Integer.toHexString(mb[i]);
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                sb.append(s);
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
