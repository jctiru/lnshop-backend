package com.jctiru.lnshop.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.jctiru.lnshop.api.io.entity.RoleEntity;
import com.jctiru.lnshop.api.io.repository.RoleRepository;

@Component
@ConditionalOnProperty(name = "app.db-role-init", havingValue = "true")
public class DbRoleInitializer implements CommandLineRunner {

	@Autowired
	RoleRepository roleRepository;

	Logger logger = LoggerFactory.getLogger(DbRoleInitializer.class);

	@Override
	public void run(String... args) throws Exception {
		logger.info("Initilizing DB Roles...");

		if (!roleRepository.existsRoleByName("ADMIN")) {
			RoleEntity adminRole = new RoleEntity();
			adminRole.setName("ADMIN");
			roleRepository.save(adminRole);
			logger.info("ADMIN role not found in DB Roles and has been created...");
		} else {
			logger.info("ADMIN role found in DB Roles...");
		}

		if (!roleRepository.existsRoleByName("USER")) {
			RoleEntity userRole = new RoleEntity();
			userRole.setName("USER");
			roleRepository.save(userRole);
			logger.info("USER role not found in DB Roles and has been created...");
		} else {
			logger.info("USER role found in DB Roles...");
		}

		logger.info("Finished initializing DB Roles...");
	}

}
