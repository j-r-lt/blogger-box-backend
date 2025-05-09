package com.dauphine.blogger.servicesImpl;

import com.dauphine.blogger.exceptions.CategoryNotFoundException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.services.CategoryService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Category> getAll() {
        return repository.findAll();
    }

    @Override
    public Category getById(UUID id) {
        return repository.findById(id)
                    .orElse(null);
    }

    @Override
    public Category create(String name){
        Category category = new Category(name);
        return repository.save(category);
    }


    @Override
    public Category update(UUID id, String name) {
        Category category = getById(id);
        if (category == null) {
            return null;
        }
        category.setName(name);
        return repository.save(category);
    }
/*
    @Override
    public boolean deleteById(UUID id) {
        if (!repository.existsById(id)) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }*/

    @Override
    public void deleteById(UUID id) {
        if (!repository.existsById(id)) {
            throw new CategoryNotFoundException("Catégorie avec l'id " + id + " non trouvée.");
        }
        repository.deleteById(id);
    }
/*
    @Override
    public List<Category> getAllLikeName() {
        return List.of();
    }*/

    @Override
    public List<Category> getAllLikeName(String name) {
        return repository.findAllLikeName(name);
    }


}