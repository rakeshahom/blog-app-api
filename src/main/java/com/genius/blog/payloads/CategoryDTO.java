package com.genius.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class CategoryDTO {

	private int categoryId;
	@NotBlank(message = "Title can  not be blank !!")
	@Size(min = 3, message = "minimum 3 charecters.!!")
	private String categoryTitle;
	@NotBlank
	@Size(min = 10, message = "min size of description 10 charecters !!")
	private String categoryDescription;
	public CategoryDTO() {
		super();
	}
	public CategoryDTO(int categoryId,
			@NotBlank(message = "Title can  not be blank !!") @Size(min = 3, message = "minimum 3 charecters.!!") String categoryTitle,
			@NotBlank @Size(min = 10, message = "min size of description 10 charecters !!") String categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryTitle = categoryTitle;
		this.categoryDescription = categoryDescription;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	@Override
	public String toString() {
		return "CategoryDTO [categoryId=" + categoryId + ", categoryTitle=" + categoryTitle + ", categoryDescription="
				+ categoryDescription + "]";
	}

}
