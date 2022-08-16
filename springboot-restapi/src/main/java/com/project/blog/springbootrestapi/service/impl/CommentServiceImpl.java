package com.project.blog.springbootrestapi.service.impl;


import com.project.blog.springbootrestapi.dtos.CommentDto;
import com.project.blog.springbootrestapi.entity.*;
import com.project.blog.springbootrestapi.exception.BlogApiException;
import com.project.blog.springbootrestapi.exception.ResourceNotFoundException;
import com.project.blog.springbootrestapi.repository.CommentRepository;
import com.project.blog.springbootrestapi.repository.PostRepository;
import com.project.blog.springbootrestapi.service.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    Logger logger = LoggerFactory.getLogger(CommentService.class);


    private CommentRepository commentRepository;
    private PostRepository postRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository=postRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Comment comment= mapToEntity(commentDto);
        var post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","id",postId));

        comment.setPost(post);

        var addedComment=commentRepository.save(comment);

        return mapToDto(addedComment);

    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(long postId) {

        //get the comments belong to the post with the post id
        List<Comment> comments= commentRepository.findByPostId(postId);

        return comments.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        var post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","id",postId));

        //get the comments belong to the post with the post id
        Comment comment= commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));

        //throw exception when post and comment are not related by the given id



        if(!comment.getPost().getId().equals(post.getId())){

            throw  new BlogApiException(postId,commentId);
        }

        return mapToDto(comment);



    }

    @Override
    public CommentDto updateComment(long postId, long commentId, CommentDto commentDto) {

        var post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","id",postId));

        //get the comments belong to the post with the post id
        var comment= commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));

        //throw exception when post and comment are not related by the given id

        if(!comment.getPost().getId().equals(post.getId())){

            throw  new BlogApiException(postId,commentId);
        }

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());

        var updatedComment=commentRepository.save(comment);

        return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(long postId, long commentId) {

        var post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","id",postId));

        //get the comments belong to the post with the post id
        var comment= commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment","id",commentId));

        //throw exception when post and comment are not related by the given id

        if(!comment.getPost().getId().equals(post.getId())){

            throw  new BlogApiException(postId,commentId);
        }

        commentRepository.delete(comment);
    }

    private CommentDto mapToDto(Comment comment){
        var commentDto= new CommentDto();

        commentDto.setId(comment.getId());
        commentDto.setName(comment.getName());
        commentDto.setEmail(comment.getEmail());
        commentDto.setBody(comment.getBody());

        return commentDto;

    }

    private Comment mapToEntity(CommentDto commentDto){
        var comment= new Comment();

        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }
}
