package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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
    @Operation(
            summary = "Get all categories",
            description = "Retrieve all categories or filter by name"
    )
    public ResponseEntity<List<Category>> getAll(@RequestParam(required=false) String name){
        List <Category> categories = name == null || name.isBlank()
                ? categoryService.getAll()
                : categoryService.getAllLikeName();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Retrieve a category",
            description = "Retrieve a category, only required its id ")
    public Category getById(@PathVariable UUID id){
        return categoryService.getById(id);
    }



    @PostMapping
    @Operation(
            summary = "Create new category",
            description = "Create new category, only required field is the name"
    )
    public ResponseEntity<Category> create(@RequestBody CategoryRequest categoryRequest){
        Category category= categoryService.create(categoryRequest.getName());
        return ResponseEntity.created(URI.create("1/categories/"+category.getId())).body(category);

    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update a category",
            description = "Update a category, only required its id ")
    public Category update(@PathVariable UUID id, @RequestBody String name){
        return categoryService.update(id, name);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a category",
            description = "Delete a category, only required its id ")
    public boolean deleteCategory(@PathVariable UUID id){
        return categoryService.deleteById(id);
    }

}
