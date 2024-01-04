package com.genius.blog.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.genius.blog.exceptions.Apiexception;
import com.genius.blog.payloads.JwtAuthRequest;
import com.genius.blog.payloads.JwtAuthResponse;
import com.genius.blog.payloads.UserDTO;
import com.genius.blog.security.JwtTokenHelper;
import com.genius.blog.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;

	@PostMapping("login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String genrateToken = this.jwtTokenHelper.genrateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(genrateToken);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		try {
			this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		} catch (BadCredentialsException e) {
			throw new Apiexception("Invalid Password");
		}

	}

//	Register new user

	@PostMapping("register")
	public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
		UserDTO registreNewUser = this.userService.registreNewUser(userDTO);
		return new ResponseEntity<UserDTO>(registreNewUser, HttpStatus.CREATED);
	}

}
