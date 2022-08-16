package com.project.blog.springbootrestapi.dtos;


import com.project.blog.springbootrestapi.entity.Comment;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private long id;

    private String title;

    private String description;

    private String content;

    private Set<Comment> comments;


}
