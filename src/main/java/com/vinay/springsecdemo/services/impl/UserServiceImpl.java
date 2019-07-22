package com.vinay.springsecdemo.services.impl;

import com.vinay.springsecdemo.configs.JwtTokenProvider;
import com.vinay.springsecdemo.models.Token;
import com.vinay.springsecdemo.models.User;
import com.vinay.springsecdemo.repositories.UserRepository;
import com.vinay.springsecdemo.services.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public List<User> findAll() {
        return repository.findAll().stream().map(User::userProfile).collect(Collectors.toList());
    }

    @Override
    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public void delete(User user) {
        repository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Token loginUser(User user) {
        Token jwtToken = new Token();
        User dbUser = repository.findByEmail(user.getEmail()).orElseThrow(()-> new UsernameNotFoundException("User Email Not Found"));
        if(passwordEncoder.matches(user.getPassword(),dbUser.getPassword())){
          String token = jwtTokenProvider.createToken(dbUser.getEmail(),dbUser.getRoles());
            jwtToken.setToken(token);
            jwtToken.setUser(dbUser);

        }else {
            throw new UsernameNotFoundException("Password Not Matched");
        }
        return jwtToken;
    }
}
