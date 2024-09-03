package com.example.LivrariaALuguel.repository;


import com.example.LivrariaALuguel.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
}
