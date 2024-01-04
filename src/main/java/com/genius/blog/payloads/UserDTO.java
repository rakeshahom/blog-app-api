package com.genius.blog.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDTO {
	private int id;
	@NotEmpty
	@Size(min = 3, message = "Username must be min of 3 charecters !!")
	private String name;
	@Email(message = "Email is not valide !!")
	private String email;
	@NotNull
	@Size(min = 6, max = 16, message = "Password must be min of 6 chars and max of 16 chars !!")
	private String password;
	@NotEmpty(message = "please provide some spacification !!")
	private String about;
	private Set<RoleDTO> roles = new HashSet<>();
	public UserDTO() {
		super();
	}
	public UserDTO(int id, @NotEmpty @Size(min = 3, message = "Username must be min of 3 charecters !!") String name,
			@Email(message = "Email is not valide !!") String email,
			@NotNull @Size(min = 6, max = 16, message = "Password must be min of 6 chars and max of 16 chars !!") String password,
			@NotEmpty(message = "please provide some spacification !!") String about, Set<RoleDTO> roles) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public Set<RoleDTO> getRoles() {
		return roles;
	}
	public void setRoles(Set<RoleDTO> roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + ", roles=" + roles + "]";
	}

}
