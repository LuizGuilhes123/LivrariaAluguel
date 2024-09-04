package com.example.LivrariaALuguel.service.test;

import com.example.LivrariaALuguel.model.Editora;
import com.example.LivrariaALuguel.repository.EditoraRepository;
import com.example.LivrariaALuguel.service.EditoraService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EditoraServiceTest {

    @Autowired
    private EditoraService editoraService;

    @MockBean
    private EditoraRepository editoraRepository;

    @Test
    public void testListarTodos() {
        Editora editora = new Editora();
        editora.setNome("Editora ABC");
        editora.setCidade("São Paulo");

        Mockito.when(editoraRepository.findAll()).thenReturn(Collections.singletonList(editora));

        List<Editora> editoras = editoraService.listarTodos();
        assertEquals(1, editoras.size());
        assertEquals("Editora ABC", editoras.get(0).getNome());
        assertEquals("São Paulo", editoras.get(0).getCidade());
    }
}
