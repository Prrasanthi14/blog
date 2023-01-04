package com.project.blog.Services;

import com.project.blog.Entities.Comment;
import com.project.blog.Entities.Post;
import com.project.blog.repository.CommentRepository;
import com.project.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CommentServiceImplementation implements CommentServiceInterface{
	

	@Autowired
	private PostRepository postRepository;
	@Autowired
	private CommentRepository commentRepository;


	@Override
	public void saveComment(Comment comment, int parseInt) {
		Post post = postRepository.findById(parseInt).get();
		comment.setPost(post);
		post.getComments().add(comment);
		postRepository.save(post);

	}

	@Override
	public void deleteComment(int cid) {
		commentRepository.deleteById(cid);
	}

	@Override
	public void editComment(String newComment, int cid, int pid) {}

}



