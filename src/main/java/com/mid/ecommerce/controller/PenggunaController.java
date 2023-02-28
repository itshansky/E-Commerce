package com.mid.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mid.ecommerce.entity.Pengguna;
import com.mid.ecommerce.service.PenggunaService;

@RestController
@RequestMapping("/api/pengguna")
@PreAuthorize("isAuthenticated()")
public class PenggunaController {
    @Autowired
    private PenggunaService penggunaService;
    
    @GetMapping("/")
    public List<Pengguna> findAll() {
        return penggunaService.findAll();
    }
    
    @GetMapping("/{id}")
    public Pengguna findById(@PathVariable("id") String id) {
        return penggunaService.findById(id);
    }
    
    @PostMapping("/")
    public Pengguna create(@RequestBody Pengguna pengguna) {
        return penggunaService.create(pengguna);
    }
    
    @PutMapping("/")
    public Pengguna edit(@RequestBody Pengguna pengguna) {
        return penggunaService.edit(pengguna);
    }
    
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        penggunaService.deleteById(id);
    }
}
