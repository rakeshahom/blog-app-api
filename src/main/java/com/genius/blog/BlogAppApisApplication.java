package com.genius.blog;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.genius.blog.config.AppConstants;
import com.genius.blog.entities.Role;
import com.genius.blog.repositories.RoleRepo;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("password new++++"+this.passwordEncoder.encode("123456"));
		try {
			List<Role> strList = new ArrayList<Role>();
			Role role = new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ADMIN_USER");

			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("NORMAL_USER");
			
			strList.add(role);
			strList.add(role1);
			List<Role> saveAll = roleRepo.saveAll(strList);
			saveAll.forEach((r)->{System.out.println(r.getName());});
		} catch (Exception e) {
			
			
			e.printStackTrace();
		}

	}

}
