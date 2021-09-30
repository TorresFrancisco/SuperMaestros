package com.hector.cinturonnegro.services;

import com.hector.cinturonnegro.models.Category;
import com.hector.cinturonnegro.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends BaseService<Category>{
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }
        public List<Category> categoryList(String name) {
            return categoryRepository.findByName(name);
        }
}
