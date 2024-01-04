package com.genius.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.genius.blog.config.AppConstants;
import com.genius.blog.entities.Role;
import com.genius.blog.entities.User;
import com.genius.blog.exceptions.ResourceNotFoundException;
import com.genius.blog.payloads.UserDTO;
import com.genius.blog.repositories.RoleRepo;
import com.genius.blog.repositories.UserRepo;
import com.genius.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDTO registreNewUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User saveuser = this.userRepo.save(user);
		UserDTO mapuser = this.modelMapper.map(saveuser, UserDTO.class);
		return mapuser;
	}

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = this.dtoToUser(userDTO);
		User saveeduser = this.userRepo.save(user);
		return this.userToDto(saveeduser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setAbout(userDTO.getAbout());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		User updateuser = this.userRepo.save(user);
		UserDTO userDTO2 = this.userToDto(updateuser);
		return userDTO2;
	}

	@Override
	public UserDTO getUserById(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDTO> userDTOs = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDTOs;
	}

	@Override
	public void deleteUSers(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}

	// This function relete to the bean create in main function(modelmapper)
	private User dtoToUser(UserDTO userDTO) {
		User user = this.modelMapper.map(userDTO, User.class);// as wel as commented down 5 line

//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setPassword(userDTO.getPassword());
//		user.setAbout(userDTO.getAbout());
		return user;
	}

//This function relete to the bean create in main function(modelmapper)

	private UserDTO userToDto(User user) {
		UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
		return userDTO;

	}

}
