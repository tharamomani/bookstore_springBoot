package com.bookstore.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	private final Key key;
	private final long jwtExpirationMs;

	/*
	 * Constructor that reads Expiration time from application.properties and get key from env variables
	 */
	public JwtUtil( @Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration-ms}") long jwtExpirationMs ) {
		
		//String secretKey = System.getenv("JWT_SECRET");
		if(secretKey == null) {
			throw new IllegalStateException("JWT_SECRET is not set please check your env variables");
		}
		this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
		this.jwtExpirationMs = jwtExpirationMs;
	}
	
	/*
	 * Generate JWT token with username and stored key in env variables using ES256 Algorithm
	 */
	public String generateToken(String username) {
	    return Jwts.builder()
	            .setSubject(username)
	            .setIssuedAt(new Date())
	            .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
	            .signWith(key, SignatureAlgorithm.HS256) // ✅ use HS256 for HMAC key
	            .compact();
	}

	
	/*
	 * Extract userName from JWT token
	 */
	public String extractUserName(String token) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
		return parser.parseClaimsJws(token).getBody().getSubject();
	}
	
	/*
	 * Extract role from JWT token
	 */
	public String extractRole(String token) {
		JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
		return (String) parser.parseClaimsJws(token).getBody().get("role");
	}
	
	private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
	
	private Date extractExpiration(String token) {
		// TODO Auto-generated method stub
		JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();
		return parser.parseClaimsJws(token).getBody().getExpiration();
	}

	/*
	 * Validate JWT Token
	 */
	public boolean validateJwtToken(String token, UserDetails userDetails) {
		return extractUserName(token).equals(userDetails.getUsername()) && !isTokenExpired(token);
	}
}
