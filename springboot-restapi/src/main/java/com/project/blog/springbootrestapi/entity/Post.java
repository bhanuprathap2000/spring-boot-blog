package com.project.blog.springbootrestapi.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})

//here we are mapping class to the table in database.
//so for that we need to specify the metadata on the class which then could be used to map to the table.
//we are specifying that this class belongs to the table posts
// entity means instance i.e, is the row of the table.

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    private String description;

    private String content;

    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Comment> comments= new HashSet<>();

}
