package com.mid.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mid.ecommerce.entity.Pengguna;
import com.mid.ecommerce.model.RefreshTokenRequest;
import com.mid.ecommerce.payload.request.LoginRequest;
import com.mid.ecommerce.payload.request.SignupRequest;
import com.mid.ecommerce.payload.response.JwtResponse;
import com.mid.ecommerce.payload.response.MessageResponse;
import com.mid.ecommerce.security.jwt.JwtUtils;
import com.mid.ecommerce.security.service.UserDetailsImpl;
import com.mid.ecommerce.security.service.UserDetailsServiceImpl;
import com.mid.ecommerce.service.PenggunaService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    PenggunaService penggunaService;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;
    
    @Autowired
    JwtUtils jwtUtils;
    
    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest request) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext()
                .setAuthentication(authentication);
        String token = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        return ResponseEntity.ok()
                .body(new JwtResponse(principal.getUsername(), principal.getEmail(), token, refreshToken));
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        Pengguna pengguna = new Pengguna();
        pengguna.setId(request.getUsername());
        pengguna.setEmail(request.getEmail());
        pengguna.setPassword(passwordEncoder.encode(request.getPassword()));
        pengguna.setNama(request.getNama());
        pengguna.setRoles("user");
        
        penggunaService.create(pengguna);
        return ResponseEntity.ok(new MessageResponse("Pengguna berhasil ditambahkan."));
    }
    
    @PostMapping("/refreshToken")
    public ResponseEntity<JwtResponse> refreshToken(@RequestBody RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        boolean valid = jwtUtils.validateJwtToken(token);
        
        if (!valid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        String username = jwtUtils.getUserNameFromJwtToken(token);
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl) userDetailsServiceImpl.loadUserByUsername(username);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetailsImpl, null,
                userDetailsImpl.getAuthorities());
        String newToken = jwtUtils.generateJwtToken(authentication);
        String refreshToken = jwtUtils.generateRefresJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(username, userDetailsImpl.getEmail(), newToken, refreshToken));
    }
}
