package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((float) count / (float) capacity >= LOAD_FACTOR) {
            expand();
        }
        boolean rsl = false;
        int keyHash = hash(key.hashCode());
        int index = indexFor(keyHash);
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] temp = table;
        table = new MapEntry[capacity];
        for (int i = 0; i < capacity / 2; i++) {
            if (temp[i] != null) {
                put(temp[i].key, temp[i].value);
            }
        }
    }

    @Override
    public V get(K key) {
        V rsl = null;
        int keyIndexFor = indexFor(hash(key.hashCode()));
        if (table[keyIndexFor] != null && Objects.equals(table[keyIndexFor].key, key)) {
            rsl = table[keyIndexFor].value;
        }
        return rsl;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int keyIndexFor = indexFor(hash(key.hashCode()));
        if (table[keyIndexFor] != null && Objects.equals(table[keyIndexFor].key, key)) {
            table[keyIndexFor].value = null;
            table[keyIndexFor].key = null;
            modCount++;
            count--;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private final int expectedModCount = modCount;
            private int counter = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (counter < capacity && table[counter] == null) {
                    counter++;
                }
                return counter < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[counter].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
