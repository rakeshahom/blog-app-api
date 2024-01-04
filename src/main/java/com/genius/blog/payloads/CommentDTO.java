package com.genius.blog.payloads;

public class CommentDTO {
	private int id;
	private String content;
	public CommentDTO() {
		super();
	}
	public CommentDTO(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", content=" + content + "]";
	}

}