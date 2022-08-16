package com.project.blog.springbootrestapi.controller;


import com.project.blog.springbootrestapi.dtos.PostDto;
import com.project.blog.springbootrestapi.dtos.PostPaginatedDto;
import com.project.blog.springbootrestapi.exception.ResourceNotFoundException;
import com.project.blog.springbootrestapi.service.PostService;
import com.project.blog.springbootrestapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    ResponseEntity<PostDto> createPost( @RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

//    try to avoid hardcoded values for assigning in your application keep in the separate file so that it is easy to track and update
    @GetMapping
    ResponseEntity<PostPaginatedDto> getAllPosts(
            @RequestParam(name="pageNo",defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = AppConstants.DEFAULT_SORT_BY,required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = AppConstants.DEFAULT_SORT_DIRECTION,required = false) String sortDir){
        return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir),HttpStatus.OK);
    }

    @GetMapping("/{id}")

    //? means is of any type in genric
    ResponseEntity<?> getPostById( @PathVariable(name="id") long id){

            return new ResponseEntity<>(postService.getPostById(id),HttpStatus.OK);

    }

    @PutMapping("/{id}")
    ResponseEntity<?> updatePostById( @RequestBody PostDto postDto, @PathVariable(name="id") long id){

            return new ResponseEntity<>(postService.updatePostById(postDto,id),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deletePostById( @PathVariable(name="id") long id){


            return new ResponseEntity<>("Successfully deleted the post with id "+id,HttpStatus.OK);

    }
}
