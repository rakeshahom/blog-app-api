package com.genius.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.blog.entities.Comment;
import com.genius.blog.entities.Post;
import com.genius.blog.exceptions.ResourceNotFoundException;
import com.genius.blog.payloads.CommentDTO;
import com.genius.blog.repositories.CommentRepo;
import com.genius.blog.repositories.PostRepo;
import com.genius.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Posts", "post id", postId));
		Comment comment = this.modelMapper.map(commentDTO, Comment.class);
		comment.setPost(post);
		Comment savecomments = this.commentRepo.save(comment);
		return this.modelMapper.map(savecomments, CommentDTO.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
		this.commentRepo.delete(comment);
	}

}
