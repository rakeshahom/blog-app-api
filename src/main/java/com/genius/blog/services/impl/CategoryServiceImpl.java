package com.genius.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.genius.blog.entities.Category;
import com.genius.blog.exceptions.ResourceNotFoundException;
import com.genius.blog.payloads.CategoryDTO;
import com.genius.blog.repositories.CategoryRepo;
import com.genius.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepo categoryRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO createCategory(CategoryDTO categoryDTO) {
		Category mapcategory = this.modelMapper.map(categoryDTO, Category.class);
		Category savecategory = this.categoryRepo.save(mapcategory);
		return this.modelMapper.map(savecategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO categoryDTO, Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		Category updateCatgory = this.categoryRepo.save(category);
		return this.modelMapper.map(updateCatgory, CategoryDTO.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));
		this.categoryRepo.delete(category);

	}

	@Override
	public CategoryDTO getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category Id", categoryId));

		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDTO> collectcategories = categories.stream()
				.map((cate) -> this.modelMapper.map(cate, CategoryDTO.class)).collect(Collectors.toList());

		return collectcategories;
	}

}
