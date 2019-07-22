package com.vinay.springsecdemo.services.impl;

import com.vinay.springsecdemo.models.Product;
import com.vinay.springsecdemo.repositories.ProductRepository;
import com.vinay.springsecdemo.services.ProductService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;

    }

    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void delete(Product product) {
        repository.delete(product);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
