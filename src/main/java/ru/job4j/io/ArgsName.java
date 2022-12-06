package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("Нет значения");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        String[] pairs;
        if (args.length == 0) {
            throw new IllegalArgumentException("Нет параметров");
        }
        for (String arg : args) {
            validateArgs(arg);
            pairs = arg.substring(1).split("=", 2);
            values.put(pairs[0], pairs[1]);
        }
    }

    private static void validateArgs(String str) {
        if (!str.contains("=")) {
            throw new IllegalArgumentException("Нет знака '=' " + str);
        }
        if (str.indexOf("=") == str.length() - 1) {
            throw new IllegalArgumentException("Пара значений не найдена" + str);
        }
        if (str.startsWith("-=")) {
            throw new IllegalArgumentException("Ключ отсутствует" + str);
        }
        if (!str.startsWith("-")) {
            throw new IllegalArgumentException("Нет знака '-'" + str);
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Нет параметров");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[]{"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[]{"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}
