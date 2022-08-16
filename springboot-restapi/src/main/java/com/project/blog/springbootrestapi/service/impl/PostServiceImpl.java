package com.project.blog.springbootrestapi.service.impl;


import com.project.blog.springbootrestapi.dtos.PostDto;
import com.project.blog.springbootrestapi.dtos.PostPaginatedDto;
import com.project.blog.springbootrestapi.entity.Post;
import com.project.blog.springbootrestapi.exception.ResourceNotFoundException;
import com.project.blog.springbootrestapi.repository.PostRepository;
import com.project.blog.springbootrestapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    //let's inject the PostRepository using the Constructor injection

    private final PostRepository postRepository;

    @Autowired
    PostServiceImpl( PostRepository postRepository){
        this.postRepository= postRepository;
    }


    //one more thing for child classes we cannot assign a lower access than it's parent access.
    @Override
    public PostDto createPost(PostDto postDto){

        //converting the dto to Entity
        //dto means data transfer object
        var post= mapToPost(postDto);

        var newPost= postRepository.save(post); //this will return the saved entity


        return mapToDto(newPost); //if we use new keyword in that case only the constructor will be called.

    }

    @Override
    public PostPaginatedDto getAllPosts(int pageNo, int pageSize,String sortBy,String sortDir) {

        //create a page-request instance using the pageno and pagesize

        //here we are also checking for sorting whether ascending or descending
        Sort sort= sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        var pageable =  PageRequest.of(pageNo,pageSize, sort);

        //findall method can accept the page-quest object to get pagination and sorting
        //here we get the page of posts unlike normal list of posts
        //we need to call the get content method to get the data out of the Page<Post>
        var posts=postRepository.findAll(pageable);

        var listOfPosts= posts.getContent().stream().map(post->mapToDto(post)).collect(Collectors.toList());// getting all the posts and converting them to the List of PostDto and sending them back

        return new PostPaginatedDto(listOfPosts,posts.getNumber(),posts.getSize(),posts.getTotalPages(),posts.isLast());

    }

    @Override
    public PostDto getPostById(long id) {

        var post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));

        return mapToDto(post);

    }

    @Override
    public PostDto updatePostById(PostDto postDto,long id) {

        var post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        postRepository.save(post);

        return mapToDto(post);
    }

    @Override
    public void deletePostById(long id) {

        var post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        postRepository.delete(post);
    }

    private PostDto mapToDto(Post post){

        var postDto= new PostDto();

        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;

    }

    private Post mapToPost(PostDto postDto){
        var post= new Post();

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

}
