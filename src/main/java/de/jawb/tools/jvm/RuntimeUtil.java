package de.jawb.tools.jvm;

import java.util.concurrent.TimeUnit;

import de.jawb.tools.io.ByteUtil;
import de.jawb.tools.number.NumberUtil;

/**
 * @author dit (11.04.2013)
 */
public class RuntimeUtil {

    /**
     * @param time
     *            in millis
     */
    public static final void sleep(long time) {
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException ex) {
            //
        }
    }

    public static String getTotalMemoryInfo() {
        long bytes = Runtime.getRuntime().totalMemory();
        return ByteUtil.getReadableSizeString(bytes);
    }

    public static String getMaxMemoryInfo() {
        long bytes = Runtime.getRuntime().maxMemory();
        return ByteUtil.getReadableSizeString(bytes);
    }

    public static String getUsedMemoryInfo() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        return ByteUtil.getReadableSizeString(total - free);
    }

    public static String getUsedMemoryInfoInPercent() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        double used = ((total - free) * 100.0) / total;
        return NumberUtil.round(used) + " %";
    }

    public static String getFreeMemoryInfo() {
        long bytes = Runtime.getRuntime().freeMemory();
        return ByteUtil.getReadableSizeString(bytes);
    }

    public static void main(String[] args) {
        System.out.println(getMaxMemoryInfo());
        System.out.println(getTotalMemoryInfo());
        System.out.println(getFreeMemoryInfo());
        System.out.println(getUsedMemoryInfo());
        System.out.println(getUsedMemoryInfoInPercent());
    }
}