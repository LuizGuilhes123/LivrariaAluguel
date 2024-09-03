package com.example.LivrariaALuguel.controller;


import com.example.LivrariaALuguel.model.Editora;
import com.example.LivrariaALuguel.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public List<Editora> listarTodos() {
        return editoraService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Editora> buscarPorId(@PathVariable Long id) {
        return editoraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Editora criar(@RequestBody Editora editora) {
        return editoraService.criarOuAtualizar(editora);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Editora> atualizar(@PathVariable Long id, @RequestBody Editora editora) {
        return editoraService.buscarPorId(id)
                .map(existingEditora -> {
                    editora.setId(id);
                    return ResponseEntity.ok(editoraService.criarOuAtualizar(editora));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return editoraService.buscarPorId(id)
                .map(existingEditora -> {
                    editoraService.deletar(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
