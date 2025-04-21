package com.dauphine.blogger.servicesImpl;

import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.exceptions.CategoryNotFoundException;
import com.dauphine.blogger.exceptions.PostNotFoundException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository repository;
    private final CategoryService categoryService;

    public PostServiceImpl(PostRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }


    @Override
    public List<Post> getAllByCategory(UUID categoryID) {
        List<Post> postsCategory = new ArrayList<>();
        for (Post post : repository.findAll()) {
            if (post.getCategory().getId().equals(categoryID)) {
                postsCategory.add(post);
            }
        }
        return postsCategory;
    }

    @Override
    public List<Post> getAllLikeTitle(String title) {
        return repository.findByTitleContainingIgnoreCase(title);  // Recherche insensible à la casse
    }

    @Override
    public List<Post> getAllSortedByCreationDateDesc() {
        return repository.findAllByOrderByCreatedDateDesc();
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }


    @Override
    public Post getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post avec l'id " + id + " non trouvé."));
    }

    @Override
    public Post create(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCreated_date(postRequest.getCreated_date());
        Category category = categoryService.getById(postRequest.getCategory_id());
        if (category == null) {
            throw new CategoryNotFoundException("Catégorie avec l'id " + postRequest.getCategory_id() + " non trouvée.");
        }
        post.setCategory(category);
        repository.save(post);
        return post;
    }

    @Override
    public Post update(UUID id, String title, String content, UUID categoryId) {
        Post post = repository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post avec l'id " + id + " non trouvé."));

        post.setTitle(title);
        post.setContent(content);
        post.setCategory(categoryService.getById(categoryId));
        return repository.save(post);
    }

    public void deletePost(UUID id) {
        if (!repository.existsById(id)) {
            throw new PostNotFoundException("Post avec l'id " + id + " non trouvé.");
        }
        repository.deleteById(id);
    }
}