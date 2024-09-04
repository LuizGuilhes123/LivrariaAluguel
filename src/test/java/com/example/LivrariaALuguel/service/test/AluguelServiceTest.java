package com.example.LivrariaALuguel.service.test;

import com.example.LivrariaALuguel.model.Aluguel;
import com.example.LivrariaALuguel.model.Usuario;
import com.example.LivrariaALuguel.model.Livro;
import com.example.LivrariaALuguel.repository.AluguelRepository;
import com.example.LivrariaALuguel.service.AluguelService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AluguelServiceTest {

    @Autowired
    private AluguelService aluguelService;

    @MockBean
    private AluguelRepository aluguelRepository;

    @Test
    public void testListarTodos() {
        Usuario usuario = new Usuario();
        usuario.setNome("Jane Doe");

        Livro livro = new Livro();
        livro.setTitulo("Java Programming");

        Aluguel aluguel = new Aluguel();
        aluguel.setUsuario(usuario);
        aluguel.setLivro(livro);
        aluguel.setDataInicio(LocalDate.now());

        Mockito.when(aluguelRepository.findAll()).thenReturn(Collections.singletonList(aluguel));

        List<Aluguel> alugueis = aluguelService.listarTodos();
        assertEquals(1, alugueis.size());
        assertEquals("Jane Doe", alugueis.get(0).getUsuario().getNome());
        assertEquals("Java Programming", alugueis.get(0).getLivro().getTitulo());
    }
}
