package com.dauphine.blogger.services;

import com.dauphine.blogger.dto.CategoryRequest;
import com.dauphine.blogger.dto.PostRequest;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public interface PostService {

    List<Post> getAll();
    Post getById(UUID id);
    Post create(PostRequest postRequest);
    Post update(UUID id, String title, String content, UUID category_id);
    void deletePost(UUID id);
    //List<Post> getAllSortedByCreationDateDesc() ;
    List<Post> getAllByCategory(UUID categoryId) ;


    List<Post> getAllLikeTitle(String title);

    List<Post> getAllSortedByCreationDateDesc();
}
