package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosCancelamentoInstrucao;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.instrucao.StatusInstrucao;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorCancelamento;
import br.com.senai.s042.autoescolas042.application.port.out.InstrutorRepository;
import br.com.senai.s042.autoescolas042.application.port.out.InstrucaoRepository;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.InstrucaoNaoExisteException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CancelamentoDeInstrucoes {

    private final InstrucaoRepository repository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final List<ValidadorCancelamento> validadores;

    public CancelamentoDeInstrucoes(
            InstrucaoRepository repository,
            AlunoRepository alunoRepository,
            InstrutorRepository instrutorRepository,
            List<ValidadorCancelamento> validadores
    ) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
        this.instrutorRepository = instrutorRepository;
        this.validadores = validadores;
    }

    @Transactional
    public DadosDetalhamentoInstrucao cancelarInstrucao(DadosCancelamentoInstrucao dadosCancelamentoInstrucao) {
        Instrucao instrucao = repository.findById(dadosCancelamentoInstrucao.idInstrucao())
                .orElseThrow(() -> new InstrucaoNaoExisteException("Instrução não encontrada"));

        if (dadosCancelamentoInstrucao.motivo() == null) {
            throw new RuntimeException("Motivo deve ser preenchido!");
        }

        validadores.forEach(v -> v.validar(instrucao, dadosCancelamentoInstrucao));

        if (instrucao.getStatus() == StatusInstrucao.CANCELADA) {
            throw new RuntimeException("Instrução já está cancelada");
        }

        instrucao.setStatus(StatusInstrucao.CANCELADA);
        instrucao.setMotivoCancelamento(dadosCancelamentoInstrucao.motivo());
        repository.save(instrucao);

        return new DadosDetalhamentoInstrucao(instrucao);
    }
}
