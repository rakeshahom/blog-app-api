package com.genius.blog.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genius.blog.payloads.ApiResponse;
import com.genius.blog.payloads.CommentDTO;
import com.genius.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO commentDTO,
			@PathVariable("postId") Integer postId) {
		CommentDTO createComment = this.commentService.createComment(commentDTO, postId);

		return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
	}

	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> createComment(@PathVariable("commentId") Integer commentId) {
		this.commentService.deleteComment(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Deleted successfully...!!", true), HttpStatus.OK);
	}

}
