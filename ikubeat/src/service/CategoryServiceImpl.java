package service;

import exception.ValueExistsException;
import model.Category;
import repository.CategoryRepository;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    CategoryRepository repository = new CategoryRepository();

    @Override
    public boolean save(String name, String description) throws ValueExistsException {
        if (!name.isEmpty()) {
            Category category = new Category();
            category.setName(name);
            category.setDescription(description);
            return repository.save(category);
        } else {
            System.err.println("Name is a required field");
            return false;
        }
    }

    @Override
    public void deleteByName(String name) {
        if (!name.isEmpty())
            repository.deleteByName(name);
    }

    @Override
    public Category findByName(String name) {
        if (!name.isEmpty()) {
            return repository.findByName(name);
        } else {
            System.err.println("Name cannot be empty");
            return null;
        }
    }

    @Override
    public List<Category> findAll() {
        return repository.findAll();
    }
}
