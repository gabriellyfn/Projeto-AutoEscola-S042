package br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrucao;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosDetalhamentoInstrucao(
        Long id,
        String aluno,
        String instrutor,
        Especialidade especialidade,

        @JsonFormat(pattern = "dd/MM/yyyy - HH:mm")
        LocalDateTime data) {
    public DadosDetalhamentoInstrucao(Instrucao instrucao){

        this(
                instrucao.getId(),
                instrucao.getAluno().getNome(),
                instrucao.getInstrutor().getNome(),
                instrucao.getInstrutor().getEspecialidade(),
                instrucao.getData()
        );
    }
}

