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

import com.mid.ecommerce.entity.Kategori;
import com.mid.ecommerce.service.KategoriService;

@RestController
@RequestMapping("/api/kategori")
@PreAuthorize("isAuthenticated()")
public class KategoriController {
    @Autowired
    private KategoriService kategoriService;
    
    @GetMapping("/")
    public List<Kategori> findAll() {
        return kategoriService.findAll();
    }
    
    @GetMapping("/{id}")
    public Kategori findById(@PathVariable("id") String id) {
        return kategoriService.findById(id);
    }
    
    @PostMapping("/")
    public Kategori create(@RequestBody Kategori kategori) {
        return kategoriService.create(kategori);
    }
    
    @PutMapping("/")
    public Kategori edit(@RequestBody Kategori kategori) {
        return kategoriService.edit(kategori);
    }
    
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        kategoriService.deleteById(id);
    }
}
