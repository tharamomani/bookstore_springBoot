package com.bookstore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bookstore.dto.AuthRequest;
import com.bookstore.dto.AuthResponse;
import com.bookstore.security.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("/Login")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "AuthenticationController", description = "Endpoints to authenticate the user and generate JWT token management.")
public class AuthenticationController {

	private final AuthenticationManager authenticationManager;
	private final JwtUtil jwtUtil;

	public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	@Operation(summary = "Authenticate the user and generate the Jwt token", description = "Authenticate the user using username and password, generate the Jwt token and return it to used in secured APIs", responses = {
			@ApiResponse(responseCode = "200", description = "Authenticated Successfully", content = @Content(schema = @Schema(implementation = AuthResponse.class))),
			@ApiResponse(responseCode = "401", description = "Invalid username or password", content = @Content), }

	)
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String token = jwtUtil.generateToken(userDetails.getUsername());
			return ResponseEntity.ok(new AuthResponse(token));
		} catch (BadCredentialsException ex) {
			// TODO: handle exception
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
		}
	}
}
