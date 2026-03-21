package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosAtualizacaoAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.aluno.DadosListagemAluno;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository){
        this.repository = repository;
    }

    @Transactional
    public DadosListagemAluno cadastrar (DadosCadastroAluno dados){
        Aluno aluno = new Aluno(dados);
        repository.save(aluno);
        return new DadosListagemAluno(aluno);
    }

    public Page<DadosListagemAluno> listar(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao)
                .map(DadosListagemAluno::new);
    }

    @Transactional
    public DadosListagemAluno atualizar(DadosAtualizacaoAluno dados){
        Aluno aluno = repository.getReferenceById(Long.valueOf(dados.id()));
        aluno.atualizarInformacoes(dados);
        repository.save(aluno);
        return new DadosListagemAluno(aluno);
    }

    @Transactional
    public void excluir(Long id){
        Aluno aluno = repository.getReferenceById(id);
        aluno.excluir();
        repository.save(aluno);
    }
}


