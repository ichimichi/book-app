package com.stackroute.bookapp.UserService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.bookapp.UserService.jwtFilter.JwtFilter;

@SpringBootApplication
public class UserServiceApplication 
{
	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {
		FilterRegistrationBean<JwtFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.addUrlPatterns("/api/v1/user");
		filterRegistrationBean.setFilter(new JwtFilter());
		return filterRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
		System.out.println("hello from http://localhost:8081");
	}
}
