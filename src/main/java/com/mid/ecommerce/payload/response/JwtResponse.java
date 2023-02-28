package com.mid.ecommerce.payload.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {
    private String username;
    private String email;
    private String token;
    private String refreshToken;
    private String type = "Bearer";
    
    public JwtResponse(String username, String email, String accessToken, String refreshToken) {
        this.username = username;
        this.email = email;
        this.token = accessToken;
        this.refreshToken = refreshToken;
    }
}
