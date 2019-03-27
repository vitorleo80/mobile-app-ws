package com.appsdeveloperblog.app.ws.io.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.appsdeveloperblog.app.ws.io.entity.UserEntity;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserRepositoryTest {

	@Autowired
	UserRepository userRepository;

	static boolean recordsCreated = false;

	@BeforeEach
	void setUp() throws Exception {

	}

	@Test
	final void testGetVerifiedUsers() {
		Pageable pageableRequest = PageRequest.of(0, 10);
		Page<UserEntity> page = userRepository.findAllUsersWithConfirmedEmailAddress(pageableRequest);
		assertNotNull(page);

		List<UserEntity> userEntities = page.getContent();
		System.out.println(userEntities.size());
		assertNotNull(userEntities);
		assertTrue(userEntities.size() == 1);
	}

}
