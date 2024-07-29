package com.cloudy.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

	@Value("${spring.client.url}")
	private String clientUrl;

	@Value("${spring.client.test-url}")
	private String testUrl;

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		log.info("client URL is... {}", clientUrl);
		corsRegistry.addMapping("/**")
			.allowCredentials(true)
			.allowedHeaders("*")
			.exposedHeaders("Set-Cookie")
			.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
			.allowedOrigins("https://cloudy.cloudy.n-e.kr", testUrl);
	}
}
