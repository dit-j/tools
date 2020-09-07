package date;

import de.jawb.tools.date.DateUtil;
import de.jawb.tools.date.DurationStyle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class DateUtilTest {

    private Calendar CAL = Calendar.getInstance();

    @Before
    public void setUp() throws Exception {
        CAL.set(2016, Calendar.OCTOBER, 29, 10, 0, 0);
        CAL.set(Calendar.MILLISECOND, 0);
    }

    @Test
    public void testGetTimeString() {
        Assert.assertEquals("00:20", DateUtil.getTimeString(20, true));
        Assert.assertEquals("10:34", DateUtil.getTimeString(60 * 10 + 34, true));
        Assert.assertEquals("20:11", DateUtil.getTimeString(60 * 20 + 11, true));

        Assert.assertEquals("00:20 AM", DateUtil.getTimeString(20, false));
        Assert.assertEquals("10:34 AM", DateUtil.getTimeString(60 * 10 + 34, false));
        Assert.assertEquals("08:11 PM", DateUtil.getTimeString(60 * 20 + 11, false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetTimeStringException() {
        DateUtil.getTimeString(-60 * 20 + 11, false);
    }

    @Test
    public void testIsWeekend() {
        Assert.assertTrue(DateUtil.isWeekend(CAL));
    }

    @Test
    public void testGetDurationFromMillis2() {
        Assert.assertEquals("3m 0s 234ms",  DateUtil.toReadableString(DurationStyle.HumanReadable, TimeUnit.MINUTES.toMillis(3) + 234));
        Assert.assertEquals("3m 1s 234ms",  DateUtil.toReadableString(DurationStyle.HumanReadable, TimeUnit.MINUTES.toMillis(3) + 1234));
        Assert.assertEquals("0ms",          DateUtil.toReadableString(DurationStyle.HumanReadable, 0));
        Assert.assertEquals("00:03:00:234", DateUtil.toReadableString(DurationStyle.Full, TimeUnit.MINUTES.toMillis(3) + 234));
        Assert.assertEquals("03:00:234",    DateUtil.toReadableString(DurationStyle.Short, TimeUnit.MINUTES.toMillis(3) + 234));
        Assert.assertEquals("03:01:234",    DateUtil.toReadableString(DurationStyle.Short, TimeUnit.MINUTES.toMillis(3) + 1234));
        Assert.assertEquals("234",          DateUtil.toReadableString(DurationStyle.Short, 234));
        Assert.assertEquals("234ms",        DateUtil.toReadableString(DurationStyle.HumanReadable, 234));
        Assert.assertEquals("0ms",        DateUtil.toReadableString(DurationStyle.HumanReadable, 0));
    }

    @Test
    public void testGetDateTimeStringDateBoolean() {
        Assert.assertEquals("29.10.2016, 10:00:00", DateUtil.getDateTimeString(CAL.getTimeInMillis(), true));
    }

    @Test
    public void testGetDateTimeStringLongBoolean() {
        Assert.assertEquals("29.10.2016, 10:00:00", DateUtil.getDateTimeString(CAL.getTimeInMillis(), true));
    }

    @Test
    public void testGetDateFromDateString() {
        try {
            Date date = DateUtil.getDateFromDateString("29.10.2016, 10:00:00");
            Assert.assertEquals(CAL.getTimeInMillis(), date.getTime());
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetMillisFromDateString() {try {
        long millis = DateUtil.getMillisFromDateString("29.10.2016, 10:00:00");
        Assert.assertEquals(CAL.getTimeInMillis(), millis);
    } catch (Exception e) {
        fail(e.getMessage());
    }
    }

}
