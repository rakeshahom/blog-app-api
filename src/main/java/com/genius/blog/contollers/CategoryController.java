package com.genius.blog.contollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genius.blog.payloads.ApiResponse;
import com.genius.blog.payloads.CategoryDTO;
import com.genius.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

//	create category
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> creatCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO createCategory = this.categoryService.createCategory(categoryDTO);
		return new ResponseEntity<CategoryDTO>(createCategory, HttpStatus.CREATED);
	}

//	update category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> creatCategory(@Valid @RequestBody CategoryDTO categoryDTO,
			@PathVariable Integer categoryId) {
		CategoryDTO updateCategory = this.categoryService.updateCategory(categoryDTO, categoryId);

		return new ResponseEntity<CategoryDTO>(updateCategory, HttpStatus.OK);
	}
//	Delete category

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully..!!", true),
				HttpStatus.OK);
	}

//	get category by id
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategoryDTObyid(@PathVariable Integer categoryId) {
		CategoryDTO categoryDTO = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
	}

//	get all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getCategoryDTObyid() {
		List<CategoryDTO> allCategory = this.categoryService.getAllCategory();
//		return ResponseEntity.ok(this.categoryService.getAllCategory());// we can also
		return ResponseEntity.ok(allCategory);
	}
}
