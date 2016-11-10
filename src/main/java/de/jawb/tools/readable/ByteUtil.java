package de.jawb.tools.readable;

import de.jawb.tools.number.NumberUtil;

/**
 * @author dit (28.12.2013)
 */
public class ByteUtil {

    public static final long KB  = 1000;
    public static final long MB  = KB * KB;
    public static final long GB  = KB * MB;

    // IEC: https://en.wikipedia.org/wiki/Gibibyte
    public static final long KiB = 1024;
    public static final long MiB = KiB * KiB;
    public static final long GiB = KiB * MiB;

    /**
     * @param bytes
     *            Dateigroesse in Bytes
     * @return Dateigroesse als lesbarer String.
     */
    public static String getReadableSizeString(long bytes) {
        return getReadableSizeString(bytes, true);
    }

    /**
     * @param bytes
     * @param iec
     *            https://en.wikipedia.org/wiki/Gibibyte
     * @return
     */
    public static String getReadableSizeString(long bytes, boolean iec) {
        String displaySize;

        if ((bytes / GB) > 0) {
            float value = Float.valueOf(bytes).floatValue() / Float.valueOf(GB).floatValue();
            displaySize = String.valueOf(NumberUtil.round(value)) + " GiB";
        } else if ((bytes / MB) > 0) {
            float value = Float.valueOf(bytes).floatValue() / Float.valueOf(MB).floatValue();
            displaySize = String.valueOf(NumberUtil.round(value)) + " MiB";
        } else if ((bytes / KB) > 0) {
            float value = Float.valueOf(bytes).floatValue() / Float.valueOf(KB).floatValue();
            displaySize = String.valueOf(NumberUtil.round(value)) + " KiB";
        } else {
            displaySize = String.valueOf(bytes) + " B";
        }
        return displaySize;
    }
}
