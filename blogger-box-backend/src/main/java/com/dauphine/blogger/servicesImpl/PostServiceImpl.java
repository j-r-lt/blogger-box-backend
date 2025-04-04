package com.dauphine.blogger.servicesImpl;

import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.CategoryService;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {
    private final List<Post> posts;
    private final CategoryService categoryService;

    public PostServiceImpl(CategoryService categoryService) {
        this.posts = new ArrayList<Post>();
        this.categoryService = categoryService;
    }


    //List<Post> getAllSortedByCreationDateDesc() ;


    @Override
    public List<Post> getAllByCategory(UUID categoryID) {
        List<Post> postscategory = new ArrayList<>();
        for (Post post : posts) {
            if (post.getCategory().getUuid() == categoryID) {
                postscategory.add(post);
            }
        }
        return postscategory;
    }

    @Override
    public List<Post> getAll() {
        return posts;
    }

    @Override
    public Post getById(UUID id) {
        for (Post post : posts) {
            if (post.getUuid().equals(id)) {
                return post;
            }
        }

        return null;
    }

    public Post create(PostRequest postRequest){
        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setCreated_date(postRequest.getCreated_date());
        post.setCategory(categoryService.getById(postRequest.getCategory_id()));
        post.setUuid(UUID.randomUUID());
        posts.add(post);
        return post;
    }



    public Post update(UUID id, String title, String content, UUID category_id){
        Post ret = getById(id);
        ret.setTitle(title);
        ret.setContent(content);
        ret.setCategory(categoryService.getById(category_id));
        return ret;
    }

    @Override
    public boolean deletePost(UUID id) {
        return posts.removeIf(post -> post.getUuid().equals(id));
    }
}