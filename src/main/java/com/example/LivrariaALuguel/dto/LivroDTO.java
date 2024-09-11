package com.example.LivrariaALuguel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private Long editoraId;
}
