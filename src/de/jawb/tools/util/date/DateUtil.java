/**
 * 
 */
package de.jawb.tools.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author dit (24.08.2012)
 */
public class DateUtil {
    
    public static final SimpleDateFormat DATE_TIME          = new SimpleDateFormat("dd.MM.yyyy, hh:mm");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
    public static final SimpleDateFormat TIME_HMS           = new SimpleDateFormat("HH:mm:ss");
    public static final SimpleDateFormat TIME_HM            = new SimpleDateFormat("HH:mm");
                                                            
    /**
     * Erstellt Zeitstring. Datum:Heute.
     * 
     * @param time
     *            wenn <code>true</code> wird Zeit der Form erstellt:
     *            <b>10.04.2012, 10:00</b><br>
     *            sonst: <b>10.04.2012</b>
     * @return datum, zeit
     */
    public static final String getTodayString(boolean time) {
        Calendar cal = Calendar.getInstance();
        return getDateTimeString(cal.getTime(), time);
    }
    
    /**
     * Gibt die Dauer als ein String der Form: <dd:>hh:mm:ss:ms
     * 
     * @param duration
     *            dauer in Millisekunden
     * @return zB fuer 122265469 -> 1d 09:57:45:469
     */
    public static String getDurationFromMillis(long duration) {
        long days       = TimeUnit.MILLISECONDS.toDays(duration);
        long hours      = TimeUnit.MILLISECONDS.toHours(duration)   - TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
        long minutes    = TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
        long seconds    = TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
        long restMillis = duration - (TimeUnit.MILLISECONDS.toSeconds(duration) * 1000);
        
        if (days == 0) {
            return String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, restMillis);
        } else {
            return String.format("%dd %02d:%02d:%02d:%03d", days, hours, minutes, seconds, restMillis);
        }
    }
    
    /**
     * Erstellt Zeitstring
     * 
     * @param date
     *            Datum
     * @param time
     *            wenn <code>true</code> wird Zeit der Form erstellt:
     *            <b>10.04.2012, 10:00</b><br>
     *            sonst: <b>10.04.2012</b>
     * @return datum (, zeit)
     */
    public static final String getDateTimeString(Date date, boolean time) {
        // if(date == null){
        // return "err:date==null";
        // }
        String today = SIMPLE_DATE_FORMAT.format(date.getTime());
        if (!time) {
            return today;
        }
        return today + ", " + TIME_HMS.format(date);
    }
    
    /**
     * Erstellt Zeitstring
     * 
     * @param millis
     *            Datum als millis
     * @param time
     *            wenn <code>true</code> wird Zeit der Form erstellt:
     *            <b>10.04.2012, 10:00</b><br>
     *            sonst: <b>10.04.2012</b>
     * @return datum (, zeit)
     */
    public static final String getDateTimeString(Long millis, boolean time) {
        
        String today = SIMPLE_DATE_FORMAT.format(millis);
        if (!time) {
            return today;
        }
        return today + ", " + TIME_HMS.format(millis);
    }
    
    public static final Date getDateFromDateString(String dateTime) throws ParseException {
        long time = getMillisFromDateString(dateTime);
        return new Date(time);
    }
    
    /**
     * Errechnet aus einem Datum-String die Millisekunden.
     * 
     * @param dateTime
     *            z.b. <tt>23.04.2012, 21:45</tt>
     * @return z.B. 1335210300000 (23.04.2012, 21:45)
     * @throws ParseException
     *             wenn der String nicht der Form <tt>dd.MM.yyyy, hh:mm</tt>
     *             oder <tt>dd.MM.yyyy</tt> ist
     */
    public static final long getMillisFromDateString(String dateTime) throws ParseException {
        if ((dateTime != null) && (dateTime.indexOf(',') < 0)) {
            return SIMPLE_DATE_FORMAT.parse(dateTime).getTime();
        } else {
            return DATE_TIME.parse(dateTime).getTime();
        }
    }
    
    public static void main(String[] args) {
        System.out.println(getDurationFromMillis(122265469));
    }
}
