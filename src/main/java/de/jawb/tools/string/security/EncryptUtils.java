package de.jawb.tools.string.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
/**
 * 
 * @author dit (17.05.2013)
 *
 */
public class EncryptUtils {
    
    public static final String   DEFAULT_ENCODING = "UTF-8";
//    private static BASE64Encoder enc              = new BASE64Encoder();
//    private static BASE64Decoder dec              = new BASE64Decoder();
    
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
            return null;
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
    
    public static String xorMessage(String message, String key) {
        try {
//            if ((message == null) || (key == null)) {
//                return null;
//            }
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
            System.err.println(e.getMessage());
            return null;
        }
    }
    
//    private static void test() {
//        long i = 0;
//        String k = "test";
//        while (i < Long.MAX_VALUE) {
//            String pw = SecStringUtil.generatePassword(140, true);
//            String en = encode(pw, k);
//            String de = decode(en, k);
//            if(!de.equals(pw)){
//                System.err.println(pw);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
////        test();
//    }
    
}