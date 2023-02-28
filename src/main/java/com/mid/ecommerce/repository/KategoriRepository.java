package com.mid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mid.ecommerce.entity.Kategori;

public interface KategoriRepository extends JpaRepository<Kategori, String> {
	
}
