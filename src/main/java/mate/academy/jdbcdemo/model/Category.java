package mate.academy.jdbcdemo.model;

import java.util.Set;

public class Category {
    private long id;
    private String name;
    private Set<Book> books;

    public Category() {
    }

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Set<Book> getBoobs() {
        return books;
    }

    public void setBoobs(Set<Book> boobs) {
        this.books = boobs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
