package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorAlunoAtivo implements ValidadorAgendamento {
    @Autowired
    private AlunoRepository repository;

    @Override
    public void validar(DadosAgendamentoInstrucao dadosAgendamentoInstrucao){
        Boolean alunoAtivo = repository.findAtivoById(dadosAgendamentoInstrucao.idAluno());

        if(!alunoAtivo){
        throw new ValidacaoException("Agendamento não pode ser agendada com aluno inativo! ");
        }
    }
}
