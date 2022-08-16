package com.project.blog.springbootrestapi.service;

import com.project.blog.springbootrestapi.dtos.PostDto;
import com.project.blog.springbootrestapi.dtos.PostPaginatedDto;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto); //the methods in the interface are public by default

   PostPaginatedDto getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir);

    PostDto getPostById(long id);

    PostDto updatePostById(PostDto postDto,long id);

    void deletePostById(long id);

}
