package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import de.jawb.tools.collections.CollectionsUtil;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CollectionsUtilTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testIsEmptyMap() {
        Map<String, String> mapNull = null;
        Map<String, String> mapEmpty = new HashMap<>();

        Assert.assertTrue(CollectionsUtil.isEmpty(mapNull));
        Assert.assertTrue(CollectionsUtil.isEmpty(mapEmpty));
    }

    @Test
    public void testNotEmptyMap() {
        Map<String, String> mapNull = null;
        Map<String, String> map = new HashMap<>();
        map.put("a", "b");
        Assert.assertTrue(CollectionsUtil.notEmpty(map));
        Assert.assertFalse(CollectionsUtil.notEmpty(mapNull));
    }

    @Test
    public void testIsEmptyCollection() {

        Set<String> set1 = null;
        Set<String> set2 = new HashSet<>();
        List<String> list1 = null;
        List<String> list2 = new ArrayList<>();

        Assert.assertTrue(CollectionsUtil.isEmpty(set1));
        Assert.assertTrue(CollectionsUtil.isEmpty(set2));
        Assert.assertTrue(CollectionsUtil.isEmpty(list1));
        Assert.assertTrue(CollectionsUtil.isEmpty(list2));
    }

    @Test
    public void testNotEmptyCollection() {
        Set<String> set1 = new HashSet<>();
        set1.add("a");
        Set<String> set2 = null;
        List<String> list1 = new ArrayList<>();
        list1.add("b");
        List<String> list2 = new ArrayList<>();

        Assert.assertTrue(CollectionsUtil.notEmpty(set1));
        Assert.assertTrue(CollectionsUtil.notEmpty(list1));

        Assert.assertFalse(CollectionsUtil.notEmpty(set2));
        Assert.assertFalse(CollectionsUtil.notEmpty(list2));
    }

    @Test
    public void testIsEmptyObjectArray() {
        String[] arr1 = null;
        Assert.assertTrue(CollectionsUtil.isEmpty(arr1));
        Assert.assertTrue(CollectionsUtil.isEmpty(new String[0]));
    }

    @Test
    public void testNotEmptyObjectArray() {
        String[] arr1 = { "a" };
        String[] arr2 = new String[0];

        Assert.assertTrue(CollectionsUtil.notEmpty(arr1));
        Assert.assertFalse(CollectionsUtil.notEmpty(arr2));
    }

    @Test
    public void testInitMap() {
        Map<String, String> map = CollectionsUtil.initMap("a", "b");
        Assert.assertTrue("b".equals(map.get("a")));
    }

    @Test
    public void testJoin() {
        Assert.assertEquals("a", CollectionsUtil.join(Arrays.asList("a"), ","));
        Assert.assertEquals("a,b,c", CollectionsUtil.join(Arrays.asList("a", "b", "c"), ","));
        Assert.assertNull(CollectionsUtil.join(new ArrayList<>(), ","));
    }

    @Test
    public void testToNoNullItemSet() {
        Set<String> set1 = CollectionsUtil.toNoNullItemSet("a", "b", null, "c", null);
        Assert.assertEquals(set1, new HashSet<>(Arrays.asList("a", "b", "c")));

        Set<String> set2 = CollectionsUtil.toNoNullItemSet((String) null);
        Assert.assertNotNull(set2);
        Assert.assertTrue(set2.isEmpty());

        Set<String> set3 = CollectionsUtil.toNoNullItemSet((String[]) null);
        Assert.assertNotNull(set3);
        Assert.assertTrue(set3.isEmpty());
    }

    @Test
    public void testGetOneValid() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("de", "a");
        map.put("en", "b");
        map.put("fr", "c");

        Assert.assertEquals("a", CollectionsUtil.getOneValid(map));
        Assert.assertEquals("a", CollectionsUtil.getOneValid(map, (Object[])null));
        Assert.assertEquals("a", CollectionsUtil.getOneValid(map, "sp"));
        Assert.assertEquals("a", CollectionsUtil.getOneValid(map, "de"));
        Assert.assertEquals("a", CollectionsUtil.getOneValid(map, "de", "fr"));
        Assert.assertEquals("a", CollectionsUtil.getOneValid(map, "ru", "sp"));

        Assert.assertNull(CollectionsUtil.getOneValid(new HashMap<>(), "ru", "sp"));
    }

    @Test
    public void testContains() {
        Assert.assertTrue(CollectionsUtil.contains(new String[] { "a", "b", "c" }, "b"));
        Assert.assertFalse(CollectionsUtil.contains(new String[] { "a", "b", "c" }, "d"));
    }

}
