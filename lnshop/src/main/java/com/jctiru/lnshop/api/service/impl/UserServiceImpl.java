package com.jctiru.lnshop.api.service.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jctiru.lnshop.api.exception.RecordAlreadyExistsException;
import com.jctiru.lnshop.api.io.entity.RoleEntity;
import com.jctiru.lnshop.api.io.entity.UserEntity;
import com.jctiru.lnshop.api.io.repository.RoleRepository;
import com.jctiru.lnshop.api.io.repository.UserRepository;
import com.jctiru.lnshop.api.service.UserService;
import com.jctiru.lnshop.api.shared.Utils;
import com.jctiru.lnshop.api.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	Utils utils;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findUserByEmail(email);
		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}

		List<GrantedAuthority> authorities = userEntity.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), authorities);
	}

	@Transactional
	@Override
	public UserDto createUser(UserDto user) {
		if (userRepository.findUserByEmail(user.getEmail()) != null) {
			throw new RecordAlreadyExistsException("Record already exists");
		}

		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		String publicUserId = utils.generatePublicEntityId(Utils.EntityType.USER);
		userEntity.setUserId(publicUserId);

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		userEntity.setRoles(new HashSet<RoleEntity>(Arrays.asList(roleRepository.findRoleByName("USER"))));
		userEntity.setEmailVerificationToken(Utils.generateEmailVerificationToken(publicUserId));

		UserEntity storedUserDetails = userRepository.save(userEntity);

		return modelMapper.map(storedUserDetails, UserDto.class);
	}

	@Transactional
	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findUserByEmail(email);

		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}

		return modelMapper.map(userEntity, UserDto.class);
	}

	@Override
	public boolean verifyEmailToken(String token) {
		boolean returnValue = false;
		UserEntity userEntity = userRepository.findByEmailVerificationToken(token);

		if (userEntity != null) {
			boolean hasTokenExpired = Utils.hasTokenExpired(token);

			if (!hasTokenExpired) {
				userEntity.setEmailVerificationToken(null);
				userEntity.setEmailVerificationStatus(true);
				userRepository.save(userEntity);
				returnValue = true;
			}
		}

		return returnValue;
	}

	@Override
	public UserDto getUserByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(String userId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

}
