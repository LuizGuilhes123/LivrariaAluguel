package com.example.LivrariaALuguel.controller;

import com.example.LivrariaALuguel.exception.EmailJaCadastradoException;
import com.example.LivrariaALuguel.model.Usuario;
import com.example.LivrariaALuguel.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String senha) {
        try {
            Usuario usuario = usuarioService.login(email, senha);
            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Usuario usuario) {
        try {
            Usuario createdUser = usuarioService.criarOuAtualizar(usuario);
            return ResponseEntity.ok(createdUser);
        } catch (IllegalArgumentException | EmailJaCadastradoException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
