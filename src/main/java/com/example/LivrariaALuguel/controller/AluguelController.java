package com.example.LivrariaALuguel.controller;


import com.example.LivrariaALuguel.model.Aluguel;
import com.example.LivrariaALuguel.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluguels")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    public List<Aluguel> listarTodos() {
        return aluguelService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluguel> buscarPorId(@PathVariable Long id) {
        return aluguelService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Aluguel criar(@RequestBody Aluguel aluguel) {
        return aluguelService.criarOuAtualizar(aluguel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluguel> atualizar(@PathVariable Long id, @RequestBody Aluguel aluguel) {
        return aluguelService.buscarPorId(id)
                .map(existingAluguel -> {
                    aluguel.setId(id);
                    return ResponseEntity.ok(aluguelService.criarOuAtualizar(aluguel));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable Long id) {
        return aluguelService.buscarPorId(id)
                .map(existingAluguel -> {
                    aluguelService.deletar(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
