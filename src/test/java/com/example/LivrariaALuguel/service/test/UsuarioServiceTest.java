package com.example.LivrariaALuguel.service.test;

import com.example.LivrariaALuguel.model.Usuario;
import com.example.LivrariaALuguel.repository.UsuarioRepository;
import com.example.LivrariaALuguel.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void testListarTodos() {
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");

        Mockito.when(usuarioRepository.findAll()).thenReturn(Collections.singletonList(usuario));

        List<Usuario> usuarios = usuarioService.listarTodos();
        assertEquals(1, usuarios.size());
        assertEquals("John Doe", usuarios.get(0).getNome());
    }
}
