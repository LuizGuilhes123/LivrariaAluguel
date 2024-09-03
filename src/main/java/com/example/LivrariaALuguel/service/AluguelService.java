package com.example.LivrariaALuguel.service;


import com.example.LivrariaALuguel.model.Aluguel;
import com.example.LivrariaALuguel.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository livroRepository;

    public List<Aluguel> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Aluguel> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Aluguel criarOuAtualizar(Aluguel livro) {
        return livroRepository.save(livro);
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }
}
