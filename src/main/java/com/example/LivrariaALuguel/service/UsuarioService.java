package com.example.LivrariaALuguel.service;

import com.example.LivrariaALuguel.exception.EmailJaCadastradoException;
import com.example.LivrariaALuguel.exception.SenhaIncorretaException;
import com.example.LivrariaALuguel.model.Usuario;
import com.example.LivrariaALuguel.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private final Pattern senhaPattern = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z]).{8,}$");

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario criarOuAtualizar(Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        if (usuarioExistente.isPresent()) {
            throw new EmailJaCadastradoException("Email já cadastrado!");
        }

        if (!emailPattern.matcher(usuario.getEmail()).matches()) {
            throw new IllegalArgumentException("Email inválido!");
        }

        if (!senhaPattern.matcher(usuario.getSenha()).matches()) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 8 caracteres, incluindo uma letra maiúscula e um número!");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario login(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        if (usuarioOpt.isEmpty()) {
            throw new IllegalArgumentException("Email não encontrado!");
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new SenhaIncorretaException("Senha incorreta!");
        }

        return usuario;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return User.withUsername(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(usuario.getRole())
                .build();
    }
}
