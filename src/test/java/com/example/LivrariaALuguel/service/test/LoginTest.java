package com.example.LivrariaALuguel;

import com.example.LivrariaALuguel.model.Usuario;
import com.example.LivrariaALuguel.repository.UsuarioRepository;
import com.example.LivrariaALuguel.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void testLogin() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("John Doe");
        usuario.setEmail("john.doe@example.com");
        usuario.setSenha("$2a$10$X9V56g4z/Od0s59e9fZ0h.Q0Q/T8oX/fmivTQH6pwmrcFCxRm1VGa"); // senha codificada
        usuario.setRole("USER");

        usuarioRepository.save(usuario);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("username", "john.doe@example.com")
                        .param("password", "password123"))
                .andExpect(status().isOk());
    }
}
