package myCollections;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public interface MyIDictionary<K, V>{
    V get(K key);
    V put(K key, V value);
    String toString();
    int size();
    boolean containsKey(K key);
    void remove(K key);
    Collection<V> values();
    boolean containsValue(V value);
    Set<K> keySet();
    Set<Entry<K, V>> entrySet();
    MyIDictionary<K, V> clone_dict();

    Iterable<Entry<K, V>> getAll();
}
