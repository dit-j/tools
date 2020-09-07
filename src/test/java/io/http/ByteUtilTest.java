package io.http;

import de.jawb.tools.io.ByteUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ByteUtilTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testGetReadableSizeStringLongBoolean() {
        Assert.assertEquals("1.0 KiB", ByteUtil.getReadableSizeString(1024, true));
        Assert.assertEquals("1.0 MiB", ByteUtil.getReadableSizeString(1024 * 1024, true));
        Assert.assertEquals("1.0 GiB", ByteUtil.getReadableSizeString(1024 * 1024 * 1024, true));
        Assert.assertEquals("2.0 GiB", ByteUtil.getReadableSizeString(1024 * 1024 * 1024 * 2L, true));


        Assert.assertEquals("1.0 KB", ByteUtil.getReadableSizeString(1000, false));
        Assert.assertEquals("1.0 MB", ByteUtil.getReadableSizeString(1000 * 1000, false));
        Assert.assertEquals("1.0 GB", ByteUtil.getReadableSizeString(1000 * 1000 * 1000, false));
        Assert.assertEquals("2.0 GB", ByteUtil.getReadableSizeString(1000 * 1000 * 1000 * 2L, false));
    }

}
