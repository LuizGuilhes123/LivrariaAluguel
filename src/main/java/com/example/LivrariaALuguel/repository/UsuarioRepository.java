package com.example.LivrariaALuguel.repository;


import com.example.LivrariaALuguel.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNome(String nome);

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}
