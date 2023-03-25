package com.mid.ecommerce.payload.request;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
