package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name="Post API",
        description = "Post endpoints"
)
@RequestMapping("/v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @Operation(
            summary = "Get all posts",
            description = "Retrieve all posts or filter by title"
    )
    public List<Post> getAll(@RequestParam(required=false) String title){
        List <Post> posts = title == null || title.isBlank()
                ? postService.getAll()
                : postService.getAllLikeTitle();
        return posts;
    }

    @GetMapping("{id}")
    @Operation(
            summary = "Get a post",
            description = "Get a post, only required its id ")
    public Post getById(@PathVariable UUID id){
        return postService.getById(id);
    }

    @PostMapping
    @Operation(
            summary = "Create a post")
    public Post create(PostRequest postRequest){
        return postService.create(postRequest);
    }

    @PutMapping("{id}")
    @Operation(
            summary = "Update a post",
            description = "Update a post, only required its id ")
    public Post update(@PathVariable UUID id, @RequestBody String title, @RequestBody String content, @RequestBody UUID category_id){
        return postService.update(id, title, content, category_id);
    }

    @DeleteMapping("{id}")
    @Operation(
            summary = "Delete a post",
            description = "Delete a post, only required its id ")
    public boolean deleteCategory(@PathVariable UUID id){
        postService.deletePost(id);
        return true;
    }

    @GetMapping("/latest")
    @Operation(
            summary = "Get  posts by creation date ")
    public List<Post> getAllSortedByCreationDate() {
        return postService.getAllSortedByCreationDateDesc();
    }

    @GetMapping("/category/{categoryId}")
    @Operation(
            summary = "Get all posts by category ",
            description = "Get all posts, only required category ")
    public List<Post> getAllByCategory(@PathVariable UUID categoryId) {
        return postService.getAllByCategory(categoryId);
    }

}
