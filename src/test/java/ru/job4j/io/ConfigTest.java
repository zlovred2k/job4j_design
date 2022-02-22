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
        assertThat(config.value("surname"), is(nullValue()));
        assertThat(config.value("#Test"), is(nullValue()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenException() {
        String path = "./data/exc_test.properties";
        Config config = new Config(path);
        config.load();
    }




}