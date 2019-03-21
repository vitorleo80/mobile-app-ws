package com.appsdeveloperblog.app.ws.userservice;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

/**
 * 
 * @author Vitor Correa
 * @date 19 Mar 2019
 */
public interface UserService extends UserDetailsService {

	UserDto createUser(UserDto user);

	UserDto getUser(String email);

	UserDto getUserByUserId(String userId);

	UserDto updateUser(String id, UserDto userDto);

	void deleteUser(String userId);

}
