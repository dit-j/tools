package de.jawb.tools.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dit
 * @param <K>
 * @param <V>
 */
public class GroupingMap<K, V> implements IGroupingMap<K, V> {

    private final Map<K, Group<K, V>> map = new HashMap<>();

    @Override
    public void add(K key, V paramV) {
        Group<K, V> l = getGroup(key);
        if (l == null) {
            map.put(key, l = new Group<>(key));
        }
        l.add(paramV);
    }

    @Override
    public final Group<K, V> getGroup(K key) {
        Group<K, V> group = map.get(key);
        return group;
    }

    @Override
    public Collection<Group<K, V>> getGroups() {
        return map.values();
    }
}
