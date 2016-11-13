package de.jawb.tools.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CollectionsUtil {

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean notEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static boolean isEmpty(Collection<?> col) {
        return col == null || col.isEmpty();
    }

    public static boolean notEmpty(Collection<?> col) {
        return !isEmpty(col);
    }

    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean notEmpty(Object[] arr) {
        return !isEmpty(arr);
    }

    public static <E, T> Map<E, T> initMap(E key, T value) {
        Map<E, T> map = new HashMap<E, T>();
        map.put(key, value);
        return map;
    }

    public static String join(Collection<?> list, String separator) {

        if (isEmpty(list)) {
            return null;
        }

        if (list.size() == 1) {
            return list.toArray()[0].toString();
        }

        StringBuilder sb = new StringBuilder();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
            if (it.hasNext()) {
                sb.append(separator);
            }
        }

        return sb.toString();
    }

    @SafeVarargs
    public static <E> Set<E> toNoNullItemSet(E... es) {
        Set<E> set = new HashSet<>();
        if (es != null) {
            for (E e : es) {
                if (e != null) {
                    set.add(e);
                }
            }
        }
        return set;
    }

    public static <K, V> V getOneValid(Map<K, V> map, Object... prefferedKeys) {

        if (map.size() == 0) {
            return null;
        }

        if (prefferedKeys != null) {
            if (prefferedKeys.length == 1) {
                V v = map.get(prefferedKeys[0]);
                if (v != null) return v;
            } else {
                for (Object key : toNoNullItemSet(prefferedKeys)) { // new HashSet<>(Arrays.asList(suggestedKeys))
                    V v = map.get(key);
                    if (v != null) {
                        return v;
                    }
                }
            }
        }

        return map.values().iterator().next();
    }

    public static int find(Object[] arr, Object item) {
        for (int i = 0; i < arr.length; ++i) {
            Object object = arr[i];
            if(object.equals(item)){
                return i;
            }
        }
        return -1;
    }

    public static boolean contains(Object[] arr, Object item) {
        return find(arr, item) >= 0;
    }
}
