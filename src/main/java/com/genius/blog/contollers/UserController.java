package com.genius.blog.contollers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genius.blog.payloads.ApiResponse;
import com.genius.blog.payloads.UserDTO;
import com.genius.blog.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO createUserDTO = this.userService.createUser(userDTO);
		return new ResponseEntity<UserDTO>(createUserDTO, HttpStatus.OK);
	}

	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer userId) {
		UserDTO updateUser = this.userService.updateUser(userDTO, userId);
		return ResponseEntity.ok(updateUser);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {
		this.userService.deleteUSers(userId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("user delete successfully", true), HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllusers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getsingUser(@PathVariable Integer userId) {

		return ResponseEntity.ok(this.userService.getUserById(userId));
	}

}
