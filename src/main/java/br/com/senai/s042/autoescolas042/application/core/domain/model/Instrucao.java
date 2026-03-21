package br.com.senai.s042.autoescolas042.application.core.domain.model;

import br.com.senai.s042.autoescolas042.application.core.domain.enums.instrucao.MotivoCancelamento;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.instrucao.StatusInstrucao;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "Instrucao")
@Table(name = "instrucoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Instrucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instrutor_id")
    private Instrutor instrutor;

    private LocalDateTime data;

    @Enumerated(EnumType.STRING)
    private StatusInstrucao status;

    @Enumerated(EnumType.STRING)
    private MotivoCancelamento motivoCancelamento;
}
