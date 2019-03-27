package com.appsdeveloperblog.app.ws.io.repository;

import org.springframework.data.repository.CrudRepository;

import com.appsdeveloperblog.app.ws.io.entity.PasswordResetTokenEntity;

/**
 * 
 * @author Vitor Correa
 * @date 27 Mar 2019
 */
public interface PasswordResetTokenRepository extends CrudRepository<PasswordResetTokenEntity, Long> {
	PasswordResetTokenEntity findByToken(String token);
}
