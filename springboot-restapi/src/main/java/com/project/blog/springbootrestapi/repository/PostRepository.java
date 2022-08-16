package com.project.blog.springbootrestapi.repository;

import com.project.blog.springbootrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

//all the crud operations are provided by this posts repository
//since we extends  JpaRepository we need to add @Repository for the repository,
// and also we need not to add @Transactional for our service classes as they are already prsent in simplejpa repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
