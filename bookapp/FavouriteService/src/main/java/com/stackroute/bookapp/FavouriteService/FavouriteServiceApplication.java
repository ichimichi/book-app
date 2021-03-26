package com.stackroute.bookapp.FavouriteService;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.bookapp.FavouriteService.config.JWTFilter;


@SpringBootApplication
public class FavouriteServiceApplication {
	
	@Bean
	public FilterRegistrationBean<JWTFilter> jwtFilter() {
		FilterRegistrationBean<JWTFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.addUrlPatterns("/api/v1/fav");
		filterRegistrationBean.setFilter(new JWTFilter());
		return filterRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(FavouriteServiceApplication.class, args);
		System.out.println("hello from localhost://8082");
	}
	


}
