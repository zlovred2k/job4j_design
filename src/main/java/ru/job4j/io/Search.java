package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validateArgs(String[] args) {

        if (args.length != 2) {
            throw new IllegalArgumentException("Two arguments are expected");
        }
        if (!new File(args[0]).exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", args[0]));
        }
        if (!new File(args[0]).isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", args[0]));
        }
        if (!args[1].startsWith(".") && args[1].length() > 1) {
            throw new IllegalArgumentException(String.format("Not an extension %s", args[1]));
        }
    }
}