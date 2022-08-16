package com.project.blog.springbootrestapi.service;

import com.project.blog.springbootrestapi.dtos.CommentDto;
import com.project.blog.springbootrestapi.entity.Comment;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId,CommentDto comment);

    List<CommentDto> getAllCommentsByPostId(long postId);

    CommentDto  getCommentById(long postId,long commentId);

    CommentDto updateComment(long postId,long commentId,CommentDto commentDto);

    void deleteComment(long postId,long commentId);
}
