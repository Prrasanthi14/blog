package com.project.blog.Controller;

import java.util.ArrayList;
import java.util.List;

import com.project.blog.Entities.Comment;
import com.project.blog.Entities.Post;
import com.project.blog.Entities.Tag;
import com.project.blog.Services.CommentServiceInterface;
import com.project.blog.Services.PostServiceInterface;
import com.project.blog.repository.PostRepository;
import com.project.blog.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
public class BlogHomeController {

	@Autowired
	private PostServiceInterface postService;

	@Autowired
	private CommentServiceInterface commentService;
	@Autowired
	private PostRepository postRepository;
	@Autowired
	private TagRepository tagRepository;

	@RequestMapping("/showWritePage")
	public String writePage(Model model) {
		model.addAttribute("postObj",new Post());
		return "writeblog";
	}

	@RequestMapping("/savePost")
	public String savePost(@ModelAttribute("postObj") Post post,@RequestParam ("tag") String tags) {
		postService.savePost(post,tags);
		return "redirect:/";
	}

	@RequestMapping("/")
	public String showBlogs(Model model) {
		List <Post> posts = postService.getPosts();
		model.addAttribute("blogPosts",posts);
		List <String> tags = postService.getTags();
		model.addAttribute("blogTags",tags);
		List <String> authors=postService.getAuthors();
		model.addAttribute("blogAuthor",authors);
		return "showblogs";

	}

	@RequestMapping("reset")
	public String reset(Model model) {
		return "redirect:/";
	}

	@RequestMapping("/viewBlog")
	public String viewBlog(Model model,@RequestParam ("postId") String id) {
		Post blog=postService.getContent(Integer.parseInt(id));
		Comment comment = new Comment();
		model.addAttribute("commentObj",comment);
		model.addAttribute("blogPost",blog);
		return "viewblog";

	}

	@RequestMapping("/editBlog")
	public String editBlog(Model model,@RequestParam ("postId") int id) {
		Post blog=postService.getContent(id);
//		model.addAttribute("postObj", postRepository.save(blog));
		model.addAttribute("postObj",blog);
		String tags = postService.getTagsOfPost(blog);
		model.addAttribute("tagList", tags);
		return "writeblog";
	}

	@RequestMapping("/deleteBlog")
	public String deleteBlog(Model model,@RequestParam ("postId") int id) {
		postService.deleteContent(id);
		return "redirect:/";
	}

	@RequestMapping("/saveComment")
	public String saveComment(@ModelAttribute("commentObj") Comment comment,@RequestParam("postId") int id, Model model) {
		System.out.println("Entered save comment");
		commentService.saveComment(comment,id);
		Post blog=postService.getContent(id);
		model.addAttribute("commentObj",new Comment());
		model.addAttribute("blogPost",blog);
		model.addAttribute("postId",id);
		return "viewblog";
	}

	@RequestMapping("/deleteComment")
	public String deleteComment(Model model,@RequestParam ("postId") int pid,@RequestParam ("commentId") int cid) {
		commentService.deleteComment(cid);
		model.addAttribute("postId",pid);
		return "redirect:/viewBlog";
	}

	@RequestMapping("/editComment")
	public String editComment(Model model,@RequestParam ("postId") int pid,@RequestParam ("commentContent") String newComment,@RequestParam ("commentId") int cid) {
		System.out.println("ENtered edit comment");
		commentService.editComment(newComment,cid,pid);
		model.addAttribute("postId",pid);
		return "comment";
	}

	@RequestMapping("/search")
	public String searchBlog( Model model,@RequestParam("theSearchName") String theSearchName)
	{
		List<Post> posts = postService.searchPosts(theSearchName);
		model.addAttribute("blogPosts",posts);
		return "showblogs";
	}

	@RequestMapping("/sort")
	public String sortBlogs( Model model,@RequestParam("sortValue") String sortValue)
	{
		List<Post> posts = postService.sortBlogs(sortValue);
		model.addAttribute("blogPosts",posts);
		List <String> tags = postService.getTags();
		model.addAttribute("blogTags",tags);
		List <String> authors=postService.getAuthors();
		model.addAttribute("blogAuthor",authors);
		return "showblogs";
	}

	@RequestMapping("/filter")
	public String filter(Model model,@RequestParam("tag") String tag,@RequestParam("authorName") String author) {
//		List <Post> posts = postService.getFilteredPosts(tag,author);
//		model.addAttribute("blogPosts",posts);
		List <String> tags = postService.getTags();
		model.addAttribute("blogTags",tags);
		List <String> authors=postService.getAuthors();
		model.addAttribute("blogAuthor",authors);
		return "showblogs";
	}
}
		
