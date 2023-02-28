package com.mid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mid.ecommerce.entity.Produk;

public interface ProdukRepository extends JpaRepository<Produk, String> {
	
}
