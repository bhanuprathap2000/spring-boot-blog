package com.project.blog.springbootrestapi.exception;

public class BlogApiException extends RuntimeException {


    public BlogApiException(long postId,long commentId) {
        super(String.format(" comment with id %s doesn't exists for post with id %s ",commentId,postId));
    }
}
