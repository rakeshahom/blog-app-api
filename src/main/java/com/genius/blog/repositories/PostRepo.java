package com.genius.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genius.blog.entities.Category;
import com.genius.blog.entities.Post;
import com.genius.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);

	List<Post> findByTitleContaining(String title);
}
