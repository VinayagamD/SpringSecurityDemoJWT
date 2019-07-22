package com.vinay.springsecdemo.repositories;

import com.vinay.springsecdemo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
