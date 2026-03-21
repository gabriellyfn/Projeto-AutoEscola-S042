package br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrutor;

import br.com.senai.s042.autoescolas042.application.core.domain.dto.DadosEndereco;
import br.com.senai.s042.autoescolas042.application.core.domain.vo.Endereco;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.Especialidade;

public record DadosDetalhamentoInstrutor(
        Long id,
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade,
        DadosEndereco endereco,
        Boolean ativo) {}
