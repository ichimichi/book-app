package com.stackroute.bookapp.UserService.jwtFilter;

import org.springframework.web.filter.GenericFilterBean;
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

import java.io.IOException;

/* This class implements the custom filter by extending org.springframework.web.filter.GenericFilterBean.  
 * Override the doFilter method with ServletRequest, ServletResponse and FilterChain.
 * This is used to authorize the API access for the application.
 */
public class JwtFilter extends GenericFilterBean {

	/*
	 * Override the doFilter method of GenericFilterBean. Retrieve the
	 * "authorization" header from the HttpServletRequest object. Retrieve the
	 * "Bearer" token from "authorization" header. If authorization header is
	 * invalid, throw Exception with message. Parse the JWT token and get claims
	 * from the token using the secret key Set the request attribute with the
	 * retrieved claims Call FilterChain object's doFilter() method
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest hreq = (HttpServletRequest) request;
		HttpServletResponse hres = (HttpServletResponse) response;
		String authHeader = hreq.getHeader("Authorization");
		if ("OPTIONS".equals(hreq.getMethod())) {
			hres.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
		} else {
			try {
				if (authHeader == null || !authHeader.startsWith("Bearer ")) {
					hres.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	            throw new ServletException("Not a valid authentication header");
					return;
				}

				String token = authHeader.split(" ")[1];
				Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
				chain.doFilter(request, response);
			} catch (SignatureException ex) {
				hres.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	            throw new ServletException("Invalid Token");
			} catch (MalformedJwtException ex) {
				hres.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//	            throw new ServletException("JWT is malformed");
			}
		}
	}
}