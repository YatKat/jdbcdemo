package mate.academy.jdbcdemo.dao;

import mate.academy.jdbcdemo.model.Book;
import mate.academy.jdbcdemo.model.Category;

import java.util.Set;

public interface CategoryDao {
    void save(Category category);
    Set<Book> findAllBooks(Long categoryId);
}
