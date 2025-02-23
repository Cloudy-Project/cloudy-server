package com.cloudy.server.auth.oauth2.dto;

import com.cloudy.server.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@RequiredArgsConstructor
public class CustomOAuth2User implements OAuth2User {

	private final MemberDto userDto;

	@Override
	public Map<String, Object> getAttributes() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> collection = new ArrayList<>();
		return collection;
	}

	@Override
	public String getName() {
		return userDto.getName();
	}

	public String getUsername() {
		return userDto.getUsername();
	}

	public String getEmail() {
		return userDto.getEmail();
	}

	public Long getId() {
		return userDto.getId();
	}
}
