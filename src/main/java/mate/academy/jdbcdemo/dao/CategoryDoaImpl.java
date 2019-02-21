package mate.academy.jdbcdemo.dao;

import mate.academy.jdbcdemo.model.Book;
import mate.academy.jdbcdemo.model.Category;

import java.util.Set;

public class CategoryDoaImpl implements CategoryDao {
    public void save(Category category) {

    }

    @Override
    public Set<Book> findAllBooks(Long categoryId) {
        return null;
    }
}
