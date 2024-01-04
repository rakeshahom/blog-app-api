package com.genius.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genius.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
