package com.gm2.pdv.pdv.repository;

import com.gm2.pdv.pdv.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
