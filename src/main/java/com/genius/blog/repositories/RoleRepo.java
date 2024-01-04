package com.genius.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genius.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role,Integer> {

}
