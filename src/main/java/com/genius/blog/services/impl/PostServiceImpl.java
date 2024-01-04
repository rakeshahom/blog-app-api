package com.genius.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.genius.blog.entities.Category;
import com.genius.blog.entities.Post;
import com.genius.blog.entities.User;
import com.genius.blog.exceptions.ResourceNotFoundException;
import com.genius.blog.payloads.PostDTO;
import com.genius.blog.payloads.PostResponse;
import com.genius.blog.repositories.CategoryRepo;
import com.genius.blog.repositories.PostRepo;
import com.genius.blog.repositories.UserRepo;
import com.genius.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDTO craetePost(PostDTO postDTO, Integer userId, Integer categoryId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user Id", userId));
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("category", "category Id", categoryId));

		Post post = this.modelMapper.map(postDTO, Post.class);
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post savepost = this.postRepo.save(post);
		return this.modelMapper.map(savepost, PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		post.setImageName(postDTO.getImageName());
		post.setAddedDate(postDTO.getAddedDate());
		Post updatepost = this.postRepo.save(post);
		return this.modelMapper.map(updatepost, PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post Id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
		Sort sort = (sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> Allposts = pagePost.getContent();

		List<PostDTO> postDTOs = Allposts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDTOs);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Post Id", postId));
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);

		List<PostDTO> collectposts = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());

		return collectposts;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "userId id", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDTO> collectposts = posts.stream().map((post) -> this.modelMapper.map(post, PostDTO.class))
				.collect(Collectors.toList());
		return collectposts;
	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		 List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		 List<PostDTO> listofsearchpost = posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
		
		 return listofsearchpost;
	}

}
