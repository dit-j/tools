package de.jawb.tools.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author dit
 * @param <K>
 * @param <V>
 */
public interface IGroupingMap<K, V> {

    /**
     * @param paramK
     * @param paramV
     */
    void add(K paramK, V paramV);

    /**
     * @param key
     * @return
     */
    Group<K, V> getGroup(K key);

    /**
     * @return
     */
    Collection<Group<K, V>> getGroups();

    /**
     * @author dit
     * @param <K>
     * @param <V>
     */
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
}