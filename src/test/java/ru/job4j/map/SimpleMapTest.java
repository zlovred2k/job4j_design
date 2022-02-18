package ru.job4j.map;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;


public class SimpleMapTest {

    @Test
    public void whenPutTrue() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, 1));
        assertTrue(simpleMap.put(2, 2));
    }

    @Test
    public void whenPutFalse() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 1);
        assertFalse(simpleMap.put(1, 2));
    }

    @Test
    public void whenGetValue() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 1);
        assertThat(simpleMap.get(1),is(1));
    }

    @Test
    public void whenGetNull() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 1);
        assertThat(simpleMap.get(2),is(nullValue()));
    }

    @Test
    public void whenRemoveTrue() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 1);
        assertTrue(simpleMap.remove(1));
    }

    @Test
    public void whenRemoveFalse() {
        SimpleMap<Integer, Integer> simpleMap = new SimpleMap<>();
        simpleMap.put(1, 1);
        assertFalse(simpleMap.remove(2));
    }
}