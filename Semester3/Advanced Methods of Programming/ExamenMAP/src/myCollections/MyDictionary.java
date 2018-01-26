package myCollections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDictionary<K, V> implements MyIDictionary<K, V>{
    private HashMap<K, V> dictionary;

    public MyDictionary() {dictionary = new HashMap<>();}

    @Override
    public V get(K key) {
        return dictionary.get(key);
    }

    @Override
    public V put(K key, V value) {
        return dictionary.put(key, value);
    }

    @Override
    public int size() {
        return dictionary.size();
    }

    @Override
    public boolean containsKey(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void remove(K key) {
        dictionary.remove(key);
    }

    @Override
    public Collection<V> values() {
        return dictionary.values();
    }

    @Override
    public boolean containsValue(V value) {
        return dictionary.containsValue(value);
    }

    @Override
    public Set<K> keySet() {
        return dictionary.keySet();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return dictionary.entrySet();
    }

    @Override
    public MyIDictionary<K, V> clone_dict() {
        MyIDictionary<K, V> dict = new MyDictionary<>();
        for(K key : this.keySet())
            dict.put(key, dictionary.get(key));
        return dict;
    }

    @Override
    public Iterable<Map.Entry<K, V>> getAll() {
        return dictionary.entrySet();
    }

    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        for(K key : this.keySet())
            buffer.append(key.toString()).append(" -> ").append(dictionary.get(key).toString()).append("\n");

        return buffer.toString();
    }
}
