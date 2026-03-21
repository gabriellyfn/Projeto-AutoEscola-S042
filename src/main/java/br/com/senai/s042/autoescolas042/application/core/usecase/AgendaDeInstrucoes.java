package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import br.com.senai.s042.autoescolas042.application.port.out.InstrucaoRepository;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.instrucao.StatusInstrucao;
import br.com.senai.s042.autoescolas042.exception.types.aluno.AlunoNaoExisteException;
import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrutor;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.EspecialidadeNaoInformadaException;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.InstrutorIndisponivelException;
import br.com.senai.s042.autoescolas042.exception.types.instrutor.InstrutorNaoExisteException;
import br.com.senai.s042.autoescolas042.application.port.out.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeInstrucoes {

    private final InstrucaoRepository repository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final List<ValidadorAgendamento> validadores;

    public AgendaDeInstrucoes(
            InstrucaoRepository repository,
            AlunoRepository alunoRepository,
            InstrutorRepository instrutorRepository,
            List<ValidadorAgendamento> validadores
    ){
        this.repository = repository;
        this.alunoRepository = alunoRepository;
        this.instrutorRepository = instrutorRepository;
        this.validadores = validadores;
    }

    @Transactional
    public DadosDetalhamentoInstrucao agendarInstrucao(DadosAgendamentoInstrucao dadosAgendamentoInstrucao){
       if (!alunoRepository.existsById(dadosAgendamentoInstrucao.idAluno())){
           throw new AlunoNaoExisteException("Id do aluno não encontrado!");
       }
       if (dadosAgendamentoInstrucao.idInstrutor() != null && !instrutorRepository.existsById(dadosAgendamentoInstrucao.idInstrutor())){
           throw new InstrutorNaoExisteException("Id do instrutor não encontrado!");
       }

       //Validacoes das regras de negocio
        validadores.forEach(v -> v.validar(dadosAgendamentoInstrucao));
        Aluno aluno = alunoRepository.getReferenceById(dadosAgendamentoInstrucao.idAluno());

        Instrutor instrutor = escolherInstrutor(dadosAgendamentoInstrucao);

        if (instrutor == null){
            throw new InstrutorIndisponivelException("Não há instrutor disponível para a data e hora escolhida!");
        }

        Instrucao instrucao = new Instrucao(
                null,
                aluno,
                instrutor,
                dadosAgendamentoInstrucao.data(),
                StatusInstrucao.AGENDADA,
                null
        );

        repository.save(instrucao);

        return new DadosDetalhamentoInstrucao(instrucao);
    }

    private Instrutor escolherInstrutor(DadosAgendamentoInstrucao dadosAgendamentoInstrucao) {

        if (dadosAgendamentoInstrucao.idInstrutor() != null) {

            return instrutorRepository.getReferenceById(dadosAgendamentoInstrucao.idInstrutor());
        }

        if(dadosAgendamentoInstrucao.especialidade() == null) {

            throw new EspecialidadeNaoInformadaException("Especialidade é obrigatoria quando o Instrutor não for informado!");
        }

        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dadosAgendamentoInstrucao.especialidade(),dadosAgendamentoInstrucao.data());
    }
}