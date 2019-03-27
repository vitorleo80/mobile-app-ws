package com.appsdeveloperblog.app.ws.service;

import java.util.List;

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

	List<UserDto> getUsers(int page, int limit);

	boolean verifyEmailToken(String token);

	boolean requestPasswordReset(String email);

	boolean resetPassword(String token, String password);

}
