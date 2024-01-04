package com.genius.blog.services;

import java.util.List;

import com.genius.blog.entities.Post;
import com.genius.blog.payloads.PostDTO;
import com.genius.blog.payloads.PostResponse;

public interface PostService {

//create post
	PostDTO craetePost(PostDTO postDTO, Integer userId, Integer categoryId);

//	update post
	PostDTO updatePost(PostDTO postDTO, Integer postId);

//	delete post
	void deletePost(Integer postId);

//	get all posts
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);

//	get single post
	PostDTO getPostById(Integer postId);

//	get all post by category

	List<PostDTO> getPostByCategory(Integer categoryId);

//	get all post by user
	List<PostDTO> getPostByUser(Integer userId);

// serch post
	List<PostDTO> searchPosts(String keyword);

}
