package com.project.blog.springbootrestapi.controller;


import com.project.blog.springbootrestapi.dtos.CommentDto;
import com.project.blog.springbootrestapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comments")
    ResponseEntity<CommentDto> createComment(@PathVariable(value="postId") long postId, @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    ResponseEntity<List<CommentDto>> getAllCommentsByPostId(@PathVariable(value="postId") long postId){
        return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId),HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> getCommentById(@PathVariable(value="postId") long postId,@PathVariable(value="commentId") long commentId){

        return new ResponseEntity<>(commentService.getCommentById(postId,commentId),HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    ResponseEntity<CommentDto> updateComment(@PathVariable(value="postId") long postId, @PathVariable(value="commentId") long commentId, @RequestBody CommentDto commentDto){

        return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto),HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    ResponseEntity<String> deleteComment(@PathVariable(value="postId") long postId, @PathVariable(value="commentId") long commentId){

        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted sucessfully",HttpStatus.OK);
    }




}
