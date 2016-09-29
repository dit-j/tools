package de.jawb.tools.readable;

import de.jawb.tools.number.NumberUtil;

/**
 * @author dit (28.12.2013)
 */
public class ByteUtil {
    
    public static final long KB = 1024;
    public static final long MB = KB * KB;
    public static final long GB = KB * MB;
    
    /**
     * @param bytes
     *            Dateigroesse in Bytes
     * @return Dateigroesse als lesbarer String.
     */
    public static String getReadableSizeString(long bytes) {
        String displaySize;
        
        if ((bytes / GB) > 0) {
            float value = Float.valueOf(bytes).floatValue() / Float.valueOf(GB).floatValue();
            displaySize = String.valueOf(NumberUtil.round(value)) + " GB";
        } else if ((bytes / MB) > 0) {
            float value = Float.valueOf(bytes).floatValue() / Float.valueOf(MB).floatValue();
            displaySize = String.valueOf(NumberUtil.round(value)) + " MB";
        } else if ((bytes / KB) > 0) {
            float value = Float.valueOf(bytes).floatValue() / Float.valueOf(KB).floatValue();
            displaySize = String.valueOf(NumberUtil.round(value)) + " KB";
        } else {
            displaySize = String.valueOf(bytes) + " B";
        }
        return displaySize;
    }
}
