package com.genius.blog.payloads;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class PostResponse {

	private List<PostDTO> content;
	private int pageNumber;
	private int pageSize;
	private long totalElement;
	private int totalPages;
	private boolean lastPage;
	public PostResponse() {
		super();
	}
	public PostResponse(List<PostDTO> content, int pageNumber, int pageSize, long totalElement, int totalPages,
			boolean lastPage) {
		super();
		this.content = content;
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
		this.totalElement = totalElement;
		this.totalPages = totalPages;
		this.lastPage = lastPage;
	}
	public List<PostDTO> getContent() {
		return content;
	}
	public void setContent(List<PostDTO> content) {
		this.content = content;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public long getTotalElement() {
		return totalElement;
	}
	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public boolean isLastPage() {
		return lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	@Override
	public String toString() {
		return "PostResponse [content=" + content + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize
				+ ", totalElement=" + totalElement + ", totalPages=" + totalPages + ", lastPage=" + lastPage + "]";
	}
	
	
}
