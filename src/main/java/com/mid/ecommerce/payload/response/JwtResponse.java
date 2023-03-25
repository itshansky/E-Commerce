package com.mid.ecommerce.payload.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
    private String username;
    private String email;
	private String role;
    private String token;
    private String refreshToken;
    private String type = "Bearer";
    
    public JwtResponse(String username, String email, String role, String accessToken, String refreshToken) {
        this.username = username;
        this.email = email;
		this.role = role;
        this.token = accessToken;
        this.refreshToken = refreshToken;
    }
}
