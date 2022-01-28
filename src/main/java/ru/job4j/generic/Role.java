package ru.job4j.generic;

public class Role extends Base {

    private String name;

    public Role(String id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
