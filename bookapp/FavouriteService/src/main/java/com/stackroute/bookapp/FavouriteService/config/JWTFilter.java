package com.stackroute.bookapp.FavouriteService.config;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;



public class JWTFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hres = (HttpServletResponse) response;

		
		
		String authHeader = hreq.getHeader("Authorization");
		
		if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new ServletException("Not a valid authentication header");
        }
		
		if ("OPTIONS".equals(hreq.getMethod())) {
			hres.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
		} else {

			try {
				String token = authHeader.split(" ")[1];
				Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				chain.doFilter(request, response);
			} catch (SignatureException ex) {
	            throw new ServletException("Invalid Token");
	        } catch (MalformedJwtException ex) {
	            throw new ServletException("JWT is malformed");
	        }

	        
		}

	}
		
	}

	

