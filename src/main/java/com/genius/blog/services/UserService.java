package com.genius.blog.services;

import java.util.List;

import com.genius.blog.payloads.UserDTO;

public interface UserService {

	UserDTO registreNewUser(UserDTO userDTO);
	
	UserDTO createUser(UserDTO user);

	UserDTO updateUser(UserDTO user, Integer userId);

	UserDTO getUserById(Integer userId);

	List<UserDTO> getAllUsers();

	void deleteUSers(Integer userId);
}
