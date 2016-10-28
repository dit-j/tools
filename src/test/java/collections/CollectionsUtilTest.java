package collections;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.jawb.tools.collections.CollectionsUtil;

public class CollectionsUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testIsEmptyMapOfQQ() {
        Map<String, String> mapNull = null;
        Map<String, String> mapEmpty = new HashMap<>();

        Assert.assertTrue(CollectionsUtil.isEmpty(mapNull));
        Assert.assertTrue(CollectionsUtil.isEmpty(mapEmpty));
    }

    @Test
    public void testNotEmptyMapOfQQ() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        Assert.assertTrue(CollectionsUtil.notEmpty(map));
    }

    @Test
    public void testIsEmptyCollectionOfQ() {
    }

    @Test
    public void testNotEmptyCollectionOfQ() {
    }

    @Test
    public void testIsEmptyObjectArray() {
    }

    @Test
    public void testNotEmptyObjectArray() {
    }

    @Test
    public void testInitMap() {
    }

    @Test
    public void testJoin() {
    }

    @Test
    public void testToNoNullItemSet() {
    }

    @Test
    public void testGetOneValid() {
    }

    @Test
    public void testContains() {
    }

}
