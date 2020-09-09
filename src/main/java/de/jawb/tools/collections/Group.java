package de.jawb.tools.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Group<K, V> implements Iterable<V>{

    private final K key;
    private final List<V> list = new ArrayList<>();

    public Group(K key) {
        this.key = key;
    }

    void add(V el) {
        list.add(el);
    }

    void addAll(Collection<V> els) {
        list.addAll(els);
    }

    public K key() {
        return key;
    }

    public int size() {
        return list.size();
    }

    public List<V> items(){
        return list;
    }

    @Override
    public Iterator<V> iterator() {
        return list.iterator();
    }
}
