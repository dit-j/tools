package de.jawb.tools.security;

import java.io.UnsupportedEncodingException;

public class Base64 {

    public static String encodeToString(String str) {
        try {
            return java.util.Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeToString(byte[] origBytes) {
        return new String(java.util.Base64.getEncoder().encode(origBytes));
    }

    public static byte[] encodeToBytes(String str) {
        try {
            return java.util.Base64.getEncoder().encode(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeToString(String base64Str) {
        byte[] bytes = java.util.Base64.getDecoder().decode(base64Str);
        return new String(bytes);
    }

    public static byte[] decodeToBytes(String base64Str) {
        return java.util.Base64.getDecoder().decode(base64Str);
    }
}
