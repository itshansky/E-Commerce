package com.mid.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mid.ecommerce.entity.Pesanan;
import com.mid.ecommerce.payload.request.PesananRequest;
import com.mid.ecommerce.payload.response.PesananResponse;
import com.mid.ecommerce.security.service.UserDetailsImpl;
import com.mid.ecommerce.service.PesananService;

@RestController
@RequestMapping("/api/pesanan")
@PreAuthorize("isAuthenticated()")
public class PesananController {
    @Autowired
    private PesananService pesananService;
    
    @PostMapping("/")
    @PreAuthorize("hasAuthority('user')")
    public PesananResponse create(
            @AuthenticationPrincipal UserDetailsImpl user,
            @RequestBody PesananRequest request) {
        return pesananService.create(user.getUsername(), request);
    }
    
    @PatchMapping("/{pesananId}/cancel")
    @PreAuthorize("hasAuthority('user')")
    public Pesanan cancelPesananUser(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("pesananId") String pesananId) {
        return pesananService.cancelPesanan(pesananId, user.getUsername());
    }
    
    @PatchMapping("/{pesananId}/terima")
    @PreAuthorize("hasAuthority('user')")
    public Pesanan terima(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("pesananId") String pesananId) {
        return pesananService.terimaPesanan(pesananId, user.getUsername());
    }
    
    @PatchMapping("/{pesananId}/konfirmasi")
    @PreAuthorize("hasAuthority('admin')")
    public Pesanan konfirmasi(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("pesananId") String pesananId) {
        return pesananService.konfirmasiPembayaran(pesananId, user.getUsername());
    }
    
    @PatchMapping("/{pesananId}/packing")
    @PreAuthorize("hasAuthority('admin')")
    public Pesanan packing(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("pesananId") String pesananId) {
        return pesananService.packing(pesananId, user.getUsername());
    }
    
    @PatchMapping("/{pesananId}/kirim")
    @PreAuthorize("hasAuthority('admin')")
    public Pesanan kirim(
            @AuthenticationPrincipal UserDetailsImpl user,
            @PathVariable("pesananId") String pesananId) {
        return pesananService.kirim(pesananId, user.getUsername());
    }
    
    @GetMapping("/")
    @PreAuthorize("hasAuthority('user')")
    public List<Pesanan> findAllPesananUser(@AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "25", required = false) int limit) {
        return pesananService.findAllPesananUser(user.getUsername(), page, limit);
    }
    
    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('admin')")
    public List<Pesanan> search(@AuthenticationPrincipal UserDetailsImpl user,
            @RequestParam(name = "filterText", defaultValue = "", required = false) String filterText,
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "limit", defaultValue = "25", required = false) int limit) {
        return pesananService.search(filterText, page, limit);
    }
}
