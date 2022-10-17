package com.ali;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ali.config.appConstants;
import com.ali.entity.Roles;
import com.ali.repository.rolesRepo;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner {
@Autowired	
private PasswordEncoder passwordEncode;
@Autowired
private rolesRepo rolesRepo;
	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

@Bean
public ModelMapper modelMapper() {
	
	return new ModelMapper();
	
}

@Override
public void run(String... args) throws Exception {
	// TODO Auto-generated method stub
	System.out.println(this.passwordEncode.encode("xyz"));
		try {
			Roles role = new Roles();
			role.setId(appConstants.Admin);
			role.setRole("ROLE_ADMIN");
			Roles role1 = new Roles();
			role1.setId(appConstants.Normal_User);
			role1.setRole("NORMAL_USER");
			List<Roles> roles = new ArrayList<>();
			roles.add(role1);
			roles.add(role);
			List<Roles> result = this.rolesRepo.saveAll(roles);
			result.forEach(e -> {
				System.out.println(e.getRole());
			});
		} catch (Exception e2) {
			// TODO: handle exception
		}
	     
}



}
