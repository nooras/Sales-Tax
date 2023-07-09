package com.salesTax.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salesTax.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String Name);
	
	List<Product> findByNameContaining(String name);
	
}
