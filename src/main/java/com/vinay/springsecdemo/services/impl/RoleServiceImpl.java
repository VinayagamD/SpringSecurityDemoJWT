package com.vinay.springsecdemo.services.impl;

import com.vinay.springsecdemo.models.Role;
import com.vinay.springsecdemo.repositories.RoleRepository;
import com.vinay.springsecdemo.services.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public void delete(Role role) {
        repository.delete(role);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Role findByRoleName(String name) {
        return repository.findByRole(name).orElse(null);
    }
}
