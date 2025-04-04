package com.dauphine.blogger.services;


import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.models.Category;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public interface CategoryService {

    List<Category> getAll();
    Category getById(UUID id);
    Category create(CategoryRequest categoryRequest);
    Category update(UUID id, String name);
    boolean deleteById(UUID id);

}
