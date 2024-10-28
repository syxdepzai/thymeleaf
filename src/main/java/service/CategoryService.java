package service;


import model.Category;
import repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Page<Category> listCategories(String search, Pageable pageable) {
        return repository.findByNameContaining(search, pageable);
    }

    public Category getCategoryById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void saveCategory(Category category) {
        repository.save(category);
    }

    public void deleteCategory(Long id) {
        repository.deleteById(id);
    }
}
