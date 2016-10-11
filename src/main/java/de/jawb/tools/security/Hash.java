package de.jawb.tools.security;

import java.math.BigInteger;
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
    public static String getMD5(String string) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(string.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String hashtext = bigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
    
    /**
     * Erzeugt einen 160 Bit-starken SHA-1 Hash fuer eingegebene Nachricht
     * 
     * @param string
     * @return SHA-1 Hash
     */
    public static String SHA_160(String string) {
        return SHA(string, SHA_1);
    }
    
    /**
     * Erzeugt einen 256 Bit-starken SHA Hash fuer eingegebene Nachricht
     * 
     * @param string
     * @return SHA-256 Hash
     */
    public static String SHA_256(String string) {
        return SHA(string, SHA_256);
    }
    
    /**
     * Erzeugt einen 384 Bit-starken SHA Hash fuer eingegebene Nachricht
     * 
     * @param string
     * @return SHA-384 Hash
     */
    public static String SHA_384(String string) {
        return SHA(string, SHA_384);
    }
    
    /**
     * Erzeugt einen 512 Bit-starken SHA Hash fuer eingegebene Nachricht
     * 
     * @param string
     * @return SHA-512 Hash
     */
    public static String SHA_512(String string) {
        return SHA(string, SHA_512);
    }
    
    private static String SHA(String string, String type) {
        
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
            System.err.println("ERROR: " + e.getMessage());
        }
        return null;
    }
    
    public static void main(String[] args) {
//        System.out.println(SHA("test", SHA_512));
        System.out.println("DE-" + SHA_160("ChIJs7LSxmQFl0cRwEoJjTz9HwQ"));
//        System.out.println(SHA("test", SHA_384));
//        System.out.println(SHA("test", SHA_256));
    }
}
