package com.jctiru.lnshop.api.io.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jctiru.lnshop.api.io.entity.RoleEntity;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

	RoleEntity findRoleById(String id);

	RoleEntity findRoleByName(String name);

	boolean existsRoleByName(String name);

}
