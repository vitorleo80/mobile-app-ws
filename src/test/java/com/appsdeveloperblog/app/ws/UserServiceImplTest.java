/**
 * 
 */
package com.appsdeveloperblog.app.ws;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdeveloperblog.app.ws.exceptions.UserServiceException;
import com.appsdeveloperblog.app.ws.io.entity.AddressEntity;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.io.repository.UserRepository;
import com.appsdeveloperblog.app.ws.service.impl.UserServiceImpl;
import com.appsdeveloperblog.app.ws.shared.AmazonSES;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.AddressDto;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

/**
 * 
 * @author Vitor Correa
 * @date 27 Mar 2019
 */
class UserServiceImplTest {

	// class under test. It takes the class and the autowired classes
	@InjectMocks
	UserServiceImpl userService;

	// getUser method uses an external class, userRepository, that must mock the
	// class to test it.
	@Mock
	UserRepository userRepository;

	@Mock
	Utils utils;

	@Mock
	AmazonSES amazonSES;

	@Mock
	BCryptPasswordEncoder bCryptPasswordEncoder;

	String userId = "hhty57ehfy";
	String encryptedPassword = "74hghd8474jf";
	UserEntity userEntity;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		userEntity = new UserEntity();
		userEntity.setId(1L);
		userEntity.setFirstName("Vitor");
		userEntity.setLastName("Correa");
		userEntity.setUserId(userId);
		userEntity.setEncryptedPassword(encryptedPassword);
		userEntity.setEmail("test@test.com");
		userEntity.setEmailVerificationToken("7htnfhr758");
		userEntity.setAddresses(getAddressesEntity());
	}

	@Test
	final void testGetUser() {

		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);

		UserDto userDto = userService.getUser("test@test.com");

		assertNotNull(userDto);
		assertEquals("Vitor", userDto.getFirstName());

	}

	@Test
	final void testGetUser_UsernameNotFoundException() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

		assertThrows(UsernameNotFoundException.class,

				() -> {
					userService.getUser("test@test.com");
				}

		);
	}

	// before add test, check what classes the method depends on. In this case,
	// Utils, BcryptPasswordEncoder and UserRepository
	@Test
	final void testCreateUser() {
		when(userRepository.findByEmail(anyString())).thenReturn(null);

//		address.setAddressId(utils.generateUserId());
		when(utils.generateUserId()).thenReturn("sdokkaopskdad");
//		String publicUserId = utils.generateUserId();
		when(utils.generateUserId()).thenReturn(userId);
//		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		when(bCryptPasswordEncoder.encode(anyString())).thenReturn(encryptedPassword);

//		UserEntity storedUserDetails = userRepository.save(userEntity);
		when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

		Mockito.doNothing().when(amazonSES).verifyEmail(any(UserDto.class));

		UserDto userDto = new UserDto();
		userDto.setAddresses(getAddressesDto());
		userDto.setFirstName("Vitor");
		userDto.setLastName("Correa");
		userDto.setPassword("12345678");
		userDto.setEmail("test@test.com");
		UserDto storedUserDetails = userService.createUser(userDto);
		assertNotNull(storedUserDetails);
		assertEquals(userEntity.getFirstName(), storedUserDetails.getFirstName());
		assertEquals(userEntity.getLastName(), storedUserDetails.getLastName());
		assertNotNull(storedUserDetails.getUserId());
		assertEquals(storedUserDetails.getAddresses().size(), userEntity.getAddresses().size());
		// check if method has been called x time.
//		address.setAddressId(utils.generateUserId());
		verify(utils, times(3)).generateUserId();
//		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		verify(bCryptPasswordEncoder, times(1)).encode("12345678");

		// repo
		verify(userRepository, times(1)).save(any(UserEntity.class));

	}

	private List<AddressDto> getAddressesDto() {
		AddressDto addressDto = new AddressDto();
		addressDto.setType("shipping");
		addressDto.setCity("Vancouver");
		addressDto.setCountry("Canada");
		addressDto.setPostalCode("ABC123");
		addressDto.setStreetName("123 Street name");

		AddressDto billingAddressDto = new AddressDto();
		billingAddressDto.setType("billling");
		billingAddressDto.setCity("Vancouver");
		billingAddressDto.setCountry("Canada");
		billingAddressDto.setPostalCode("ABC123");
		billingAddressDto.setStreetName("123 Street name");

		List<AddressDto> addresses = new ArrayList<>();
		addresses.add(addressDto);
		addresses.add(billingAddressDto);

		return addresses;

	}

	private List<AddressEntity> getAddressesEntity() {
		List<AddressDto> addresses = getAddressesDto();

		Type listType = new TypeToken<List<AddressEntity>>() {
		}.getType();

		return new ModelMapper().map(addresses, listType);
	}

	@Test
	final void testCreateUser_CreateUserServiceException() {
		when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
		UserDto userDto = new UserDto();
		userDto.setAddresses(getAddressesDto());
		userDto.setFirstName("Vitor");
		userDto.setLastName("Correa");
		userDto.setPassword("12345678");
		userDto.setEmail("test@test.com");

		assertThrows(UserServiceException.class,

				() -> {
					userService.createUser(userDto);
				}

		);
	}

}
