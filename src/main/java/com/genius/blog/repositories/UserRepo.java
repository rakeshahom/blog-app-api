package com.genius.blog.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.genius.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

//	@Query(nativeQuery = true,value="SELECT * from user u where u.email=:email")
	
//	@Query("SELECT u FROM User u WHERE u.email =:email")
	
	Optional<User>findByEmail(String email);
}
