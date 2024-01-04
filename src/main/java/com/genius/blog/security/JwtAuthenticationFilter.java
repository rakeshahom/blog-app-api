package com.genius.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestTokenString = request.getHeader("Authorizaton");
		System.out.println(requestTokenString);
		String usernameString = null;
		String tokenString = null;
		if (requestTokenString != null && requestTokenString.startsWith("Bearer")) {
			tokenString = requestTokenString.substring(7);
			try {
				usernameString = this.jwtTokenHelper.getUsernameFromToken(tokenString);

			} catch (IllegalArgumentException e) {
				System.out.println("Unable get jwt token");
			} catch (ExpiredJwtException e) {
				System.out.println("Jwt token has expired");
			} catch (MalformedJwtException e) {
				System.out.println("invalid Jwt");
			}
		} else {
			System.out.println("Jwt token not start with Bearer !!");
		}
		if (usernameString != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(usernameString);
			if (this.jwtTokenHelper.validateToken(tokenString, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			} else {
				System.out.println("Invalid Jwt token.!!");
			}
		} else {
			System.out.println("username is null or context is not null !!");
		}
		filterChain.doFilter(request, response);
	}

}
