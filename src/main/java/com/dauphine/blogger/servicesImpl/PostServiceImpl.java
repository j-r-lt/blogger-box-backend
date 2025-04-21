package com.dauphine.blogger.servicesImpl;

import com.dauphine.blogger.dto.PostRequest;
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
            if (post.getCategory().getId() == categoryID) {
                postsCategory.add(post);
            }
        }
        return postsCategory;
    }

    @Override
    public List<Post> getAllLikeTitle() {
        return List.of();
    }

    @Override
    public List<Post> getAllSortedByCreationDateDesc() {
        return List.of();
    }

    @Override
    public List<Post> getAll() {
        return repository.findAll();
    }


    @Override
    public Post getById(UUID id) {
        return repository.findById(id)
                .orElse(null);
    }

    @Override
    public Post create(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCreated_date(postRequest.getCreated_date());
        post.setCategory(categoryService.getById(postRequest.getCategory_id()));
        post.setUuid(UUID.randomUUID());
        repository.save(post);
        return post;
    }


    @Override
    public Post update(UUID id, String title, String content, UUID category_id){
        Post ret = getById(id);
        ret.setTitle(title);
        ret.setContent(content);
        ret.setCategory(categoryService.getById(category_id));
        return repository.save(ret);
    }

    @Override
    public boolean deletePost(UUID id) {
        repository.deleteById(id);
        return  true;
    }
}