package collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.jawb.tools.collections.GroupingMap;
import de.jawb.tools.collections.IGroupingMap;

public class GroupingMapTest {

    @Before
    public void setUp() throws Exception {}

    @Test
    public void testGeneral() {
        IGroupingMap<String, String> gmap = new GroupingMap<>();
        gmap.add("a", "a");
        gmap.add("a", "a");
        gmap.add("a", "a");

        gmap.add("b", "b");
        gmap.add("b", "b");
        gmap.add("b", "b");

        Assert.assertEquals(3, gmap.getGroup("a").size());
        Assert.assertEquals(3, gmap.getGroup("b").size());
        Assert.assertEquals(2, gmap.getGroups().size());
    }
}
