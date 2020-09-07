package de.jawb.tools.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Group<K, V> implements Iterable<V> {

    private final K key;
    private List<V> list = new ArrayList<>();

    public Group(K key) {
        this.key = key;
    }

    void add(V el) {
        list.add(el);
    }

    public K getKey() {
        return key;
    }

    public int size() {
        return list.size();
    }

    @Override
    public Iterator<V> iterator() {
        return list.iterator();
    }

}
