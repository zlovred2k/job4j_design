package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name"), is("Petr Arsentev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExceptionAfter() {
        String path = "./data/exc_test2.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenExceptionBefore() {
        String path = "./data/exc_test.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test
    public void whenComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("#Test"), is(nullValue()));
    }
}