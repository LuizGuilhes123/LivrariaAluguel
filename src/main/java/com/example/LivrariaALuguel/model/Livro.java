package com.example.LivrariaALuguel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;

    @ManyToOne
    @JoinColumn(name = "editora_id", nullable = false)
    private Editora editora;

    private String isbn;
    private Integer anoPublicacao;
}
