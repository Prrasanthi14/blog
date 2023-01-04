package com.project.blog.Services;

import java.util.List;

import com.project.blog.Entities.Post;
import com.project.blog.Entities.Tag;

public interface PostServiceInterface {

	void savePost(Post post, String tags);

//	void setTags(List<Tag> tags);

	List<Post> getPosts();

	List<String> getTags();

	List<String> getAuthors();

	Post getContent(int id);

	void deleteContent(int id);

	List<Post> searchPosts(String theSearchName);

	List<Post> sortBlogs(String sortValue);

	String getTagsOfPost(Post blog);
}