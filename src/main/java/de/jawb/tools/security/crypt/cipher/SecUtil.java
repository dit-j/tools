package de.jawb.tools.security.crypt.cipher;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.util.Arrays;

public class SecUtil {

    public static void clean(CharBuffer buffer){
        clean(buffer.array());
    }

    public static void clean(char[] array){
        Arrays.fill(array, '\0');
    }

    public static void clean(ByteBuffer buffer){
        clean(buffer.array());
    }

    public static void clean(byte[] array){
        Arrays.fill(array, (byte) 0);
    }

}
