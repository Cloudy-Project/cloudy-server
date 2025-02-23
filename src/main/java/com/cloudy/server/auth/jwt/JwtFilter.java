package com.cloudy.server.auth.jwt;

import com.cloudy.server.auth.oauth2.dto.CustomOAuth2User;
import com.cloudy.server.member.dto.MemberDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;

	@Value("${spring.jwt.expire-length}")
	private long expireLong;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String requestUri = request.getRequestURI();

		if (noAuthentication(request)) {
			log.info("pass!");
			filterChain.doFilter(request, response);
			return;
		}

		if (requestUri.matches("^\\/login(?:\\/.*)?$")) {
			filterChain.doFilter(request, response);
			return;
		}
		if (requestUri.matches("^\\/oauth2(?:\\/.*)?$")) {
			filterChain.doFilter(request, response);
			return;
		}

		//cookie들을 불러온 뒤 token Key에 담긴 쿠키를 찾음
		String authorization = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				log.info("cookie name is ! {}", cookie.getName());
				if (cookie.getName().equals("token")) {
					log.info("cookie value is ! {}", cookie.getValue());
					authorization = cookie.getValue();
				}
			}
		}

		//Authorization 헤더 검증
		if (authorization == null) {
			log.info("token is null!");
			filterChain.doFilter(request, response);

			//조건이 해당되면 메소드 종료 (필수)
			return;
		}

		//토큰
		String token = authorization;

		//토큰 소멸 시간 검증
		try {
			jwtUtil.isExpired(token);
			log.info("passed!!");
		} catch (ExpiredJwtException e) {
			log.info("token is expired!");
			log.info("jwt error: {}", e.getMessage());

			Cookie newToken = new Cookie("token", "");
			newToken.setMaxAge(0);
			newToken.setPath("/");

			response.addCookie(newToken);

			filterChain.doFilter(request, response);

			//조건이 해당되면 메소드 종료 (필수)
			return;
		} catch (MalformedJwtException e){
			log.info("token is invalid!");
			log.info("jwt error: {}", e.getMessage());

			Cookie newToken = new Cookie("token", "");
			newToken.setMaxAge(0);
			newToken.setPath("/");

			response.addCookie(newToken);

			filterChain.doFilter(request, response);

			//조건이 해당되면 메소드 종료 (필수)
			return;
		}

		//토큰에서 userId 획득
		long userId = jwtUtil.getUserId(token);

		//userDto를 생성하여 값 set
		MemberDto userDto = MemberDto.builder()
			.id(userId)
			.build();

		//UserDetails에 회원 정보 객체 담기
		CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDto);

		//스프링 시큐리티 인증 토큰 생성
		Authentication authToken = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());

		//세션에 사용자 등록
		SecurityContextHolder.getContext().setAuthentication(authToken);

		filterChain.doFilter(request, response);
	}

	private boolean noAuthentication(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String method = req.getMethod();
		return uri.startsWith("/swagger-ui")
				|| uri.startsWith("/api/letter") && !method.equals("DELETE")
				|| uri.startsWith("/api/member") && method.equals("GET")
				|| uri.startsWith("/error");
	}
}
