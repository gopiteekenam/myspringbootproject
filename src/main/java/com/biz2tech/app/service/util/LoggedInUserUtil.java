package com.biz2tech.app.service.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import com.biz2tech.app.security.jwt.TokenProvider;

/**
 * <p>
 * Common utility to get Logged in user
 * 
 * @author Gopi_Teekenam
 *
 */

public class LoggedInUserUtil {

	/**
	 * <p>
	 * Static utility method to get logged in user
	 * </p>
	 * 
	 * @param tokenProvider
	 * @param authorization
	 * @return String
	 */

	public static String getLoggedInUser(TokenProvider tokenProvider, String authorization) {
		String userName = SecurityContextHolder.getContext().getAuthentication().getName();
		if (StringUtils.isEmpty(userName)) {
			userName = tokenProvider.getAuthentication(authorization).getName();
		}
		return userName;
	}

}
