package de.jawb.tools.readable;

import de.jawb.tools.number.NumberUtil;

/**
 * @author dit (28.12.2013)
 */
public class ByteUtil {

    /**
     * @param bytes
     *            Dateigroesse in Bytes
     * @return Dateigroesse als lesbarer String nach iec (KiB = 1024 bytes)
     */
    public static String getReadableSizeString(long bytes) {
        return getReadableSizeString(bytes, true);
    }

    /**
     * @param bytes
     * @param iec
     * @return
     */
    public static String getReadableSizeString(long bytes, boolean iec) {

        int base = iec ? 1024 : 1000;
        String end = iec ? "iB" : "B";

        if (bytes >= base) {
            float kb = bytes / base;
            if (kb >= base) {
                float mb = kb / base;
                if (mb >= base) {
                    float gb = mb / base;
                    return String.valueOf(NumberUtil.round(gb)) + " G" + end;
                }
                return String.valueOf(NumberUtil.round(mb)) + " M" + end;
            }
            return String.valueOf(NumberUtil.round(kb)) + " K" + end;
        }
        return String.valueOf(bytes) + " " + end;
    }
}
