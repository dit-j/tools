package de.jawb.tools.reflection;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.jawb.tools.collections.CollectionsUtil;

public class ReflectionUtil {

    public static Map<String, Object> beanToMap(Object o) {
        return beanToMap(o, "class");
    }

    public static Map<String, Object> beanToTreeMap(Object o) {
        return new TreeMap<>(beanToMap(o, (String[]) null));
    }

    public static Map<String, Object> beanToTreeMap(Object o, String... ignoreFields) {
        return new TreeMap<>(beanToMap(o, ignoreFields));
    }

    public static Map<String, Object> getConstants(Class<?> c) {

        Map<String, Object> map = new HashMap<>();

        for (Field f : c.getDeclaredFields()) {
            int mod = f.getModifiers();
            if (Modifier.isStatic(mod) && Modifier.isPublic(mod) && Modifier.isFinal(mod)) {
                try {
                    map.put(f.getName(), f.get(null));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return map;
    }

    public static Map<String, Object> beanToMap(Object o, String... ignoreFields) {

        try {

            Map<String, Object> map = new HashMap<String, Object>();
            BeanInfo info = Introspector.getBeanInfo(o.getClass());

            if (CollectionsUtil.notEmpty(ignoreFields)) {

                Set<String> ignore = new HashSet<>(Arrays.asList(ignoreFields));

                for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                    Method reader = pd.getReadMethod();
                    if (reader != null && !ignore.contains(pd.getName())) {
                        map.put(pd.getName(), reader.invoke(o));
                    }
                }
            } else {
                for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
                    Method reader = pd.getReadMethod();
                    if (reader != null) {
                        map.put(pd.getName(), reader.invoke(o));
                    }
                }
            }

            return map;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
