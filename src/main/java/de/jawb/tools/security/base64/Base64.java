package de.jawb.tools.security.base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64 {

    public static String encodeToString(String str) {
        try {
            return encodeToString(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String encodeToString(byte[] origBytes) {
        return Base64Impl.encodeBytes(origBytes);
    }

    public static byte[] encodeToBytes(String str) {
        try {
            return Base64Impl.decode(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeToString(String base64Str) {
        try {
            byte[] bytes = Base64Impl.decode(base64Str);
            return new String(bytes, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decodeToBytes(String base64Str) {
        try {
            return Base64Impl.decode(base64Str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
