package com.example.LivrariaALuguel.service;


import com.example.LivrariaALuguel.model.Editora;
import com.example.LivrariaALuguel.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public List<Editora> listarTodos() {
        return editoraRepository.findAll();
    }

    public Optional<Editora> buscarPorId(Long id) {
        return editoraRepository.findById(id);
    }

    public Editora criarOuAtualizar(Editora editora) {
        return editoraRepository.save(editora);
    }

    public void deletar(Long id) {
        editoraRepository.deleteById(id);
    }
}
