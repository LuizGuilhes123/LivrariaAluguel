package com.example.LivrariaALuguel.service.test;


import com.example.LivrariaALuguel.model.Livro;
import com.example.LivrariaALuguel.repository.LivroRepository;
import com.example.LivrariaALuguel.service.LivroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LivroServiceTest {

    @Autowired
    private LivroService livroService;

    @MockBean
    private LivroRepository livroRepository;

    @Test
    public void testListarTodos() {
        Livro livro = new Livro();
        livro.setTitulo("Java Programming");

        Mockito.when(livroRepository.findAll()).thenReturn(Collections.singletonList(livro));

        List<Livro> livros = livroService.listarTodos();
        assertEquals(1, livros.size());
        assertEquals("Java Programming", livros.get(0).getTitulo());
    }
}
