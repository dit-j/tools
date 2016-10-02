package de.jawb.tools.util.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class CollectionsUtil {
    
    public static void main(String[] args) {
        System.out.println(notEmpty(new HashSet<String>()));
    }
    
    public static boolean isEmpty(Collection<?> col) {
        return col == null || col.isEmpty();
    }
    
    public static boolean notEmpty(Collection<?> col) {
        return col != null && !col.isEmpty();
    }
    
    public static boolean notEmpty(Object[] arr) {
        return arr != null && arr.length > 0;
    }
    
    public static String join(Collection<?> list, String separator) {
        
        if (list.isEmpty()) {
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
    
//    public static <K, V> V tryKeys(Map<K, V> map, Object... keys) {
//        if(map.size() == 0) return null;
//
//        for (Object key : keys) {
//            V v = map.get(key);
//            if (v != null) {
//                return v;
//            }
//        }
//        return null;
//    }
    
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
        
        if (map.size() == 0) return null;
        
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
    
    public static boolean contains(Object[] arr, Object item) {
        for (Object object : arr) {
            if (object.equals(item)) return true;
        }
        return false;
    }
    
    public static <E, T> Map<E, T> initMap(E key, T value) {
        Map<E, T> map = new HashMap<E, T>();
        map.put(key, value);
        return map;
    }
}
