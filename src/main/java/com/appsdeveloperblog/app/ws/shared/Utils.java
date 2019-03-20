package com.appsdeveloperblog.app.ws.shared;

import java.util.UUID;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Vitor Correa
 * @date 20 Mar 2019
 */
@Service
public class Utils {
	public String generateUserId() {
		return UUID.randomUUID().toString();
	}
}
