package com.appsdeveloperblog.app.ws.io.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

/**
 * 
 * @author Vitor Correa
 * @date 19 Mar 2019
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByEmail(String email);

	UserEntity findByUserId(String userId);

	UserEntity findUserByEmailVerificationToken(String token);

}
