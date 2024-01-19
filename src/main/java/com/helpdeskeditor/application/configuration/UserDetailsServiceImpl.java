package com.helpdeskeditor.application.configuration;

import com.helpdeskeditor.application.app.data.entity.UsuarioSoporteEntity;

import java.util.Collections;
import java.util.List;

import com.helpdeskeditor.application.app.data.repository.UsuariosSoporteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuariosSoporteRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UsuariosSoporteRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioSoporteEntity user = userRepository.findByNombreUsuario(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user present with username: " + username);
        }
        else {
            return new org.springframework.security.core.userdetails.User(user.getNombreUsuario(),
                    user.getPassword(),
                    getAuthorities(user));
        }
    }

    private static List<GrantedAuthority> getAuthorities(UsuarioSoporteEntity user) {
        //return user.getRoles().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + user.getRol()));
    }

}