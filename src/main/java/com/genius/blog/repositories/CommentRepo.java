package com.genius.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genius.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
