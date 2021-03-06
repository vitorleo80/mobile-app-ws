package com.appsdeveloperblog.app.ws.io.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
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

	@Query(value = "select * from Users u where u.EMAIL_VERIFICATION_STATUS = 'true'", countQuery = "select count(*) from Users u where u.EMAIL_VERIFICATION_STATUS = 'true'", nativeQuery = true)
	Page<UserEntity> findAllUsersWithConfirmedEmailAddress(Pageable pageableRequest);
}
