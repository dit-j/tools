package de.jawb.tools.collections;

import java.util.*;

public class CollectionsUtil {

    public static char[] remove(char[] array, char[] sequenceToRemove) {

        int start;
        char[] temp = array;

        while ((start = find(temp, sequenceToRemove)) >= 0) {

            int end = start + sequenceToRemove.length;

            int newLength   = temp.length - sequenceToRemove.length;
            char[] newArray = new char[newLength];
            int j = 0;

            for (int i = 0; i < temp.length; i++) {
                if (i < start || i >= end) {
                    newArray[j++] = temp[i];
                }
            }
            temp = newArray;
        }

        return temp;
    }

    public static boolean contains(char[] array, char[] searchSequence){
        return find(array, searchSequence) >= 0;
    }

    public static int find(char[] array, char[] searchSequence){

        if(isEmpty(searchSequence)){
            return -1;
        }

        for(int start = 0; start < array.length; start++){

            int i    = start;
            int hits = 0;
            for(int j = 0; j < searchSequence.length; j++){

                if(array[i] == searchSequence[j]){
                    ++hits;
                } else {
                    hits = 0;
                    break;
                }

                ++i;

                if(i > array.length - 1){
                    break;
                }
            }

            if(hits == searchSequence.length){
                return start;
            }

        }

        return -1;
    }

    public static boolean isEmpty(char[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isEmpty(long[] longs) {
        return longs == null || longs.length == 0;
    }

    public static boolean isEmpty(int[] ints) {
        return ints == null || ints.length == 0;
    }

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

    public static <E, T> Map<E, T> mapOf(E key, T value) {
        Map<E, T> map = new HashMap<E, T>();
        map.put(key, value);
        return map;
    }

    public static String toString(long[] longs, String separator) {
        if(isEmpty(longs)){
            return null;
        }
        List<Long> list = new ArrayList<>();
        for (long i : longs) {
            list.add(i);
        }
        return toString(list, separator);
    }

    public static String toString(int[] array, String separator) {
        if(isEmpty(array)){
            return null;
        }
        List<Integer> list = new ArrayList<>();
        for (int i : array) {
            list.add(i);
        }
        return toString(list, separator);
    }

    public static String toString(Collection<?> list, String separator) {

        if (isEmpty(list)) {
            return "";
        }

        if (list.size() == 1) {
            return list.toArray()[0].toString();
        }

        StringBuilder sb = new StringBuilder();
        Iterator<?> it = list.iterator();
        while (it.hasNext()) {

            sb.append(it.next());

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

    public static <K, V> V getOneValid(Map<K, V> map, Object... preferredKeys) {

        if (map.size() == 0) {
            return null;
        }

        if (preferredKeys != null) {
            if (preferredKeys.length == 1) {
                V v = map.get(preferredKeys[0]);
                if (v != null) return v;
            } else {
                for (Object key : toNoNullItemSet(preferredKeys)) { // new HashSet<>(Arrays.asList(suggestedKeys))
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

    public static <T> List<T> toList(Enumeration<T> enumeration){
        List<T> list = new ArrayList<>();
        while(enumeration.hasMoreElements()){
            list.add(enumeration.nextElement());
        }
        return list;
    }
}
