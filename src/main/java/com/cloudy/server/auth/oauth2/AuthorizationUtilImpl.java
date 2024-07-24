package com.cloudy.server.auth.oauth2;

import com.cloudy.server.auth.oauth2.dto.CustomOAuth2User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationUtilImpl implements AuthorizationUtil {
	@Override
	public long getLoginUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomOAuth2User userDetails = (CustomOAuth2User) authentication.getPrincipal();

		return userDetails.getId();
	}
}
