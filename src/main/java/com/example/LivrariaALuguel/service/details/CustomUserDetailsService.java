package com.example.LivrariaALuguel.service.details;

import com.example.LivrariaALuguel.model.Usuario;
import com.example.LivrariaALuguel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(usuario.getNome(), usuario.getSenha(),
                AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}

