package reflection;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.jawb.tools.reflection.ReflectionUtil;

public class ReflectionUtilTest {

    private TestBeanStudent student;

    @Before
    public void setUp() throws Exception {
        student = new TestBeanStudent("a", 42);
    }

    @Test
    public void testBeanToMapObject() {
        Map<String, Object> map = ReflectionUtil.beanToMap(student);

        Assert.assertEquals(2, map.size());
        Assert.assertEquals("a", map.get("name"));
        Assert.assertEquals(42, map.get("age"));
    }

    @Test
    public void testBeanToTreeMapObject() {
        Map<String, Object> map = ReflectionUtil.beanToMap(student, "age", "class");
        System.out.println(map);
        Assert.assertEquals(1, map.size());
        Assert.assertEquals("a", map.get("name"));
    }

    @Test
    public void testBeanToTreeMapObjectStringArray() {}

    @Test
    public void testGetConstants() {}

    @Test
    public void testBeanToMapObjectStringArray() {}

}
