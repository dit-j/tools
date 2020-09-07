package de.jawb.tools.number;


/**
 * @author dit (25.08.2012)
 */
public class NumberUtil {

    /**
     * Rundet eine Zahl auf zwei Stellen auf
     *
     * @param d
     *            eine dezimale Zahle 4.45535445454
     * @return 4.45
     */
    public static double round(double d) {
        long tmp = Math.round(d * 100);
        return (double) tmp / 100;
    }

    /**
     * Rundet eine Zahl auf X Stellen auf
     *
     * @param d
     *            eine dezimale Zahle 4.45535445454
     * @param x
     * @return 4.45
     */
    public static double round(double d, int x) {
        int p = (int) Math.pow(10, x);
        long tmp = Math.round(d * p);
        return (double) tmp / p;
    }

    /**
     * Macht aus einer großen Zahl wie 13423434543 eine besser lesbare Stringdarstellung dieser Zahl
     * <tt>13.423.434.543</tt>
     *
     * @param number
     *            eine ganze Zahl
     * @param delimiter Trennzeichen
     * @return <tt>13.423.434.543</tt>
     */
    public static String readableString(long number, String delimiter) {

        String asString = Long.toString(number);

        if (asString.length() > 3) {

            StringBuilder sb = new StringBuilder();
            int i = 0;
            char[] array = asString.toCharArray();
            for (int j = array.length - 1; j >= 1; --j) {
                sb.append(array[j]);
                if (++i == 3) {
                    i = 0;
                    sb.append(delimiter);
                }
            }
            sb.append(array[0]);

            return sb.reverse().toString();
        }

        return asString;
    }

    @Deprecated
    public static String readableString(long number) {
        return readableString(number, ".");
    }

    /**
     * Macht aus einer 6.0 Zahl eine ohne Null: 6<br>
     * *
     *
     * @param d
     *            eine Fliesskommazahl
     * @return eine Zahl ohne ,0 als String
     */
    public static String cutZero(Double d) {
        double tmp = d.doubleValue() - d.intValue();
        if (tmp < 0.000000001) {
            return Integer.toString(d.intValue());
        } else {
            return Double.toString(d.doubleValue());
        }
    }

    public static Long parseLong(String attest) {
        try {
            return Long.parseLong(attest);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
