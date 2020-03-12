package com.jctiru.lnshop.api.io.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jctiru.lnshop.api.io.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

	UserEntity findUserByEmail(String email);

	UserEntity findByUserId(String userId);

	UserEntity findByEmailVerificationToken(String token);

}
