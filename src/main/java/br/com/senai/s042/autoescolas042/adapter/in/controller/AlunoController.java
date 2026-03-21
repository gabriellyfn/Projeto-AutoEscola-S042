package br.com.senai.s042.autoescolas042.adapter.in.controller;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosAtualizacaoAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.aluno.DadosListagemAluno;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @PostMapping
    @Transactional
    public String cadastrarAluno(@RequestBody DadosCadastroAluno dadosAluno) {
        alunoRepository.save(new Aluno(dadosAluno));
        return "Cadastro realizado com sucesso!";
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAluno>> listarAlunos(
            @PageableDefault(size = 10, sort = {"nome"})Pageable pageable) {
        Page page = alunoRepository.findAllByAtivoTrue(pageable).map(DadosListagemAluno::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public void atualizarAluno(@RequestBody DadosAtualizacaoAluno dadosAtualizacaoAluno) {
        Aluno aluno = alunoRepository.getReferenceById(Long.valueOf(dadosAtualizacaoAluno.id()));
        aluno.atualizarInformacoes(dadosAtualizacaoAluno);
        alunoRepository.save(aluno);
    }

    @DeleteMapping
    @Transactional
    public void excluirAluno(@PathVariable Long id){
        Aluno aluno = alunoRepository.getReferenceById(id);
        aluno.excluir();
        alunoRepository.save(aluno);
    }

}
