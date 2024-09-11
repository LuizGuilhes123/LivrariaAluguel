package com.example.LivrariaALuguel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AluguelDTO {

    private Long id;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long livroId;
    private Long usuarioId;
}
