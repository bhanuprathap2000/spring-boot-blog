package com.project.blog.springbootrestapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PostPaginatedDto {

    private List<PostDto> content;

    private int pageNo;

    private int pageSize;

    private int totalPosts;

    private boolean isLast;
}
