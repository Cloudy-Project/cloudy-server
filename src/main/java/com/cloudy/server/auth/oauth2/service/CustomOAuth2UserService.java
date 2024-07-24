package com.cloudy.server.auth.oauth2.service;

import com.cloudy.server.auth.oauth2.dto.CustomOAuth2User;
import com.cloudy.server.auth.oauth2.dto.GoogleResponse;
import com.cloudy.server.auth.oauth2.dto.OAuth2Response;
import com.cloudy.server.member.domain.Member;
import com.cloudy.server.member.dto.MemberDto;
import com.cloudy.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final MemberRepository memberRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

		OAuth2User oAuth2User = super.loadUser(userRequest);

		log.info("hello this is.. {}", oAuth2User);

		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		OAuth2Response oAuth2Response = null;
		if (registrationId.equals("google")) {
			log.info("google login processing..");
			oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
		} else {
			return null;
		}

		String username = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();
		Member existData = memberRepository.findByUsername(username).orElse(null);

		if (existData == null) {
			// 새로 가입
			Member user = Member.builder()
				.email(oAuth2Response.getEmail())
				.username(username)
				.name(oAuth2Response.getName())
				.build();

			user = memberRepository.save(user);

			MemberDto userDto = new MemberDto(user.getId(), oAuth2Response.getName(), username, oAuth2Response.getEmail());

			return new CustomOAuth2User(userDto);
		} else {
			MemberDto userDto = new MemberDto(existData.getId(), existData.getName(), existData.getUsername(), existData.getEmail());

			return new CustomOAuth2User(userDto);
		}
	}
}
