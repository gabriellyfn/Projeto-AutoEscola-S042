package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.port.out.InstrutorRepository;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorInstrutorAtivo implements ValidadorAgendamento {
    @Autowired
    private InstrutorRepository repository;

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {
        Boolean instrutorAtivo = repository.findAtivoById(dadosAgendamentoInstrucao.idInstrutor());

        if(!instrutorAtivo){
            throw new ValidacaoException("Instrução não pode ser agendada com instrutor inativo! ");
        }

    }
}
