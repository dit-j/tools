package de.jawb.tools.jvm;

public class JavaRuntimeMemoryInfo {

    public final String max;        // -Xmx
    public final String total;
    public final String free;
    public final String used;
    public final float  usedPercent;

    JavaRuntimeMemoryInfo() {
        this.max = JavaRuntimeUtil.getMaxMemoryInfo();
        this.total = JavaRuntimeUtil.getTotalMemoryInfo();
        this.free = JavaRuntimeUtil.getFreeMemoryInfo();
        this.used = JavaRuntimeUtil.getUsedMemoryInfo();
        this.usedPercent = JavaRuntimeUtil.getUsedMemoryInfoInPercent();
    }

    @Override
    public String toString() {
        return "JavaRuntimeMemoryInfo {" +
                "\n   max:   " + max  +
                "\n   total: " + total +
                "\n   free:  " + free +
                "\n   used:  " + used + " (" + usedPercent + " %)" +
                "\n}";
    }
}
