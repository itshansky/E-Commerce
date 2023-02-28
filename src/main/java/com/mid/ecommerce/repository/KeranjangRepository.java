package com.mid.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mid.ecommerce.entity.Keranjang;

public interface KeranjangRepository extends JpaRepository<Keranjang, String> {
    Optional<Keranjang> findByPenggunaIdAndProdukId(String username, String produkId);
    
    List<Keranjang> findByPenggunaId(String username);
}
