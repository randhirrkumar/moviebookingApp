package com.usermanagement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.usermanagement.config.AppConstants;
import com.usermanagement.model.Role;
import com.usermanagement.repository.RoleRepository;

@SpringBootApplication
public class UserManagementApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			List<Role> alreadyAddedRoles = roleRepository.findAll();
			if(alreadyAddedRoles.isEmpty()) {
				Role role = new Role();
				role.setId(AppConstants.ADMIN_USER);
				role.setName("ADMIN_USER");
				
				Role role1 = new Role();
				role1.setId(AppConstants.NORMAL_USER);
				role1.setName("NORMAL_USER");
				
				List<Role> roles = List.of(role, role1);
				
				List<Role> result = roleRepository.saveAll(roles);
				
				result.forEach(r->{
					System.out.println(r.getName());
				});
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
