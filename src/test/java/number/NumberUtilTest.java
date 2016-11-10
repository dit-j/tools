package number;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.jawb.tools.number.NumberUtil;

public class NumberUtilTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testRoundDouble() {
        Assert.assertEquals(3.14, NumberUtil.round(3.141516), 0.0);
        Assert.assertEquals(3.15, NumberUtil.round(3.145), 0.0);
    }

    @Test
    public void testRoundDoubleInt() {
        Assert.assertEquals(3.142, NumberUtil.round(3.141516, 3), 0.0);
        Assert.assertEquals(3.140, NumberUtil.round(3.1401, 3), 0.0);
    }

    @Test
    public void testReadableString() {
        Assert.assertEquals("100.000", NumberUtil.readableString(100_000, "."));
        Assert.assertEquals("1.000", NumberUtil.readableString(1000, "."));

        Assert.assertEquals("100 000", NumberUtil.readableString(100_000, " "));
        Assert.assertEquals("1 000", NumberUtil.readableString(1000, " "));
    }

    @Test
    public void testCutZero() {
        Assert.assertEquals("6", NumberUtil.cutZero(6.0));
        Assert.assertEquals("6.3", NumberUtil.cutZero(6.3));
    }

    @Test
    public void testParseLong() {
        Assert.assertEquals(Long.valueOf(123), NumberUtil.parseLong("123"));
    }

}
