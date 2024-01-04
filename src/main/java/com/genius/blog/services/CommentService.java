package com.genius.blog.services;

import com.genius.blog.payloads.CommentDTO;

public interface CommentService {

	
	CommentDTO createComment(CommentDTO commentDTO,Integer postId);
	void deleteComment(Integer commentId);
	
}
