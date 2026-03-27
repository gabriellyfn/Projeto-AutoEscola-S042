package br.com.senai.s042.autoescolas042.application.core.domain.model;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosAtualizacaoAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.s042.autoescolas042.application.core.domain.vo.Endereco;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Aluno")
@Table(name = "Alunos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")

public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;
    private Boolean ativo = true;

    public Aluno(DadosCadastroAluno dados){
        this.id = null;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.cpf = dados.cpf();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;
    }


    public void atualizarInformacoes(DadosAtualizacaoAluno dadosAtualizacaoAluno) {
        if (dadosAtualizacaoAluno.nome() != null && !dadosAtualizacaoAluno.nome().isBlank()) {
            this.nome = dadosAtualizacaoAluno.nome();
        } if (dadosAtualizacaoAluno.telefone() != null && !dadosAtualizacaoAluno.telefone().isBlank()) {
            this.telefone = dadosAtualizacaoAluno.telefone();
        } if (dadosAtualizacaoAluno.endereco() != null) {
            this.endereco.atualizarInformacoes(endereco);
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
