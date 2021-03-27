package com.stackroute.bookapp.RecommendationService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.bookapp.RecommendationService.jwtFilter.JwtFilter;

@SpringBootApplication
public class RecommendationServiceApplication {

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.addUrlPatterns("/api/v1/recommendation");
		filterRegistrationBean.setFilter(new JwtFilter());
		return filterRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(RecommendationServiceApplication.class, args);
		System.out.println("hello from http://localhost:8083");
	}

}
