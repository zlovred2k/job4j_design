package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicateVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> files = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty property = new FileProperty(file.toFile().length(), file.toFile().getName());
        files.putIfAbsent(property, new ArrayList<>());
        files.get(property).add(file.toAbsolutePath());
        return super.visitFile(file, attrs);
    }

    public void findDuplicates() {
        for (Map.Entry<FileProperty, List<Path>> entry : files.entrySet()) {
            if (entry.getValue().size() > 1) {
                System.out.println("Duplicates found: " + entry.getKey().getName() + " " + entry.getValue().size());
                for (Path path : entry.getValue()) {
                    System.out.println(path);
                }
            }
        }
    }
}
