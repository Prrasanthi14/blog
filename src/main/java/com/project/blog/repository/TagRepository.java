package com.project.blog.repository;

import com.project.blog.Entities.Post;
import com.project.blog.Entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("select id from Tag where name=:tagitem")
    public int getTagId(@Param("tagitem") String tagitem);

    @Query(nativeQuery = true,value="select distinct name from tag")
    List<String> getTagsUniq();
}
