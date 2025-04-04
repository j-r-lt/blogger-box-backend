package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name="Category API",
        description = "Category endpoints"
)
@RequestMapping("/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getAll(){
        return categoryService.getAll();
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable UUID id){
        return categoryService.getById(id);
    }

    @PostMapping
    public Category create(CategoryRequest categoryRequest){
        return categoryService.create(categoryRequest);
    }

    @PutMapping("{id}")
    public Category update(@PathVariable UUID id, @RequestBody String name){
        return categoryService.update(id, name);
    }

    @DeleteMapping("{id}")
    public boolean deleteCategory(@PathVariable UUID id){
        return categoryService.deleteById(id);
    }

}
