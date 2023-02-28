package com.mid.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mid.ecommerce.entity.PesananItem;

public interface PesananItemRepository extends JpaRepository<PesananItem, String> {
	
}
