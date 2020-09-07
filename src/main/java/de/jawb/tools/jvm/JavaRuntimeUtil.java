package de.jawb.tools.jvm;

import de.jawb.tools.io.ByteUtil;
import de.jawb.tools.number.NumberUtil;

import java.util.concurrent.TimeUnit;

/**
 * @author dit (11.04.2013)
 */
public class JavaRuntimeUtil {

    /**
     * @param time
     *            in millis
     */
    public static void sleep(long time) {
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

    public static float getUsedMemoryInfoInPercent() {
        long total = Runtime.getRuntime().totalMemory();
        long free = Runtime.getRuntime().freeMemory();
        double used = ((total - free) * 100.0) / total;
        return (float)NumberUtil.round(used);
    }

    public static String getFreeMemoryInfo() {
        long bytes = Runtime.getRuntime().freeMemory();
        return ByteUtil.getReadableSizeString(bytes);
    }

    public static JavaRuntimeMemoryInfo getJavaRuntimeMemoryInfo(){
        return new JavaRuntimeMemoryInfo();
    }

}
