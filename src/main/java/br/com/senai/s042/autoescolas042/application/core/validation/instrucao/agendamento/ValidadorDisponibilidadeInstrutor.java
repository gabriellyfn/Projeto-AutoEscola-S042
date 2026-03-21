package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.port.out.InstrucaoRepository;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDisponibilidadeInstrutor implements ValidadorAgendamento {
    @Autowired
    private InstrucaoRepository repository;

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {
        Boolean instrutorOcupado = repository.existsByInstrutorIdAndData(
                dadosAgendamentoInstrucao.idInstrutor(),
                dadosAgendamentoInstrucao.data()
        );

        if (instrutorOcupado){
            throw new ValidacaoException("Instrutor ocupado na data e horário solicitado");
        }
    }
}
