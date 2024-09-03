package com.example.LivrariaALuguel.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "aluguel")
public class Aluguel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "livro_id", nullable = false)
    private Livro livro;

    private LocalDate dataInicio;

    @Column(nullable = false)
    private LocalDate prazo;

    @Enumerated(EnumType.STRING)
    private StatusAluguel status;

    @PrePersist
    public void prePersist() {
        this.prazo = this.dataInicio.plusMonths(1);
        this.status = StatusAluguel.DENTRO_DO_PRAZO;
    }

    public enum StatusAluguel {
        DENTRO_DO_PRAZO, FORA_DO_PRAZO
    }
}
