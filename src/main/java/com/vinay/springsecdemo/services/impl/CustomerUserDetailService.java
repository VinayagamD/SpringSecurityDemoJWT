package com.vinay.springsecdemo.services.impl;

import com.vinay.springsecdemo.exceptions.NoRoleFoundException;
import com.vinay.springsecdemo.models.Role;
import com.vinay.springsecdemo.models.User;
import com.vinay.springsecdemo.repositories.RoleRepository;
import com.vinay.springsecdemo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CustomerUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("Email Id Not Found"));
        return buildUserForAuthentication(user, getUserAuthority(user.getRoles()));
    }

    @Transactional
    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findByRole("ADMIN").orElseThrow(()-> new NoRoleFoundException("No Such Role Present"));
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles){
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach(role-> roles.add(new SimpleGrantedAuthority(role.getRole())));

        return new ArrayList<>(roles);
    }

    private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
    }
}
