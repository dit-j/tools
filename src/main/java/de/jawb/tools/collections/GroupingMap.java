package de.jawb.tools.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @param <K>
 * @param <V>
 * @author dit
 */
public class GroupingMap<K, V> implements Iterable<Group<K, V>> {

    private final Map<K, Group<K, V>> map = new HashMap<>();

    public void add(K key, V paramV) {
        Group<K, V> l = getGroup(key);
        if (l == null) {
            map.put(key, l = new Group<>(key));
        }
        l.add(paramV);
    }

    public void addAll(K key, Collection<V> values){
        Group<K, V> l = getGroup(key);
        if (l == null) {
            map.put(key, l = new Group<>(key));
        }
        l.addAll(values);
    }

    public final Group<K, V> getGroup(K key) {
        return map.get(key);
    }

    public Collection<Group<K, V>> getGroups() {
        return map.values();
    }

    @Override
    public Iterator<Group<K, V>> iterator() {
        return map.values().iterator();
    }
}
