package ru.job4j.map;

import java.util.Iterator;

public class SimpleMap<K, V> implements Iterable {
    private SimpleHashTable<K, V> table;
    private int size;

    public SimpleMap(int size) {
        this.table = new SimpleHashTable<>(size);
        this.size = size;

    }

    public boolean insert(K key, V value) {
        return this.table.add(key, value);
    }

    public V get(K key) {
        return this.table.getValue(key);
    }

    public boolean delete(K key) {
        return this.table.remove(key);
    }

    public int getSize() {
        return this.table.getCounter();
    }

    @Override
    public Iterator iterator() {
        return null;
    }
}
