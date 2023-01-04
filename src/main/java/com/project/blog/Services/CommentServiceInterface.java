package com.project.blog.Services;


import com.project.blog.Entities.Comment;

public interface CommentServiceInterface {

	public void saveComment(Comment comment, int parseInt);


	void deleteComment(int cid);

	void editComment(String newComment, int cid, int pid);
}
