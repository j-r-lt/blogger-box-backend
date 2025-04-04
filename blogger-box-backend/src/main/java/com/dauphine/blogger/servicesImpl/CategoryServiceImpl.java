package com.dauphine.blogger.servicesImpl;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final List<Category> categories;

    public CategoryServiceImpl() {
        categories = new ArrayList<>();
    }

    @Override
    public List<Category> getAll() {
        return categories;
    }

    @Override
    public Category getById(UUID id) {
        for (Category category : categories) {
            if (category.getUuid().equals(id)) {
                return category;
            }
        }
        return null;
    }

    @Override
    public Category create(CategoryRequest categoryRequest){
        Category category = new Category();
        category.setName(categoryRequest.getName());
        category.setUuid(UUID.randomUUID());
        categories.add(category);
        return category;
    }


    @Override
    public Category update(UUID id, String name) {
        for (Category category : categories) {
            if (category.getUuid().equals(id)) {
                category.setName(name);
                return category;
            }
        }
        return null;
    }

    @Override
    public boolean deleteById(UUID id) {
        return categories.removeIf(category -> category.getUuid().equals(id));
    }


}