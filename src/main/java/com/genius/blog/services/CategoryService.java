package com.genius.blog.services;

import java.util.List;

import com.genius.blog.payloads.CategoryDTO;


public interface CategoryService {
//Created Category

	CategoryDTO createCategory(CategoryDTO categoryDTO);

//Update category
	CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId);

//DeleteMapping category
	void deleteCategory(Integer categoryId);

//getCategor by Id 
	CategoryDTO getCategory(Integer categoryId);

//get all category
	List<CategoryDTO> getAllCategory();

}
