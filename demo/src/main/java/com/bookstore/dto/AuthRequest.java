package com.bookstore.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import lombok.Setter;

@AllArgsConstructor
@Data
@Getter
@Setter
public class AuthRequest {

	public AuthRequest() {}
	
	@Schema(description = "Username of the user", example = "admin")
	private String username;
	
	@Schema(description = "Password of the user", example = "TestPass@123")
	private String password;
}

