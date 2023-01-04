package com.project.blog.Services;

import com.project.blog.Entities.Post;
import com.project.blog.Entities.Tag;
import com.project.blog.repository.PostRepository;
import com.project.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImplementation implements PostServiceInterface{
	
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private TagRepository tagRepository;

	public void savePost(Post post, String tags) {
		String []tagString= tags.split(",");
		List<String> tagsList= tagRepository.getTagsUniq();
		List<Tag> tagsPresent= new ArrayList<>();
		for(String tagitem:tagString){
			if(tagsList.contains(tagitem)){
				int tagids = tagRepository.getTagId(tagitem);
				Tag newTag = tagRepository.findById(tagids).get();
				tagsPresent.add(newTag);
			}
			else{
				Tag newTag= new Tag();
				newTag.setName(tagitem);
				tagsPresent.add(newTag);
			}
		}
//		System.out.println(tagsPresent);
		post.setTags(tagsPresent);
//		System.out.println(post.getTags().toString());
		postRepository.save(post);
	}

	@Override
	public List<Post> getPosts() {
		return postRepository.findAll();
	}

	@Override
	public List<String> getTags() {
	return tagRepository.getTagsUniq();
	}

	@Override
	public List<String> getAuthors() {
		return postRepository.getAuthors();
//		return new ArrayList<>();
	}

	@Override
	public Post getContent(int id) {
		return postRepository.findById(id).get();
	}

	@Override
	public void deleteContent(int id) {
		postRepository.deleteById(id);
	}

	@Override
	public List<Post> searchPosts(String theSearchName) {
	return postRepository.searchPosts(theSearchName.toLowerCase());
	}

	@Override
	public List<Post> sortBlogs(String sortValue) {
		List<Post>posts = new ArrayList<Post>();
		if(sortValue.equals("title")){
			posts= postRepository.sortBlogsByTitle();
		}
		else if(sortValue.equals("author")){
			posts= postRepository.sortBlogsByAuthor();
		} else if (sortValue.equals("published_at")) {
			posts= postRepository.sortBlogsByPublishedAt();
		}
		return posts;
	}

	@Override
	public String getTagsOfPost(Post blog) {
		List<Tag> postTags = blog.getTags();
		String tagString="";
		for(Tag tag:postTags){
			tagString+=tag.getName();
			tagString+=",";
		}
		return tagString.substring(0,tagString.length()-1);
	}
}
