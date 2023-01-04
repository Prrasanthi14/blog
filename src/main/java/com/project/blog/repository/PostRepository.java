package com.project.blog.repository;


import com.project.blog.Entities.Post;
import com.project.blog.Entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {


    @Query(nativeQuery = true,value="select distinct author from post")
    List<String> getAuthors();

    @Query(nativeQuery = true,value="SELECT * from post where lower(title) like %:theSearchName% OR lower(content) like %:theSearchName% OR lower(author) like %:theSearchName% ")
    List<Post> searchPosts(@Param("theSearchName") String theSearchName);

    @Query(nativeQuery = true,value="SELECT * from post order by :sortValue asc ")
    List<Post> sortBlogs(@Param("sortValue") String sortValue);

    @Query(nativeQuery = true,value="select * from post order by title asc ")
    List<Post> sortBlogsByTitle();

    @Query(nativeQuery = true,value="select * from post order by author asc ")
    List<Post> sortBlogsByAuthor();

    @Query(nativeQuery = true,value="select * from post order by published_at asc")
    List<Post> sortBlogsByPublishedAt();

    @Query(nativeQuery = true,value="select id from tag where id :tagitem")
    List<Integer> getTagId(String tagitem);

    @Query(nativeQuery = true,value="select * from tag where id :id")
    List<Integer> getTagById(int id);

}